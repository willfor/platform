package enums;

public enum MantisMethodName {
	GET_BUG_INFO("get_bug_info"),
	UPDATE_BUG_INFO("update_bug_info"),
	INSERT_NEW_BUG("custom_report_bug");
	
	private String name;
	
	private MantisMethodName(String name){
		this.name = name;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	

	

}
