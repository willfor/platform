package enums;

public enum PackageAPKPostFix {

	debug("-debug.apk"),
	release("-release.apk"),
	debugRelease("Debug-release.apk");
	
	private String postfix;
	
	private PackageAPKPostFix(String postfix){
		this.postfix = postfix;
	}
	public String getPostfix() {
		return postfix;
	}
	public void setPostfix(String postfix) {
		this.postfix = postfix;
	}
	
	
}
