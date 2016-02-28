package models.po;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import play.db.jpa.GenericModel;

@Entity
@Table(name = "android_test_case")
public class AndroidTestCase extends GenericModel {

	@Column(name = "test_case_id")
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int testCaseid;

	@Column(name = "case_desc")
	private String caseDescription;

	public int getTestCaseid() {
		return testCaseid;
	}

	public void setTestCaseid(int testCaseid) {
		this.testCaseid = testCaseid;
	}

	public String getCaseDescription() {
		return caseDescription;
	}

	public void setCaseDescription(String caseDescription) {
		this.caseDescription = caseDescription;
	}

}
