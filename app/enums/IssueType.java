package enums;

public enum IssueType {

	MEMORY_LEAK("memoryLeak", "内存泄漏");

	private String name;
	private String mantisCategory;

	private IssueType(String name, String mantisCategory) {
		this.name = name;
		this.mantisCategory = mantisCategory;
	}

	public static IssueType find(String typeName) {
		IssueType expectedIssueType = null;
		for (IssueType issueType : IssueType.values()) {
			if (issueType.getName().equals(typeName)) {
				expectedIssueType = issueType;
				break;
			}
		}

		if (expectedIssueType == null) {
			throw new UnsupportedOperationException("不支持type:" + typeName
					+ ", 请检查是否正确！");
		}
		return expectedIssueType;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMantisCategory() {
		return mantisCategory;
	}

	public void setMantisCategory(String mantisCategory) {
		this.mantisCategory = mantisCategory;
	}

}
