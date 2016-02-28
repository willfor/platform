package models.vo;

import java.util.Date;

public class BranchForBuildInfo {
	private int branchId;

	private String description;

	private String branchName;

	private String versionName;

	private String versionCode;

	private String status;

	private String ppserverUrl;

	private Date createTime;

	private Date updateTime;

	private Date lastBuildTime;

	private int packTaskId;

	private String statusDescription;

	public String getPpserverUrl() {
		return ppserverUrl;
	}

	public void setPpserverUrl(String ppserverUrl) {
		this.ppserverUrl = ppserverUrl;
	}

	public void setCreateTimeToNow() {
		this.createTime = new Date();
	}

	public void setUpdateTimeToNow() {
		this.updateTime = new Date();
	}

	public void setLastBuildTimeToNow() {
		this.lastBuildTime = new Date();
	}

	public int getBranchId() {
		return branchId;
	}

	public void setBranchId(int branchId) {
		this.branchId = branchId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getBranchName() {
		return branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	public String getVersionName() {
		return versionName;
	}

	public void setVersionName(String versionName) {
		this.versionName = versionName;
	}

	public String getVersionCode() {
		return versionCode;
	}

	public void setVersionCode(String versionCode) {
		this.versionCode = versionCode;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public void setToEnabledStatus() {
		this.status = "enabled";
	}

	public Date getLastBuildTime() {
		return lastBuildTime;
	}

	public void setLastBuildTime(Date lastBuildTime) {
		this.lastBuildTime = lastBuildTime;
	}

	public int getPackTaskId() {
		return packTaskId;
	}

	public void setPackTaskId(int packTaskId) {
		this.packTaskId = packTaskId;
	}

	public String getStatusDescription() {
		return statusDescription;
	}

	public void setStatusDescription(String statusDescription) {
		this.statusDescription = statusDescription;
	}

}
