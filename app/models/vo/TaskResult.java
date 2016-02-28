package models.vo;

import java.util.ArrayList;
import java.util.List;

public class TaskResult {
	private String name;
	private List<Double> data;

	public TaskResult(String name, List<Double> data) {
		this.name = name;
		this.data = data;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Double> getData() {
		return data;
	}

	public void setData(List<Double> data) {
		this.data = data;
	}

	public void addToData(Double testData) {
		if (data == null) {
			data = new ArrayList<Double>();
		}
		data.add(testData);
	}
}
