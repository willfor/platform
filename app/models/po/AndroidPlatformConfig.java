package models.po;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import play.db.jpa.GenericModel;

@Entity
@Table(name = "android_platform_config")
public class AndroidPlatformConfig extends GenericModel {
	@Column(name = "config_id")
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int configId;

	@Column(name = "config_key")
	private String configKey;

	@Column(name = "config_value")
	private String configValue;

	public String getConfigKey() {
		return configKey;
	}

	public void setConfigKey(String configKey) {
		this.configKey = configKey;
	}

	public String getConfigValue() {
		return configValue;
	}

	public void setConfigValue(String configValue) {
		this.configValue = configValue;
	}

	public int getConfigId() {
		return configId;
	}

	public void setConfigId(int configId) {
		this.configId = configId;
	}

}
