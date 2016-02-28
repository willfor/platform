package models.vo;

import java.util.ArrayList;
import java.util.List;

public class PackInfoForDisplay {
	private String status;
	private String statusDescription;
	private String versionCode;
	private String packTaskId;
	private String mainTaskStatus;
	private String mainTaskUrl;
	private String buildType;
	private String mainBranch;
	private String mainTag;
	private String commonBranch;
	private String commonTag;
	private String barCodeBranch;
	private String barCodeTag;
	private String apkName;
	private String ppserverUrl;
	private String versionName;
	private String channel;
	private String createTime;
	private String doneTime;
	private String signature;
	private String mainTaskId;
	private String url;
	private List<TaskStatusInfo> taskStatusInfos;
	private double statusFinishPercentage;
	private boolean onlyHavePackTask;
	private String debugMappingUrl;
	private String releaseMappingUrl;
	private String releaseMappingFilePath;
	private String releaseMD5;
	private String fileSize;
	private String timestamp;
	private String apkDebugReleaseUrl;
	private String apkReleaseUrl;
	private long doneTimestamp;
	

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getVersionCode() {
		return versionCode;
	}

	public void setVersionCode(String versionCode) {
		this.versionCode = versionCode;
	}

	public String getPackTaskId() {
		return packTaskId;
	}

	public void setPackTaskId(String packTaskId) {
		this.packTaskId = packTaskId;
	}

	public String getMainTaskStatus() {
		return mainTaskStatus;
	}

	public void setMainTaskStatus(String mainTaskStatus) {
		this.mainTaskStatus = mainTaskStatus;
	}

	public String getMainTaskUrl() {
		return mainTaskUrl;
	}

	public void setMainTaskUrl(String mainTaskUrl) {
		this.mainTaskUrl = mainTaskUrl;
	}

	public String getBuildType() {
		return buildType;
	}

	public void setBuildType(String buildType) {
		this.buildType = buildType;
	}

	public String getMainBranch() {
		return mainBranch;
	}

	public void setMainBranch(String mainBranch) {
		this.mainBranch = mainBranch;
	}

	public String getMainTag() {
		return mainTag;
	}

	public void setMainTag(String mainTag) {
		this.mainTag = mainTag;
	}

	public String getCommonBranch() {
		return commonBranch;
	}

	public void setCommonBranch(String commonBranch) {
		this.commonBranch = commonBranch;
	}

	public String getCommonTag() {
		return commonTag;
	}

	public void setCommonTag(String commonTag) {
		this.commonTag = commonTag;
	}

	public String getBarCodeBranch() {
		return barCodeBranch;
	}

	public void setBarCodeBranch(String barCodeBranch) {
		this.barCodeBranch = barCodeBranch;
	}

	public String getBarCodeTag() {
		return barCodeTag;
	}

	public void setBarCodeTag(String barCodeTag) {
		this.barCodeTag = barCodeTag;
	}

	public String getApkName() {
		return apkName;
	}

	public void setApkName(String apkName) {
		this.apkName = apkName;
	}

	public String getPpserverUrl() {
		return ppserverUrl;
	}

	public void setPpserverUrl(String ppserverUrl) {
		this.ppserverUrl = ppserverUrl;
	}

	public String getVersionName() {
		return versionName;
	}

	public void setVersionName(String versionName) {
		this.versionName = versionName;
	}

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getDoneTime() {
		return doneTime;
	}

	public void setDoneTime(String doneTime) {
		this.doneTime = doneTime;
	}

	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}

	public String getMainTaskId() {
		return mainTaskId;
	}

	public void setMainTaskId(String mainTaskId) {
		this.mainTaskId = mainTaskId;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getStatusDescription() {
		return statusDescription;
	}

	public void setStatusDescription(String statusDescription) {
		this.statusDescription = statusDescription;
	}

	public List<TaskStatusInfo> getTaskStatusInfos() {
		return taskStatusInfos;
	}

	public void setTaskStatusInfos(List<TaskStatusInfo> taskStatusInfos) {
		this.taskStatusInfos = taskStatusInfos;
	}

	public void addToTaskStatusInfos(TaskStatusInfo statusInfo) {
		if (taskStatusInfos == null) {
			taskStatusInfos = new ArrayList<TaskStatusInfo>();
		}
		taskStatusInfos.add(statusInfo);
	}

	public double getStatusFinishPercentage() {
		return statusFinishPercentage;
	}

	public void setStatusFinishPercentage(double statusFinishPercentage) {
		this.statusFinishPercentage = statusFinishPercentage;
	}

	public boolean isOnlyHavePackTask() {
		return onlyHavePackTask;
	}

	public void setOnlyHavePackTask(boolean onlyHavePackTask) {
		this.onlyHavePackTask = onlyHavePackTask;
	}

	public String getDebugMappingUrl() {
		return debugMappingUrl;
	}

	public void setDebugMappingUrl(String debugMappingUrl) {
		this.debugMappingUrl = debugMappingUrl;
	}

	public String getReleaseMappingUrl() {
		return releaseMappingUrl;
	}

	public void setReleaseMappingUrl(String releaseMappingUrl) {
		this.releaseMappingUrl = releaseMappingUrl;
	}

	public String getReleaseMD5() {
		return releaseMD5;
	}

	public void setReleaseMD5(String releaseMD5) {
		this.releaseMD5 = releaseMD5;
	}

	public String getFileSize() {
		return fileSize;
	}

	public void setFileSize(String fileSize) {
		this.fileSize = fileSize;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public String getApkDebugReleaseUrl() {
		return apkDebugReleaseUrl;
	}

	public void setApkDebugReleaseUrl(String apkDebugReleaseUrl) {
		this.apkDebugReleaseUrl = apkDebugReleaseUrl;
	}

	public String getApkReleaseUrl() {
		return apkReleaseUrl;
	}

	public void setApkReleaseUrl(String apkReleaseUrl) {
		this.apkReleaseUrl = apkReleaseUrl;
	}

	public long getDoneTimestamp() {
		return doneTimestamp;
	}

	public void setDoneTimestamp(long doneTimestamp) {
		this.doneTimestamp = doneTimestamp;
	}

	public String getReleaseMappingFilePath() {
		return releaseMappingFilePath;
	}

	public void setReleaseMappingFilePath(String releaseMappingFilePath) {
		this.releaseMappingFilePath = releaseMappingFilePath;
	}

}
