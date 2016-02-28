package enums;

public enum SubTaskType {
	PACK(0, "pack", "打包",0,0,10), 
	TEST_CPU(1, "CPU", "CPU测试",7,10,3), 
	TEST_MEMORY(2,"Memory", "内存测试",40000,50000,3), 
	TEST_DOWNLOAD(3, "Download", "下载测试",1,2,3), 
	TEST_JS(4,"JS", "JS测试",1,2,3), 
	TEST_UI(5, "UI", "UI测试",1,2,3),
	TEST_MONKEY(6, "Monkey", "Monkey测试",1,2,3);
	

	private int typeId;
	private String name;
	private String description;
	private double rangeFrom;
	private double rangeTo;
	private int interval;
	
	

	private SubTaskType(int typeId, String name, String description,double rangeFrom,double rangeTo,int interval) {
		this.typeId = typeId;
		this.name = name;
		this.description = description;
		this.rangeFrom = rangeFrom;
		this.rangeTo = rangeTo;
		this.interval = interval;
	}
	
	public static SubTaskType findSubTaskTypeById(int typeId){
		for(SubTaskType subTaskType:SubTaskType.values()){
			if(subTaskType.getTypeId() == typeId){
				return subTaskType;
			}
		}
		return null;
	}

	public int getTypeId() {
		return typeId;
	}

	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getRangeFrom() {
		return rangeFrom;
	}

	public void setRangeFrom(double rangeFrom) {
		this.rangeFrom = rangeFrom;
	}

	public double getRangeTo() {
		return rangeTo;
	}

	public void setRangeTo(double rangeTo) {
		this.rangeTo = rangeTo;
	}

	public int getInterval() {
		return interval;
	}

	public void setInterval(int interval) {
		this.interval = interval;
	}
	
	

}
