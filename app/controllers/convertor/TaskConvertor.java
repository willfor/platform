package controllers.convertor;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import models.PlatformDao;
import models.po.AndroidBranchForBuild;
import models.po.AndroidMainTask;
import models.po.AndroidPackTask;
import models.po.AndroidSubTask;
import models.po.AndroidTestCPU;
import models.po.AndroidTestMemory;
import models.po.AndroidTestMonkey;
import models.vo.BranchForBuildInfo;
import models.vo.MonkeyTestResult;
import models.vo.PackInfo;
import models.vo.PackInfoForDisplay;
import models.vo.ReportData;
import models.vo.TaskResult;
import models.vo.TaskStatusInfo;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;

import play.Logger;
import util.StringUtil;
import enums.BranchStatus;
import enums.BuildType;
import enums.PackageAPKPostFix;
import enums.PackageMappingPostfix;
import enums.SignatureType;
import enums.SubTaskType;
import enums.TaskStatus;

public class TaskConvertor {
	private static PlatformDao dao = new PlatformDao();

	public static AndroidMainTask convertFromPackInfoToMainTask(
			PackInfo packInfo) {
		AndroidMainTask maintask = AndroidMainTask.initMainTask();
		maintask.setPackTask(convertFromUIPackInfo(packInfo));
		addSubTask(maintask, packInfo);
		return maintask;
	}

	public static List<BranchForBuildInfo> convertToBranchForBuildInfo(
			List<AndroidBranchForBuild> branchesForBuild) {
		List<BranchForBuildInfo> branches = new ArrayList<BranchForBuildInfo>();
		for (AndroidBranchForBuild originBuildInfo : branchesForBuild) {
			BranchForBuildInfo destBuildInfo = new BranchForBuildInfo();
			try {
				BeanUtils.copyProperties(destBuildInfo, originBuildInfo);
			} catch (Exception e) {
				continue;
			}
			destBuildInfo.setStatusDescription(BranchStatus
					.getBranchStatusDescriptionByValue(destBuildInfo
							.getStatus()));
			branches.add(destBuildInfo);
		}
		return branches;
	}

	public static void addSubTask(AndroidMainTask mainTask, PackInfo packInfo) {
		if (packInfo.getTestTypes() != null
				&& packInfo.getTestTypes().size() > 0) {
			for (String testType : packInfo.getTestTypes()) {
				AndroidSubTask subTask = new AndroidSubTask();
				subTask.setCreateTime(new Date());
				subTask.setTestTypeId(Integer.valueOf(testType));
				subTask.setStatus(TaskStatus.WAITING.getName());
				mainTask.addToSubTasks(subTask);
			}
		}
	}

	public static AndroidPackTask convertFromUIPackInfo(PackInfo packInfo) {
		AndroidPackTask packTask = new AndroidPackTask();
		packTask.setChannel(StringUtil.getTrimedStr(packInfo.getChannel()));
		packTask.setCreatTime(new Date());
		packTask.setPpserver(StringUtil.getTrimedStr(packInfo.getPpserverUrl()));
		packTask.setStatus(TaskStatus.WAITING.getName());
		packTask.setBuildType(BuildType.getBuildTypeNameByName(StringUtil.getTrimedStr(packInfo
				.getBuildType())));

		packTask.setSignature(SignatureType.findBySignatureTypeName(
				StringUtil.getTrimedStr(packInfo.getSignature())).getName());

		packTask.setMainBranch(StringUtil.getTrimedStr(packInfo.getDepBranch()));
		packTask.setMainTag(StringUtil.getTrimedStr(packInfo.getDepTag()));
		packTask.setCommonBranch(StringUtil.getTrimedStr(packInfo.getCommonBranch()));
		packTask.setCommonTag(StringUtil.getTrimedStr(packInfo.getCommonTag()));
		packTask.setBarCodeBranch(StringUtil.getTrimedStr(packInfo.getBarCodeBranch()));
		packTask.setBarCodeTag(StringUtil.getTrimedStr(packInfo.getBarCodeTag()));

		packTask.setVersionCode(StringUtil.getTrimedStr(packInfo.getVersionCode()));
		packTask.setVersionName(StringUtil.getTrimedStr(packInfo.getVersionName()));
		packTask.setAgooKey(StringUtil.getTrimedStr(packInfo.getAgooConfigure()).split("_")[0]);
		packTask.setAgooSecret(StringUtil.getTrimedStr(packInfo.getAgooConfigure()).split("_")[1]);
		return packTask;
	}

	public static void addToTestDataMap(List<AndroidTestCPU> cpuTests,
			Map<String, List<Double>> keyAndValues) {
		for (AndroidTestCPU cpuTest : cpuTests) {
			String key = cpuTest.getProcess();
			List<Double> testDatas = keyAndValues.get(cpuTest.getProcess());
			if (testDatas == null) {
				testDatas = new ArrayList<Double>();
			}
			testDatas.add(Double.parseDouble(cpuTest.getCpuPercentage()));
			keyAndValues.put(key, testDatas);
		}
	}

	public static void addToTestDataMap2(List<AndroidTestMemory> memoryTests,
			Map<String, List<Double>> keyAndValues) {
		for (AndroidTestMemory memoryTest : memoryTests) {
			List<Double> testDatas = keyAndValues.get(memoryTest.getProcess());
			if (testDatas == null) {
				testDatas = new ArrayList<Double>();
			}
			testDatas.add(Double.parseDouble(memoryTest.getMemory()));
			keyAndValues.put(memoryTest.getProcess(), testDatas);
		}
	}

	public static List<PackInfoForDisplay> convertToPackInfo(
			List<AndroidPackTask> packTasks) {
		List<PackInfoForDisplay> packInfos = new ArrayList<PackInfoForDisplay>();
		for (AndroidPackTask packTask : packTasks) {
			PackInfoForDisplay packInfo = getPackInfoForDisplay(packTask);
			packInfos.add(packInfo);
		}
		return packInfos;
	}

	public static PackInfoForDisplay getPackInfoForDisplay(
			AndroidPackTask packTask) {
		PackInfoForDisplay packInfo = new PackInfoForDisplay();
		constructPackInfoForDisplay(packInfo, packTask, packTask.getMainTask());
		return packInfo;
	}

	public static List<PackInfoForDisplay> convertPackInfo(
			List<AndroidMainTask> mainTasks) {
		List<PackInfoForDisplay> packInfos = new ArrayList<PackInfoForDisplay>();
		for (AndroidMainTask mainTask : mainTasks) {
			try {
				PackInfoForDisplay packInfo = new PackInfoForDisplay();
				AndroidPackTask packTask = mainTask.getPackTask();
				if (packTask == null) {
					Logger.info("will query from db by mainTaskId");
					packTask = dao.listPackTaskByMainTaskId(mainTask
							.getMainTaskId());
				}
				if (packTask == null) {
					Logger.error("mainTask no packTask will ignore this record");
					continue;
				}
				constructPackInfoForDisplay(packInfo, packTask, mainTask);
				packInfos.add(packInfo);
			} catch (Exception e) {
				e.printStackTrace();
				Logger.error("转换打包信息时出现异常！" + e.getMessage());
				continue;
			}
		}
		return packInfos;
	}

	private static void constructPackInfoForDisplay(
			PackInfoForDisplay packInfo, AndroidPackTask packTask,
			AndroidMainTask mainTask) {
		try{
		packInfo.setApkName(packTask.getApkName());
		packInfo.setBarCodeBranch(packTask.getBarCodeBranch());
		packInfo.setBarCodeTag(packTask.getBarCodeTag());

		packInfo.setChannel(packTask.getChannel());
		packInfo.setCommonBranch(packTask.getCommonBranch());
		packInfo.setCommonTag(packTask.getCommonTag());
		packInfo.setCreateTime(formatDate(packTask.getCreatTime()));
		packInfo.setDoneTime(formatDate(packTask.getDoneTime()));
		if (packTask.getDoneTime() != null) {
			packInfo.setDoneTimestamp(packTask.getDoneTime().getTime() / 1000);
		}
		packInfo.setMainBranch(packTask.getMainBranch());
		packInfo.setMainTag(packTask.getMainTag());
		packInfo.setPackTaskId(String.valueOf(packTask.getTaskId()));
		packInfo.setPpserverUrl(packTask.getPpserver());

		packInfo.setSignature(SignatureType.findBySignatureTypeName(
				packTask.getSignature()).getDescription());
		packInfo.setStatus(mainTask.getStatus());
		packInfo.setStatusDescription(TaskStatus
				.findTaskStatusDescriptionByName(mainTask.getStatus()));
		packInfo.setBuildType(BuildType.getBuildTypeByName(
				packTask.getBuildType()).getBuildTypeDescription());

		packInfo.setVersionCode(packTask.getVersionCode());
		packInfo.setVersionName(packTask.getVersionName());
		packInfo.setUrl(packTask.getUrl());
		if (mainTask != null) {
			packInfo.setMainTaskStatus(mainTask.getStatus());
			packInfo.setMainTaskId(String.valueOf(mainTask.getMainTaskId()));
		}
		packInfo.addToTaskStatusInfos(new TaskStatusInfo(packTask.getTaskId(),SubTaskType.PACK,
				TaskStatus.findTaskStatusByStatusName(packTask.getStatus())));
		for (AndroidSubTask subTask : mainTask.getSubTasks()) {
			packInfo.addToTaskStatusInfos(new TaskStatusInfo(subTask.getSubTaskId(),SubTaskType
					.findSubTaskTypeById(subTask.getTestTypeId()), TaskStatus
					.findTaskStatusByStatusName(subTask.getStatus())));
		}
		double base = (double) 1
				/ (double) packInfo.getTaskStatusInfos().size();
		double total = 0;
		for (TaskStatusInfo taskStatusInfo : packInfo.getTaskStatusInfos()) {
			TaskStatus status = TaskStatus
					.findTaskStatusByStatusName(taskStatusInfo.getTaskStatus());
			if(status!=null){
			total += status.getPercentage() * base;
			}
		}
		Logger.info("packtaskId:" + packInfo.getPackTaskId() + " total:"
				+ total);
		packInfo.setStatusFinishPercentage(formatDecemail(total));
		packInfo.setOnlyHavePackTask(packInfo.getTaskStatusInfos().size() == 1);
		packInfo.setDebugMappingUrl(packTask.getUrl()
				+ PackageMappingPostfix.debug.getPostfix());
		packInfo.setReleaseMappingUrl(packTask.getUrl()
				+ PackageMappingPostfix.release.getPostfix());
		packInfo.setReleaseMappingFilePath(getAbsoluteApkUrl(
				PackageMappingPostfix.release, packTask.getUrl()));
		packInfo.setReleaseMD5(packTask.getApkMD5());
		packInfo.setFileSize(convertByteToMB(packTask.getApkByteSize()));
		packInfo.setApkDebugReleaseUrl(packTask.getUrl()
				+ PackageAPKPostFix.debugRelease.getPostfix());
		packInfo.setApkReleaseUrl(packTask.getUrl()
				+ PackageAPKPostFix.release.getPostfix());
		}catch(Exception e){
			Logger.error("exception throws when convert info,mainTaskId was:" + mainTask.getMainTaskId());
			e.printStackTrace();
		}
	}

	public static String getAbsoluteApkUrl(
			PackageMappingPostfix mappingPostFix, String apkUrl) {
		if (StringUtils.isEmpty(apkUrl)) {
			return "";
		}
		String packServerPath = dao.getConfigByKey("packServerPath")
				.getConfigValue();
		String packageName = apkUrl.substring(apkUrl.lastIndexOf("/") + 1,
				apkUrl.length());
		return packServerPath + packageName + mappingPostFix.getPostfix();
	}

	private static String convertByteToMB(String fileLength) {
		if (fileLength == null || fileLength == "") {
			return null;
		}
		BigDecimal bd = new BigDecimal(Double.valueOf(fileLength)
				/ (1024 * 1024));
		bd.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
		return bd.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue() + "M";
	}

	public static double formatDecemail(double x) {
		NumberFormat ddf1 = NumberFormat.getNumberInstance();
		ddf1.setMaximumFractionDigits(2);
		return Double.valueOf(ddf1.format(x));
	}

	public static String formatDate(Date date) {
		if (date == null) {
			return "";
		}
		SimpleDateFormat dateF = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		String dateStr = dateF.format(date);
		return dateStr;
	}

	public static final String DEFAULT_BRANCH = "master";

	public static AndroidPackTask constructContinuousBuildTask(
			AndroidBranchForBuild branch) {
		AndroidPackTask packTask = new AndroidPackTask();
		packTask.setBuildType(BuildType.CONTINUOUS_BUILD.getBuildTypeName());
		packTask.setBarCodeBranch(DEFAULT_BRANCH);
		packTask.setCommonBranch(branch.getBranchName());
		packTask.setMainBranch(branch.getBranchName());
		packTask.setVersionCode(branch.getVersionCode());
		packTask.setVersionName(branch.getVersionName());
		packTask.setPpserver(branch.getPpserverUrl());
		packTask.setStatus(TaskStatus.WAITING.getName());
		packTask.setCreatTime(new Date());
		packTask.setSignature(SignatureType.FORMAL_SIGNATURE.getName());
		packTask.setDoneTime(util.DateUtil.getDateBeforeCurrent(1));
		return packTask;
	}

	public static List<String> getBranchNames(
			List<AndroidBranchForBuild> branchesForBuild) {
		List<String> branchNames = new ArrayList<String>();
		for (AndroidBranchForBuild branchForBuild : branchesForBuild) {
			if (!branchNames.contains(branchForBuild.getBranchName())) {
				branchNames.add(branchForBuild.getBranchName());
			}
		}
		return branchNames;
	}

	public static List<String> getVersionCodes(
			List<AndroidBranchForBuild> branchesForBuild) {
		List<String> versionCodes = new ArrayList<String>();
		for (AndroidBranchForBuild branchForBuild : branchesForBuild) {
			if (StringUtils.isNotEmpty(branchForBuild.getVersionCode()) && !versionCodes.contains(branchForBuild.getVersionCode())) {
				versionCodes.add(branchForBuild.getVersionCode());
			}
		}
		return versionCodes;
	}

	public static List<String> getVersionNames(
			List<AndroidBranchForBuild> branchesForBuild) {
		List<String> versionNames = new ArrayList<String>();
		for (AndroidBranchForBuild branchForBuild : branchesForBuild) {
			if (!versionNames.contains(branchForBuild.getVersionName())) {
				versionNames.add(branchForBuild.getVersionName());
			}
		}
		return versionNames;
	}

	public static List<ReportData> constructTestResult(
			List<AndroidSubTask> subTasks) {
		Map<SubTaskType, Map<String, List<Double>>> taskTypeAndKeyValues = new HashMap<SubTaskType, Map<String, List<Double>>>();
		for (AndroidSubTask subTask : subTasks) {
			Map<String, List<Double>> keyAndValues = new HashMap<String, List<Double>>();
			SubTaskType subTaskType = SubTaskType.findSubTaskTypeById(subTask
					.getTestTypeId());
			switch (subTaskType) {
			case TEST_CPU:
				TaskConvertor.addToTestDataMap(
						dao.findCPUTestDataBySubTaskId(subTask.getSubTaskId()),
						keyAndValues);
				break;
			case TEST_MEMORY:
				TaskConvertor.addToTestDataMap2(dao
						.findMemoryTestDataBySubTaskId(subTask.getSubTaskId()),
						keyAndValues);
				break;
			default:
				break;
			}
			if (!keyAndValues.isEmpty()) {
				taskTypeAndKeyValues.put(subTaskType, keyAndValues);
			}
		}
		List<ReportData> reportDatas = new ArrayList<ReportData>();
		for (SubTaskType taskType : taskTypeAndKeyValues.keySet()) {
			Map<String, List<Double>> keyAndValues = taskTypeAndKeyValues
					.get(taskType);
			ReportData reportData = new ReportData(taskType);
			reportDatas.add(reportData);
			for (String process : keyAndValues.keySet()) {
				TaskResult taskResult = new TaskResult(process,
						keyAndValues.get(process));
				reportData.addToTaskResults(taskResult);
			}
		}
		return reportDatas;
	}

	public static List<MonkeyTestResult> convertToMonkeyTestResult(
			List<AndroidSubTask> subTasks) {
		List<AndroidTestMonkey> monkeyTestResults = dao
				.findMonkeyTestDataBySubTaskId(StringUtil
						.getAttributeList(subTasks));
		if(monkeyTestResults==null || monkeyTestResults.size()==0){
			return null;
		}
		List<MonkeyTestResult> results = new ArrayList<MonkeyTestResult>();
		Map<String, MonkeyTestResult> errorMd5AndResultMap = new HashMap<String, MonkeyTestResult>();
		
		for (AndroidTestMonkey testMonkey : monkeyTestResults) {
			MonkeyTestResult result = errorMd5AndResultMap.get(testMonkey
					.getMd5());
			if (result == null) {
				result = convertToMonkeyTestResult(testMonkey);
			}
			result.increaseErrorCount();
			result.addDeviceInfo(testMonkey.getDeviceInfo());
			errorMd5AndResultMap.put(testMonkey
					.getMd5(), result);
		}
		
		for (String errorMD5 : errorMd5AndResultMap.keySet()) {
			results.add(errorMd5AndResultMap.get(errorMD5));
		}
		return results;
	}
	
	public static MonkeyTestResult convertToMonkeyTestResult(AndroidTestMonkey testMonkey){
		MonkeyTestResult result = new MonkeyTestResult();
		result.setErrorBreif(testMonkey.getErrorInfo().split("\n")[0]);
		result.setErrorDetails(testMonkey.getErrorInfo());
		result.setSubTaskId(testMonkey.getSubTaskId());
		result.setErrorMD5(testMonkey.getMd5());
		return result;
	}

	public static TaskStatus getExpectedStatus(AndroidPackTask p,
			List<AndroidSubTask> subTasks) {
		Map<TaskStatus, Integer> statusAndTaskCount = new HashMap<TaskStatus, Integer>();
		int taskCount = 0;
		if (p != null) {
			addToTaskStatusMap(statusAndTaskCount,
					TaskStatus.findTaskStatusByStatusName(p.getStatus()));
			taskCount++;
		}
		if (subTasks != null) {
			for (AndroidSubTask subTask : subTasks) {
				addToTaskStatusMap(statusAndTaskCount,
						TaskStatus.findTaskStatusByStatusName(subTask
								.getStatus()));
				taskCount++;
			}
		}
		if (taskCount == 0) {
			return null;
		}
		return getExpectedStatus(statusAndTaskCount, taskCount);
	}

	private static TaskStatus getExpectedStatus(
			Map<TaskStatus, Integer> statusAndTaskCount, int taskCount) {
		TaskStatus expectedStatus = null;
		if (getActualCount(statusAndTaskCount, TaskStatus.SUCCESS) == taskCount) {
			expectedStatus = TaskStatus.SUCCESS;
		} else if (getActualCount(statusAndTaskCount, TaskStatus.WAITING) == taskCount) {
			expectedStatus = TaskStatus.WAITING;
		} else if (getActualCount(statusAndTaskCount, TaskStatus.FAILED) > 0) {
			expectedStatus = TaskStatus.FAILED;
		} else {
			expectedStatus = TaskStatus.PROCESSING;
		}
		return expectedStatus;
	}

	private static Integer getActualCount(
			Map<TaskStatus, Integer> statusAndTaskCount, TaskStatus status) {
		if (statusAndTaskCount.get(status) == null) {
			return 0;
		}
		return statusAndTaskCount.get(status);
	}

	private static void addToTaskStatusMap(
			Map<TaskStatus, Integer> statusAndCount, TaskStatus taskStatus) {
		Integer count = statusAndCount.get(taskStatus);
		if (count == null) {
			count = 0;
		}
		count += 1;
		statusAndCount.put(taskStatus, count);
	}

	public static List<ReportData> constructSubTaskJobUrls(
			AndroidPackTask packTask, List<AndroidSubTask> subTasks) {
		List<ReportData> reportDatas = new ArrayList<ReportData>();
		if (packTask != null && StringUtils.isNotEmpty(packTask.getJobUrl())) {
			reportDatas.add(new ReportData(SubTaskType.PACK, packTask
					.getJobUrl()));
		}
		if (subTasks != null) {
			for (AndroidSubTask subTask : subTasks) {
				if (subTask != null && StringUtils.isNotEmpty(subTask.getUrl())) {
					reportDatas.add(new ReportData(SubTaskType
							.findSubTaskTypeById(subTask.getTestTypeId()),
							subTask.getUrl()));
				}
			}
		}
		return reportDatas;
	}
}
