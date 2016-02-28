package enums;

public enum BuildType {

	MANUAL_BUILD(1,"manual_build", "单次构建"), 
	CONTINUOUS_BUILD(0,"continuous_build", "持续构建");

	private int buildType;

	private String buildTypeName;

	private String buildTypeDescription;

	private BuildType(int buildType, String buildTypeName,
			String buildTypeDescription) {
		this.buildType = buildType;
		this.buildTypeName = buildTypeName;
		this.buildTypeDescription = buildTypeDescription;
	}
	
	public static String getBuildTypeNameByName(String buildTypeName){
		return getBuildTypeByName(buildTypeName).getBuildTypeName();
	}

	public static BuildType getBuildTypeByName(String buildTypeName) {
		BuildType expectedBuildType = null;
		for (BuildType buildType : BuildType.values()) {
			if (buildType.getBuildTypeName().equals(buildTypeName)) {
				expectedBuildType =  buildType;
				break;
			}
		}
		if(expectedBuildType ==null){
			throw new UnsupportedOperationException("buildTypeName not as expected!");
		}
		return expectedBuildType;
	}

	public int getBuildType() {
		return buildType;
	}

	public void setBuildType(int buildType) {
		this.buildType = buildType;
	}

	public String getBuildTypeName() {
		return buildTypeName;
	}

	public void setBuildTypeName(String buildTypeName) {
		this.buildTypeName = buildTypeName;
	}

	public String getBuildTypeDescription() {
		return buildTypeDescription;
	}

	public void setBuildTypeDescription(String buildTypeDescription) {
		this.buildTypeDescription = buildTypeDescription;
	}

}
