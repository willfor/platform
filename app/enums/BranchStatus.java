package enums;

public enum BranchStatus {

	ENABLED("enabled", "已启用", 1), DISABLED("disabled", "已禁用", 0);

	private String statusName;
	private String description;
	private int value;

	private BranchStatus(String statusName, String description, int value) {
		this.statusName = statusName;
		this.description = description;
		this.value = value;
	}

	public static String getBranchStatusDescriptionByValue(String statusName) {
		return findByStatusName(statusName).getDescription();
	}

	public static BranchStatus findByStatusName(String statusName) {
		for (BranchStatus branchStatus : BranchStatus.values()) {
			if (branchStatus.statusName.equals(statusName)) {
				return branchStatus;
			}
		}
		return null;
	}

	public static boolean isEnable(String statusName) {
		return ENABLED.getStatusName().equals(statusName);
	}

	public static boolean isDisable(String statusName) {
		return DISABLED.getStatusName().equals(statusName);
	}

	public String getStatusName() {
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

}
