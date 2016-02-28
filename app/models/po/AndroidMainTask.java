package models.po;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import play.db.jpa.GenericModel;
import enums.TaskStatus;

@Entity
@Table(name = "android_main_task")
public class AndroidMainTask extends GenericModel {
	@OneToOne(cascade = { CascadeType.ALL }, targetEntity = AndroidPackTask.class)
	@JoinColumn(name = "main_task_id")
	private AndroidPackTask packTask;

	@OneToMany(cascade = { CascadeType.ALL }, targetEntity = AndroidSubTask.class)
	@JoinColumn(name = "main_task_id")
	private List<AndroidSubTask> subTasks;

	@Column(name = "main_task_id")
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int mainTaskId;

	@Column(name = "create_time")
	private Date createTime;

	@Column(name = "end_time")
	private Date endTime;

	@Column(name = "main_task_status")
	private String status;

	public int getMainTaskId() {
		return mainTaskId;
	}

	public void setMainTaskId(int mainTaskId) {
		this.mainTaskId = mainTaskId;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public AndroidPackTask getPackTask() {
		return packTask;
	}

	public void setPackTask(AndroidPackTask packTask) {
		this.packTask = packTask;
		this.packTask.setMainTask(this);
	}

	public List<AndroidSubTask> getSubTasks() {
		return subTasks;
	}

	public void setSubTasks(List<AndroidSubTask> subTasks) {
		this.subTasks = subTasks;
	}

	public void addToSubTasks(AndroidSubTask subTask) {
		if (subTasks == null) {
			subTasks = new ArrayList<AndroidSubTask>();
		}
		subTask.setMainTask(this);
		subTasks.add(subTask);
	}
	
	public static AndroidMainTask initMainTask() {
		AndroidMainTask maintask = new AndroidMainTask();
		maintask.setCreateTime(new Date());
		maintask.setStatus(TaskStatus.WAITING.getName());
		return maintask;
	}

}
