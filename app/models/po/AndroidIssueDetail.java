package models.po;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import play.data.binding.As;
import play.db.jpa.GenericModel;
import enums.IssueType;

@Entity
@Table(name = "android_issue_detail")
public class AndroidIssueDetail extends GenericModel {

	@Column(name = "issue_id")
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int issueId;

	@Column(name = "issue_type")
	private String issueType;

	@Column(name = "issue_detail")
	private String issueDetail;

	@Column(name = "mantis_id")
	private String mantisId;

	@Column(name = "issue_found_version")
	private String issueFoundVersion;
	
	
	@Column(name = "save_to_mantis_status")
	private String status;
	

	@Column(name = "create_time")
	private Date createTime;

	public AndroidIssueDetail() {
		this.createTime = new Date();
	}

	public AndroidIssueDetail issueType(IssueType issueType) {
		this.issueType = issueType.getName();
		return this;
	}

	public AndroidIssueDetail mantisId(String mantisId) {
		this.mantisId = mantisId;
		return this;
	}
	
	public AndroidIssueDetail issueDetail(String issueDetail) {
		this.issueDetail = issueDetail;
		return this;
	}
	
	public AndroidIssueDetail issueFoundVersion(String issueFoundVersion) {
		this.issueFoundVersion = issueFoundVersion;
		return this;
	}
	
	public AndroidIssueDetail status(String status) {
		this.status = status;
		return this;
	}

	public int getIssueId() {
		return issueId;
	}

	public void setIssueId(int issueId) {
		this.issueId = issueId;
	}

	public String getIssueType() {
		return issueType;
	}

	public void setIssueType(String issueType) {
		this.issueType = issueType;
	}

	public String getIssueDetail() {
		return issueDetail;
	}

	public void setIssueDetail(String issueDetail) {
		this.issueDetail = issueDetail;
	}

	public Date getCreateTime() {
		return createTime;
	}

	@As("yyyy-MM-dd HH:mm:ss")
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getMantisId() {
		return mantisId;
	}

	public void setMantisId(String mantisId) {
		this.mantisId = mantisId;
	}

	public String getIssueFoundVersion() {
		return issueFoundVersion;
	}

	public void setIssueFoundVersion(String issueFoundVersion) {
		this.issueFoundVersion = issueFoundVersion;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	
}
