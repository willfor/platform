package models;

import java.util.Date;
import java.util.List;

import models.po.AndroidAgooConfigure;
import models.po.AndroidBranchForBuild;
import models.po.AndroidIssueDetail;
import models.po.AndroidMainTask;
import models.po.AndroidPackTask;
import models.po.AndroidPlatformConfig;
import models.po.AndroidSubTask;
import models.po.AndroidTestCPU;
import models.po.AndroidTestMemory;
import models.po.AndroidTestMonkey;
import models.po.AndroidTestType;
import models.po.AndroidTestTypeDetail;
import play.Logger;
import util.StringUtil;
import controllers.convertor.TaskConvertor;
import enums.BranchStatus;
import enums.SubTaskType;
import enums.TaskStatus;

public class PlatformDao {

	public AndroidPackTask findPackTaskByPackTaskId(int packTaskId) {
		return AndroidPackTask.findById(packTaskId);
	}
	
	public long getMainTaskTotalCount(){
		return AndroidMainTask.count();
	}

	public List<AndroidSubTask> findSubTasksByMainTaskId(int mainTaskId) {
		return AndroidSubTask.find(" main_task_id = " + mainTaskId).fetch();
	}
	
	public List<AndroidSubTask> findSubTasksByMainTaskIdAndTestType(int mainTaskId,SubTaskType subTaskType) {
		return AndroidSubTask.find(" main_task_id = " + mainTaskId + " and test_type_id=" + subTaskType.getTypeId()).fetch();
	}

	public AndroidPackTask findPackTaskByMainTaskId(int mainTaskId) {
		return AndroidPackTask.find(" main_task_id = " + mainTaskId).first();
	}

	/** need order by cpu_test_id, to keep order */
	public List<AndroidTestCPU> findCPUTestDataBySubTaskId(int subTaskId) {
		return AndroidTestCPU.find(
				" sub_task_id = " + subTaskId + " order by cpu_test_id asc")
				.fetch();
	}
	/**
	 * need order by memory_test_id, to keep order
	 * */
	public List<AndroidTestMemory> findMemoryTestDataBySubTaskId(int subTaskId) {
		return AndroidTestMemory.find(
				" sub_task_id = " + subTaskId + " order by memory_test_id asc")
				.fetch();
	}
	
	
	public List<AndroidTestMonkey> findMonkeyTestDataBySubTaskId(
			List<Integer> subTaskIds) {
		return AndroidTestMonkey.find(
				" sub_task_id in ("
						+ StringUtil.convertListToString(subTaskIds)
						+ ") and errorInfo <> '"+AndroidTestMonkey.NORMAL+"' order by md5 asc").fetch();
	}
	

	public List<AndroidBranchForBuild> listAllBranches() {
		return AndroidBranchForBuild.find("order by create_time desc").fetch();
	}

	public List<AndroidMainTask> listMainTasksByPage(int from, int batchCount) {
		return AndroidMainTask.find("order by create_time desc").from(from)
				.fetch(batchCount);
	}

	public List<AndroidAgooConfigure> listAgooConfigures() {
		return AndroidAgooConfigure.find(" order by agoo_description asc").fetch();
	}

	public List<AndroidPackTask> listPackTasks(String branchName, int from,
			int batchCount) {
		return AndroidPackTask
				.find("main_repository_branch='" + branchName
						+ "' order by create_time desc").from(from)
				.fetch(batchCount);
	}

	public AndroidPackTask listPackTaskByMainTaskId(int mainTaskId) {
		return AndroidPackTask.find("main_task_id=" + mainTaskId).first();
	}

	public List<AndroidTestType> listAutomationTestTypes() {
		return AndroidTestType.find("order by typeId asc").fetch();
	}

	public List<AndroidTestTypeDetail> listTestTypeDetail(int typeId) {
		return AndroidTestTypeDetail.find(" typeId= " + typeId).fetch();
	}

	public AndroidPlatformConfig getConfigByKey(String key) {
		return AndroidPlatformConfig.find(" config_key='" + key + "'").first();
	}

	public List<AndroidBranchForBuild> listEnabledBranches() {
		return AndroidBranchForBuild.find(
				"branch_status='" + BranchStatus.ENABLED.getStatusName()
						+ "' order by branch_name desc").fetch();
	}

	public void updateBranchStatusById(int branchId, String status) {
		AndroidBranchForBuild branch = AndroidBranchForBuild.findById(branchId);
		branch.setStatus(BranchStatus.findByStatusName(status).getStatusName());
		branch.setUpdateTimeToNow();
		if (BranchStatus.isDisable(status)) {
			AndroidPackTask relatedPackTask = AndroidPackTask.find(
					"taskId=" + branch.getPackTaskId()).first();
			if (relatedPackTask != null) {
				relatedPackTask.delete();
			}
			branch.setPackTaskId(0);
		} else if (BranchStatus.isEnable(status)) {
			AndroidPackTask newTask = newContinuousPacktask(branch);
			branch.setPackTaskId(newTask.getTaskId());
		}
		branch.save();
	}

	public AndroidPackTask newContinuousPacktask(AndroidBranchForBuild branch) {
		AndroidPackTask packTask = TaskConvertor
				.constructContinuousBuildTask(branch);
		AndroidMainTask mainTask = AndroidMainTask.initMainTask();
		mainTask.setPackTask(packTask);
		mainTask.save();
		return packTask;
	}

	public void updatePackTasksExecutedLongTimeToFailed() {
		List<AndroidPackTask> packTasks = AndroidPackTask
				.find(" DATE_FORMAT(NOW(),'%Y%m%d%H')-DATE_FORMAT(done_time,'%Y%m%d%H') > 1 and status='"
						+ TaskStatus.PROCESSING.getName() + "'").fetch();
		if (packTasks.size() == 0) {
			System.out.println("not pack task executed long than one hour!");
			return;
		}
		for (AndroidPackTask packTask : packTasks) {
			System.out.println("will update packTask:" + packTask.getTaskId()
					+ " to failed!");
			packTask.setStatus(TaskStatus.FAILED.getName());
			packTask.save();
		}

	}

	public void updateSubTasksExecutedLongTimeToFailed() {
		List<AndroidSubTask> subTasks = AndroidSubTask
				.find(" DATE_FORMAT(NOW(),'%Y%m%d%H')-DATE_FORMAT(endTime,'%Y%m%d%H') > 1 and status ='"
						+ TaskStatus.PROCESSING.getName() + "'").fetch();
		if (subTasks.size() == 0) {
			System.out.println("not subTasks executed long than one hour!");
			return;
		}
		for (AndroidSubTask subTask : subTasks) {
			System.out.println("will update subTask:" + subTask.getSubTaskId()
					+ " to failed!");
			subTask.setStatus(TaskStatus.FAILED.getName());
			subTask.save();
		}
	}

	public void updateMainTaskStatus() {
		AndroidMainTask mainTask = AndroidMainTask.find(
				"main_task_status in ('" + TaskStatus.PROCESSING.getName()
						+ "','" + TaskStatus.WAITING.getName()
						+ "') order by end_time asc").first();
		if (mainTask == null) {
			Logger.info(" no processing/waiting main task, no need do any update!");
			return;
		}
		Logger.info("start to update mainTaskStatus mainTaskId:"
				+ mainTask.getMainTaskId());
		AndroidPackTask p = mainTask.getPackTask();
		List<AndroidSubTask> subTasks = mainTask.getSubTasks();
		if (p == null) {
			p = AndroidPackTask
					.find("main_task_id=" + mainTask.getMainTaskId()).first();
		}
		TaskStatus expectedStatus = TaskConvertor
				.getExpectedStatus(p, subTasks);
		updateMainTaskStatus(mainTask,
				TaskStatus.findTaskStatusByStatusName(mainTask.getStatus()),
				expectedStatus);
	}

	private void updateMainTaskStatus(AndroidMainTask mainTask,
			TaskStatus originStatus, TaskStatus expectedStatus) {
		if (expectedStatus !=null && originStatus != expectedStatus) {
			mainTask.setStatus(expectedStatus.getName());
			Logger.info("mainTask:" + mainTask.getMainTaskId()
					+ " status changed from :" + originStatus + " to:"
					+ expectedStatus + " save to db successfully");
		}else{
			Logger.info("no need update maintask status!" + mainTask.getMainTaskId());
		}
		mainTask.setEndTime(new Date());
		mainTask.save();
	}

	public AndroidIssueDetail saveIssue(AndroidIssueDetail issueDetail) {
		return issueDetail.save();
	}

}
