package models.po;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import play.db.jpa.GenericModel;

@Entity
@Table(name = "android_sub_task")
public class AndroidSubTask extends GenericModel {

	@ManyToOne(cascade = { CascadeType.REFRESH }, optional = true, fetch = FetchType.EAGER)
	@Fetch(FetchMode.SELECT)
	@NotFound(action = NotFoundAction.IGNORE)
	@JoinColumn(name = "main_task_id")
	private AndroidMainTask mainTask;

	@Column(name = "test_type_id")
	private int testTypeId = 1;

	@Column(name = "sub_task_id")
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int subTaskId;

	@Column(name = "url")
	private String url;

	@Column(name = "result")
	private String result;

	@Column(name = "creatTime")
	private Date createTime;

	@Column(name = "endTime")
	private Date endTime;

	@Column(name = "device_id")
	private int deviceId;

	@Column(name = "sub_task_status")
	private String status;

	public int getTestTypeId() {
		return testTypeId;
	}

	public void setTestTypeId(int testTypeId) {
		this.testTypeId = testTypeId;
	}

	public int getSubTaskId() {
		return subTaskId;
	}

	public void setSubTaskId(int subTaskId) {
		this.subTaskId = subTaskId;
	}

	public AndroidMainTask getMainTask() {
		return mainTask;
	}

	public void setMainTask(AndroidMainTask mainTask) {
		this.mainTask = mainTask;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public int getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(int deviceId) {
		this.deviceId = deviceId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
