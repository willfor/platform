package models.po;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import play.db.jpa.GenericModel;

@Entity
@Table(name = "android_test_cpu")
public class AndroidTestCPU extends GenericModel {

	@Column(name = "cpu_test_id")
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int cpuTestId;

	@Column(name = "sub_task_id")
	private int subTaskId;

	@Column(name = "cpu_percentage")
	private String cpuPercentage;

	@Column(name = "process")
	private String process;

	@Column(name = "test_case_id")
	private int testCaseId;

	public int getCpuTestId() {
		return cpuTestId;
	}

	public void setCpuTestId(int cpuTestId) {
		this.cpuTestId = cpuTestId;
	}

	public int getSubTaskId() {
		return subTaskId;
	}

	public void setSubTaskId(int subTaskId) {
		this.subTaskId = subTaskId;
	}

	public String getCpuPercentage() {
		return cpuPercentage;
	}

	public void setCpuPercentage(String cpuPercentage) {
		this.cpuPercentage = cpuPercentage;
	}

	public String getProcess() {
		return process;
	}

	public void setProcess(String process) {
		this.process = process;
	}

	public int getTestCaseId() {
		return testCaseId;
	}

	public void setTestCaseId(int testCaseId) {
		this.testCaseId = testCaseId;
	}

}
