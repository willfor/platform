package models.po;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import play.db.jpa.GenericModel;

@Entity
@Table(name = "android_test_memory")
public class AndroidTestMemory extends GenericModel{

	@Column(name = "memory_test_id")
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int memoryTestId;

	@Column(name = "sub_task_id")
	private int subTaskId;

	@Column(name = "memory")
	private String memory;

	@Column(name = "process")
	private String process;

	@Column(name = "test_case_id")
	private int testCaseId;

	public int getMemoryTestId() {
		return memoryTestId;
	}

	public void setMemoryTestId(int memoryTestId) {
		this.memoryTestId = memoryTestId;
	}

	public int getSubTaskId() {
		return subTaskId;
	}

	public void setSubTaskId(int subTaskId) {
		this.subTaskId = subTaskId;
	}

	public String getMemory() {
		return memory;
	}

	public void setMemory(String memory) {
		this.memory = memory;
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
