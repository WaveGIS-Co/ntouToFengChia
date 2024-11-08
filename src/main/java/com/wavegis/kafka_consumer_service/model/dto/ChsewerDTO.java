package com.wavegis.kafka_consumer_service.model.dto;

public class ChsewerDTO {

    private String st_no;//測站代碼
    
    private String sendtime;//資料上傳時間
    
    private String datatime;//資料量測時間
    
    private String device_type;//設備類型

    private Double water_inner;//深度(公分)

	public String getSt_no() {
		return st_no;
	}

	public void setSt_no(String st_no) {
		this.st_no = st_no;
	}

	public String getSendtime() {
		return sendtime;
	}

	public void setSendtime(String sendtime) {
		this.sendtime = sendtime;
	}

	public String getDatatime() {
		return datatime;
	}

	public void setDatatime(String datatime) {
		this.datatime = datatime;
	}

	public String getDevice_type() {
		return device_type;
	}

	public void setDevice_type(String device_type) {
		this.device_type = device_type;
	}

	public Double getWater_inner() {
		return water_inner;
	}

	public void setWater_inner(Double water_inner) {
		this.water_inner = water_inner;
	}
}
