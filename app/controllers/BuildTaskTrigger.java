package controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import enums.PackageAPKPostFix;
import models.PlatformDao;
import models.po.AndroidMainTask;
import models.po.AndroidPackTask;
import models.po.AndroidSubTask;
import models.po.AndroidTestType;
import models.po.AndroidTestTypeDetail;
import play.Logger;

public class BuildTaskTrigger {

	private static PlatformDao dao = new PlatformDao();
	
	//一种类型可能有多个构建的Url，例如Monkey，在表android_test_type_detail中配置即可
	private static Map<Integer, List<String>> typeIdAndBuildUrlMap;

	{
		if (typeIdAndBuildUrlMap == null) {
			typeIdAndBuildUrlMap = new HashMap<Integer, List<String>>();
			for (AndroidTestType testType : dao
					.listAutomationTestTypes()) {
				int testTypeId = testType.getTypeId();
				List<AndroidTestTypeDetail> testTypeDetails = dao.listTestTypeDetail(testTypeId);
				List<String> requestUrls = typeIdAndBuildUrlMap.get(testTypeId);
				if(requestUrls ==null ){
					requestUrls = new ArrayList<String>();
				}
				for(AndroidTestTypeDetail testTypeDetail : testTypeDetails){
					requestUrls.add(testTypeDetail.getRequestUrl());
				}
				typeIdAndBuildUrlMap.put(testType.getTypeId(),
						requestUrls);
			}

		}

	}
	
	public  void runCMD(String cmdString) {
		Logger.info("start to run cmd:" + cmdString);
		String[] commands = new String[]{"/bin/sh","-c",cmdString};   
		try {
			Process process = Runtime.getRuntime().exec(commands);
			try {
				process.waitFor();
			} catch (InterruptedException e) {
				Logger.error("execute cmd:" + cmdString + " failed!");
			}
		} catch (IOException e) {
			e.printStackTrace();
			Logger.error("execute cmd:" + cmdString + " failed!");
		}
	}


	public void triggerPackTask(int packTaskId,boolean proguardResFiles) {
		Logger.info("will trigger pack task url:" + typeIdAndBuildUrlMap.get(0) + "packtaskId:" + packTaskId);
		runCMD(typeIdAndBuildUrlMap.get(0).get(0).replace("$param$",
				String.valueOf(packTaskId)).replace("$proguard_resfiles$", String.valueOf(proguardResFiles)));
	}

	public void triggerMappingTask(String mappingPath,String version){
		//TODO:
		
		runCMD(typeIdAndBuildUrlMap.get(7).get(0).replace("$mappingUrl$",
				String.valueOf(mappingPath)).replace("$ver$", String.valueOf(version)));
	}
	
	public void triggerAutomationJobs(int packTaskId) {
		AndroidPackTask packTask = AndroidPackTask.findById(packTaskId);
		String packUrl = packTask.getUrl() + PackageAPKPostFix.debugRelease.getPostfix();
		AndroidMainTask mainTask = packTask.getMainTask();
		List<AndroidSubTask> subTasks = mainTask.getSubTasks();
		for (AndroidSubTask subTask : subTasks) {
			for (String requestUrl : typeIdAndBuildUrlMap.get(subTask
					.getTestTypeId())) {
				String buildJobUrl = requestUrl
						.replace("$param$",String.valueOf(subTask.getSubTaskId()))
						.replace("$sub_task_id$",String.valueOf(subTask.getSubTaskId()))
						.replace("$package_url$", packUrl);
				runCMD(buildJobUrl);
			}
		}
	}

}
