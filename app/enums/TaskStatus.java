package enums;

import play.Logger;

public enum TaskStatus {
	WAITING("waiting", 0, "等待中", 0), 
	PROCESSING("processing", 2, "执行中", 0.3), 
	FAILED("failed", 3, "执行失败", 1), 
	SUCCESS("success", 4, "已完成", 1);

	private String name;
	private int statusId;
	private String description;
	private double percentage;

	public boolean isEndStatus() {
		return this == FAILED || this == SUCCESS;
	}

	public static String findTaskStatusDescriptionByName(String statusName) {
		Logger.info("statusName:" + statusName);
		return findTaskStatusByStatusName(statusName).getDescription();
	}

	public static TaskStatus findTaskStatusByStatusName(String statusName) {
		for (TaskStatus status : TaskStatus.values()) {
			if (status.getName().equals(statusName)) {
				return status;
			}
		}
		return null;
	}

	private TaskStatus(String name, int statusId, String description,
			double percentage) {
		this.name = name;
		this.statusId = statusId;
		this.description = description;
		this.percentage = percentage;
	}

	public static boolean isSucess(String status) {
		return TaskStatus.SUCCESS.getName().equals(status);
	}
	
	public boolean isFailed(){
		return TaskStatus.FAILED ==this;
	}
	
	public boolean isProcessing(){
		return TaskStatus.PROCESSING == this;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getStatusId() {
		return statusId;
	}

	public void setStatusId(int statusId) {
		this.statusId = statusId;
	}

	public String getStatusIdStr() {
		return String.valueOf(statusId);
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getPercentage() {
		return percentage;
	}

	public void setPercentage(double percentage) {
		this.percentage = percentage;
	}

}
