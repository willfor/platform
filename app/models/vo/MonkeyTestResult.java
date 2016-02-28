package models.vo;

import java.util.ArrayList;
import java.util.List;

public class MonkeyTestResult {
	private int subTaskId;
	private String errorMD5;
	private String errorBreif;
	private String errorDetails;
	private int errorCount = 0;
	private List<String> deviceInfo;

	public int getSubTaskId() {
		return subTaskId;
	}

	public void setSubTaskId(int subTaskId) {
		this.subTaskId = subTaskId;
	}

	public String getErrorMD5() {
		return errorMD5;
	}

	public void setErrorMD5(String errorMD5) {
		this.errorMD5 = errorMD5;
	}

	public String getErrorBreif() {
		return errorBreif;
	}

	public void setErrorBreif(String errorBreif) {
		this.errorBreif = errorBreif;
	}

	public String getErrorDetails() {
		return errorDetails;
	}

	public void setErrorDetails(String errorDetails) {
		this.errorDetails = errorDetails;
	}

	public int getErrorCount() {
		return errorCount;
	}

	public void setErrorCount(int errorCount) {
		this.errorCount = errorCount;
	}

	public void increaseErrorCount() {
		this.errorCount += 1;
	}

	public void addDeviceInfo(String device) {
		if (deviceInfo == null) {
			deviceInfo = new ArrayList<String>();
		}
		if (!deviceInfo.contains(device)) {
			deviceInfo.add(device);
		}
	}

	public List<String> getDeviceInfo() {
		return deviceInfo;
	}

	public void setDeviceInfo(List<String> deviceInfo) {
		this.deviceInfo = deviceInfo;
	}

}
