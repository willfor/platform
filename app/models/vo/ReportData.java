package models.vo;

import java.util.ArrayList;
import java.util.List;

import enums.SubTaskType;

public class ReportData {
	private String subTaskType;
	private String subTaskTypeDescription;
	private double rangeFrom;
	private double rangeTo;
	private double interval;
	private String jobUrl;

	private List<TaskResult> taskResults;

	public ReportData(SubTaskType taskType) {
		this.subTaskType = taskType.getName();
		this.subTaskTypeDescription = taskType.getDescription();
		this.rangeFrom = taskType.getRangeFrom();
		this.rangeTo = taskType.getRangeTo();
		this.interval = taskType.getInterval();
	}

	public String getSubTaskType() {
		return subTaskType;
	}

	public void setSubTaskType(String subTaskType) {
		this.subTaskType = subTaskType;
	}

	public String getSubTaskTypeDescription() {
		return subTaskTypeDescription;
	}

	public void setSubTaskTypeDescription(String subTaskTypeDescription) {
		this.subTaskTypeDescription = subTaskTypeDescription;
	}

	public List<TaskResult> getTaskResults() {
		return taskResults;
	}

	public void setTaskResults(List<TaskResult> taskResults) {
		this.taskResults = taskResults;
	}

	public void addToTaskResults(TaskResult taskResult) {
		if (taskResults == null) {
			taskResults = new ArrayList<TaskResult>();
		}
		taskResults.add(taskResult);
	}

	public double getRangeFrom() {
		return rangeFrom;
	}

	public void setRangeFrom(double rangeFrom) {
		this.rangeFrom = rangeFrom;
	}

	public double getRangeTo() {
		return rangeTo;
	}

	public void setRangeTo(double rangeTo) {
		this.rangeTo = rangeTo;
	}

	public double getInterval() {
		return interval;
	}

	public void setInterval(double interval) {
		this.interval = interval;
	}

	public String getJobUrl() {
		return jobUrl;
	}

	public void setJobUrl(String jobUrl) {
		this.jobUrl = jobUrl;
	}
	
	public ReportData(SubTaskType taskType,String jobUrl){
		this.subTaskType = taskType.getName();
		this.subTaskTypeDescription = taskType.getDescription();
		this.jobUrl = jobUrl;
	}

}
