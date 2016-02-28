package models.po;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import play.data.binding.As;
import play.db.jpa.GenericModel;

@Entity
@Table(name = "android_pack_task")
public class AndroidPackTask extends GenericModel {

	@Column(name = "task_id")
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int taskId;

	@OneToOne(targetEntity = AndroidMainTask.class)
	@JoinColumn(name = "main_task_id")
	private AndroidMainTask mainTask;

	@Column(name = "main_repository_branch")
	private String mainBranch;

	@Column(name = "main_repository_tag")
	private String mainTag;

	@Column(name = "common_repository_branch")
	private String commonBranch;

	@Column(name = "common_repository_tag")
	private String commonTag;

	@Column(name = "bar_code_repository_branch")
	private String barCodeBranch;

	@Column(name = "bar_code_repository_tag")
	private String barCodeTag;

	@Column(name = "version_name")
	private String versionName;

	@Column(name = "version_code")
	private String versionCode;

	@Column(name = "pp_server_url")
	private String ppserver;

	@Column(name = "channel")
	private String channel;

	@Column(name = "apk_url")
	private String url;

	@Column(name = "create_time")
	private Date creatTime;

	@Column(name = "done_time")
	private Date doneTime;

	@Column(name = "pack_task_status")
	private String status;

	@Column(name = "build_type")
	private String buildType;

	@Column(name = "apk_name")
	private String apkName;

	@Column(name = "signature")
	private String signature;

	@Column(name = "job_url")
	private String jobUrl;

	@Column(name = "agoo_key")
	private String agooKey;

	@Column(name = "agoo_secret")
	private String agooSecret;

	@Column(name = "release_apk_md5")
	private String apkMD5;

	@Column(name = "apk_byte_size")
	private String apkByteSize;

	public AndroidMainTask getMainTask() {
		return mainTask;
	}

	public void setMainTask(AndroidMainTask mainTask) {
		this.mainTask = mainTask;
	}

	public int getTaskId() {
		return taskId;
	}

	public void setTaskId(int taskId) {
		this.taskId = taskId;
	}

	public String getPpserver() {
		return ppserver;
	}

	public void setPpserver(String ppserver) {
		this.ppserver = ppserver;
	}

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Date getCreatTime() {
		return creatTime;
	}

	@As("yyyy-MM-dd HH:mm:ss")
	public void setCreatTime(Date creatTime) {
		this.creatTime = creatTime;
	}

	public Date getDoneTime() {
		return doneTime;
	}

	@As("yyyy-MM-dd HH:mm:ss")
	public void setDoneTime(Date doneTime) {
		this.doneTime = doneTime;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getApkName() {
		return apkName;
	}

	public void setApkName(String apkName) {
		this.apkName = apkName;
	}

	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
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

	public String getBuildType() {
		return buildType;
	}

	public void setBuildType(String buildType) {
		this.buildType = buildType;
	}

	public String getJobUrl() {
		return jobUrl;
	}

	public void setJobUrl(String jobUrl) {
		this.jobUrl = jobUrl;
	}

	public String getAgooKey() {
		return agooKey;
	}

	public void setAgooKey(String agooKey) {
		this.agooKey = agooKey;
	}

	public String getAgooSecret() {
		return agooSecret;
	}

	public void setAgooSecret(String agooSecret) {
		this.agooSecret = agooSecret;
	}

	public String getApkMD5() {
		return apkMD5;
	}

	public void setApkMD5(String apkMD5) {
		this.apkMD5 = apkMD5;
	}

	public String getApkByteSize() {
		return apkByteSize;
	}

	public void setApkByteSize(String apkByteSize) {
		this.apkByteSize = apkByteSize;
	}

}
