package models.vo;

import enums.SubTaskType;
import enums.TaskStatus;

public class TaskStatusInfo {
	private int taskId;
	private String taskName;
	private String taskStatus;
	private String taskStatusForDisplay;

	public TaskStatusInfo(int taskId, SubTaskType taskType, TaskStatus taskStatus) {
		this.taskId = taskId;
		this.taskName = taskType.getDescription();
		if (taskStatus != null) {
			this.taskStatus = taskStatus.getName();
			this.taskStatusForDisplay = taskStatus.getDescription();
		}
	}
	
	

	public int getTaskId() {
		return taskId;
	}



	public void setTaskId(int taskId) {
		this.taskId = taskId;
	}



	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public String getTaskStatus() {
		return taskStatus;
	}

	public void setTaskStatus(String taskStatus) {
		this.taskStatus = taskStatus;
	}

	public String getTaskStatusForDisplay() {
		return taskStatusForDisplay;
	}

	public void setTaskStatusForDisplay(String taskStatusForDisplay) {
		this.taskStatusForDisplay = taskStatusForDisplay;
	}

}
