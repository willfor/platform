package controllers.ajax;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import models.PlatformDao;
import models.po.AndroidAgooConfigure;
import models.po.AndroidBranchForBuild;
import models.po.AndroidMainTask;
import models.po.AndroidPackTask;
import models.po.AndroidSubTask;
import models.vo.PackInfo;
import play.Logger;
import play.mvc.Controller;
import util.StringUtil;

import com.google.gson.Gson;

import controllers.BuildTaskTrigger;
import controllers.convertor.TaskConvertor;
import enums.SubTaskType;

public class PackController extends Controller {
	private static PlatformDao dao = new PlatformDao();
	private static BuildTaskTrigger buildTaskTrigger = new BuildTaskTrigger();
	
	public static void applyMapping(String mappingPath,String versionName){
		Logger.info(mappingPath );
		Logger.info(versionName );
		buildTaskTrigger.triggerMappingTask(mappingPath, versionName);
		renderText("success");
	}
	
	public static void getPackDetail(int packTaskId){
		AndroidPackTask packTask = dao.findPackTaskByPackTaskId(packTaskId);
		renderJSON(TaskConvertor.getPackInfoForDisplay(packTask));	
	}

	public static void filterByBranchName(String branchName, int from,
			int batchCount) {
		List<AndroidPackTask> packTasks = dao.listPackTasks(branchName, from,
				batchCount);
		renderJSON(TaskConvertor.convertToPackInfo(packTasks));
	}
	
	public static void listAgooConfigures(){
		List<AndroidAgooConfigure> agooConfigures = dao.listAgooConfigures();
		renderJSON(agooConfigures);
	}

	public static void listMainTasks(int from, int batchCount) {
		List<AndroidMainTask> mainTasks = dao.listMainTasksByPage(from,
				batchCount);
		renderJSON(TaskConvertor.convertPackInfo(mainTasks));
	}
	
	public static void getMainTaskTotalCount(){
		renderText(dao.getMainTaskTotalCount());
	}

	public static void listEnabledBranches() {
		List<AndroidBranchForBuild> branchedForBuild = dao
				.listEnabledBranches();
		renderJSON(TaskConvertor.getBranchNames(branchedForBuild));
	}

	public static void listEnabledVersionNames() {
		List<AndroidBranchForBuild> branchedForBuild = dao
				.listEnabledBranches();
		renderJSON(TaskConvertor.getVersionNames(branchedForBuild));
	}

	public static void listEnabledVersionCodes() {
		List<AndroidBranchForBuild> branchedForBuild = dao
				.listEnabledBranches();
		renderJSON(TaskConvertor.getVersionCodes(branchedForBuild));
	}

	public static void listAutomationTestTypes() {
		renderJSON(dao.listAutomationTestTypes());
	}

	public static void savePackInfo(String packInfoJson) {
		Gson gson = new Gson();
		PackInfo packInfoObj = gson.fromJson(packInfoJson, PackInfo.class);
		System.out.println("packInfo---" + packInfoObj);
		AndroidMainTask mainTask = TaskConvertor
				.convertFromPackInfoToMainTask(packInfoObj);
		mainTask.save();
		try {
			buildTaskTrigger
					.triggerPackTask(mainTask.getPackTask().getTaskId(),packInfoObj.isProguardResourceFiles());
		} catch (Exception e) {
			renderText("save db success,trigger pack task failed!");
		}
		renderText("success");
	}

	public static void saveBranchInfo(String branchInfo) {
		Gson gson = new Gson();
		AndroidBranchForBuild branch = gson.fromJson(branchInfo,
				AndroidBranchForBuild.class);
		trimBranchDetails(branch);
		AndroidPackTask task = null;
		if(StringUtils.isEmpty(branch.getVersionCode()) || StringUtils.isEmpty(branch.getVersionName())){
			task =new AndroidPackTask();
			task.setTaskId(0);
		}else{
			task = dao.newContinuousPacktask(branch);
		}
		saveBranchForContinuousBuild(branch, task);
		renderJSON(dao.listAllBranches());
	}

	private static void trimBranchDetails(AndroidBranchForBuild branch) {
		branch.setBranchName(StringUtil.getTrimedStr(branch.getBranchName()));
		branch.setPpserverUrl(StringUtil.getTrimedStr(branch.getPpserverUrl()));
		branch.setVersionCode(StringUtil.getTrimedStr(branch.getVersionCode()));
		branch.setVersionName(StringUtil.getTrimedStr(branch.getVersionName()));
	}

	private static void saveBranchForContinuousBuild(
			AndroidBranchForBuild branch, AndroidPackTask task) {
		branch.setCreateTimeToNow();
		branch.setUpdateTimeToNow();
		branch.setToEnabledStatus();
		branch.setLastBuildTimeToNow();
		branch.setPackTaskId(task.getTaskId());
		branch.save();
	}

	public static void listBranches() {
		System.out.println("request to list branches");
		List<AndroidBranchForBuild> branchesForBuild = dao.listAllBranches();
		renderJSON(TaskConvertor.convertToBranchForBuildInfo(branchesForBuild));
	}

	public static void updateBranchStatus(int branchId, String status) {
		dao.updateBranchStatusById(branchId, status);
		renderText("success");
	}

	public static void listReportData(int mainTaskId) {
		List<AndroidSubTask> subTasks = dao
				.findSubTasksByMainTaskId(mainTaskId);
		renderJSON(TaskConvertor.constructTestResult(subTasks));
	}
	
	public static void listMonkeyResult(int mainTaskId) {
		List<AndroidSubTask> subTasks = dao
				.findSubTasksByMainTaskIdAndTestType(mainTaskId,
						SubTaskType.TEST_MONKEY);
		renderJSON(TaskConvertor.convertToMonkeyTestResult(subTasks));
	}

	public static void listReportUrl(int mainTaskId) {
		AndroidPackTask packTask = dao.findPackTaskByMainTaskId(mainTaskId);
		List<AndroidSubTask> subTasks = dao
				.findSubTasksByMainTaskId(mainTaskId);
		renderJSON(TaskConvertor.constructSubTaskJobUrls(packTask, subTasks));
	}

}
