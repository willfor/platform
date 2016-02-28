package models.po;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import play.db.jpa.GenericModel;

@Entity
@Table(name = "android_test_type_detail")
public class AndroidTestTypeDetail extends GenericModel {

	@Column(name = "type_detail_id")
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int typeDetailId;

	@Column(name = "typeId")
	private int typeId;

	@Column(name = "requestUrl")
	private String requestUrl;

	public int getTypeId() {
		return typeId;
	}

	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}

	public String getRequestUrl() {
		return requestUrl;
	}

	public void setRequestUrl(String requestUrl) {
		this.requestUrl = requestUrl;
	}

	public int getTypeDetailId() {
		return typeDetailId;
	}

	public void setTypeDetailId(int typeDetailId) {
		this.typeDetailId = typeDetailId;
	}

}
