package models.po;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import play.db.jpa.GenericModel;

@Entity
@Table(name = "android_test_monkey")
public class AndroidTestMonkey extends GenericModel {
	public static final String NORMAL = "no error";

	@Column(name = "monkey_test_id")
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int monkeyTestId;

	@Column(name = "sub_task_id")
	private int subTaskId;

	@Column(name = "devicesinfo")
	private String deviceInfo;

	@Column(name = "errorInfo")
	private String errorInfo;

	@Column(name = "md5")
	private String md5;

	@Column(name = "mantisId")
	private String mantisId;

	public int getMonkeyTestId() {
		return monkeyTestId;
	}

	public void setMonkeyTestId(int monkeyTestId) {
		this.monkeyTestId = monkeyTestId;
	}

	public int getSubTaskId() {
		return subTaskId;
	}

	public void setSubTaskId(int subTaskId) {
		this.subTaskId = subTaskId;
	}

	public String getDeviceInfo() {
		return deviceInfo;
	}

	public void setDeviceInfo(String deviceInfo) {
		this.deviceInfo = deviceInfo;
	}

	public String getErrorInfo() {
		return errorInfo;
	}

	public void setErrorInfo(String errorInfo) {
		this.errorInfo = errorInfo;
	}

	public String getMd5() {
		return md5;
	}

	public void setMd5(String md5) {
		this.md5 = md5;
	}

	public String getMantisId() {
		return mantisId;
	}

	public void setMantisId(String mantisId) {
		this.mantisId = mantisId;
	}

}
