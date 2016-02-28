package enums;

public enum PackageMappingPostfix {

	debug("-debugmapping.zip"), 
	release("-releasemapping.zip");

	private String postfix;

	private PackageMappingPostfix(String postfix) {
		this.postfix = postfix;
	}

	public String getPostfix() {
		return postfix;
	}

	public void setPostfix(String postfix) {
		this.postfix = postfix;
	}

}
