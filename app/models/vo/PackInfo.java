package models.vo;

import java.util.List;

public class PackInfo {
	private String depBranch;
	private String depTag;
	private String commonBranch;
	private String commonTag;
	private String barCodeBranch;
	private String barCodeTag;

	private String versionName;
	private String versionCode;

	private String ppserverUrl;

	private String channel;
	private String signature;
	private String buildType;
	
	private List<String> testTypes;
	private String agooConfigure;
	
	private boolean proguardResourceFiles;

	public String getDepBranch() {
		return depBranch;
	}

	public void setDepBranch(String depBranch) {
		this.depBranch = depBranch;
	}

	public String getCommonBranch() {
		return commonBranch;
	}

	public void setCommonBranch(String commonBranch) {
		this.commonBranch = commonBranch;
	}

	public String getBarCodeBranch() {
		return barCodeBranch;
	}

	public void setBarCodeBranch(String barCodeBranch) {
		this.barCodeBranch = barCodeBranch;
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

	public String getPpserverUrl() {
		return ppserverUrl;
	}

	public void setPpserverUrl(String ppserverUrl) {
		this.ppserverUrl = ppserverUrl;
	}

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}

	public String getBuildType() {
		return buildType;
	}

	public void setBuildType(String buildType) {
		this.buildType = buildType;
	}

	public String getDepTag() {
		return depTag;
	}

	public void setDepTag(String depTag) {
		this.depTag = depTag;
	}

	public String getCommonTag() {
		return commonTag;
	}

	public void setCommonTag(String commonTag) {
		this.commonTag = commonTag;
	}

	public String getBarCodeTag() {
		return barCodeTag;
	}

	public void setBarCodeTag(String barCodeTag) {
		this.barCodeTag = barCodeTag;
	}

	public List<String> getTestTypes() {
		return testTypes;
	}

	public void setTestTypes(List<String> testTypes) {
		this.testTypes = testTypes;
	}

	public String getAgooConfigure() {
		return agooConfigure;
	}

	public void setAgooConfigure(String agooConfigure) {
		this.agooConfigure = agooConfigure;
	}

	public boolean isProguardResourceFiles() {
		return proguardResourceFiles;
	}

	public void setProguardResourceFiles(boolean proguardResourceFiles) {
		this.proguardResourceFiles = proguardResourceFiles;
	}

}
