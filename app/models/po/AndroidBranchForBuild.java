package models.po;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import play.db.jpa.GenericModel;

@Entity
@Table(name = "android_branch_for_build")
public class AndroidBranchForBuild extends GenericModel {

	@Column(name = "branch_Id")
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int branchId;

	@Column(name = "description")
	private String description;

	@Column(name = "branch_name")
	private String branchName;

	@Column(name = "version_name")
	private String versionName;

	@Column(name = "version_code")
	private String versionCode;

	@Column(name = "branch_status")
	private String status;

	@Column(name = "server_url")
	private String ppserverUrl;

	@Column(name = "create_time")
	private Date createTime;

	@Column(name = "update_time")
	private Date updateTime;

	@Column(name = "last_build_time")
	private Date lastBuildTime;

	@Column(name = "pack_task_id")
	private int packTaskId;

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
	
	
	

}
