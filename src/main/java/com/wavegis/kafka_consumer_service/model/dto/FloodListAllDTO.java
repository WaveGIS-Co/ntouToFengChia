package com.wavegis.kafka_consumer_service.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class FloodListAllDTO {

    @JsonProperty("st_no")
    private String stNo;

    @JsonProperty("st_name")
    private String stName;

    @JsonProperty("st_code")
    private String stCode;

    @JsonProperty("macaddress")
    private String macaddress;

    @JsonProperty("org_id")
    private String orgId;

    @JsonProperty("lon")
    private Double lon;

    @JsonProperty("lat")
    private Double lat;

    @JsonProperty("datatime")
    private String datatime;

    @JsonProperty("sendtime")
    private String sendtime;

    @JsonProperty("receivetime")
    private String receivetime;

    @JsonProperty("rssi")
    private Double rssi;

    @JsonProperty("snr")
    private Double snr;

    @JsonProperty("water_inner")
    private Double waterInner;

    @JsonProperty("water_inner_org")
    private Double waterInnerOrg;

    @JsonProperty("water_inner_fix")
    private Double waterInnerFix;

    @JsonProperty("water_inner_bed")
    private Double waterInnerBed;

    @JsonProperty("rain_inner")
    private Double rainInner;

    @JsonProperty("batteryvol")
    private Double batteryvol;

    @JsonProperty("batteryvol2")
    private Double batteryvol2;

    @JsonProperty("warn_lv1")
    private Double warnLv1;

    @JsonProperty("warn_lv2")
    private Double warnLv2;

    @JsonProperty("warn_lv3")
    private Double warnLv3;

    @JsonProperty("description")
    private String description;

    @JsonProperty("device_type")
    private String deviceType;

    @JsonProperty("trans_func")
    private String transFunc;

    @JsonProperty("malfunction")
    private String malfunction;

    @JsonProperty("city")
    private String city;

    @JsonProperty("town")
    private String town;

    @JsonProperty("village")
    private String village;

    @JsonProperty("supplier")
    private Integer supplier;

    @JsonProperty("version")
    private String version;

    @JsonProperty("fullwater")
    private Double fullwater;

    @JsonProperty("deadwater")
    private Double deadwater;

    @JsonProperty("reservoir")
    private Double reservoir;

    @JsonProperty("reservoir_warn1")
    private Double reservoirWarn1;

    @JsonProperty("reservoir_warn2")
    private Double reservoirWarn2;

    @JsonProperty("speed")
    private Double speed;

    @JsonProperty("speed_warn1")
    private Double speedWarn1;

    @JsonProperty("speed_warn2")
    private Double speedWarn2;

    @JsonProperty("qpe_rain")
    private Double qpeRain;

    @JsonProperty("qpe_radar")
    private Double qpeRadar;

    @JsonProperty("ex_value1")
    private Double exValue1;

    @JsonProperty("ex_value2")
    private Double exValue2;

    @JsonProperty("dtype")
    private Integer dtype;

    @JsonProperty("notify")
    private Boolean notify;
    
    //params
    
    @JsonProperty("source")
    private String source;
    
    @JsonProperty("unit")
    private String unit;
    
    @JsonProperty("display")
    private String display;
    
    @JsonProperty("group_type")
    private String groupType;

    public String getStNo() {
        return stNo;
    }

    public void setStNo(String stNo) {
        this.stNo = stNo;
    }

    public String getStName() {
        return stName;
    }

    public void setStName(String stName) {
        this.stName = stName;
    }

    public String getStCode() {
        return stCode;
    }

    public void setStCode(String stCode) {
        this.stCode = stCode;
    }

    public String getMacaddress() {
        return macaddress;
    }

    public void setMacaddress(String macaddress) {
        this.macaddress = macaddress;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public Double getLon() {
        return lon;
    }

    public void setLon(Double lon) {
        this.lon = lon;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public String getDatatime() {
        return datatime;
    }

    public void setDatatime(String datatime) {
        this.datatime = datatime;
    }

    public String getSendtime() {
        return sendtime;
    }

    public void setSendtime(String sendtime) {
        this.sendtime = sendtime;
    }

    public String getReceivetime() {
        return receivetime;
    }

    public void setReceivetime(String receivetime) {
        this.receivetime = receivetime;
    }

    public Double getRssi() {
        return rssi;
    }

    public void setRssi(Double rssi) {
        this.rssi = rssi;
    }

    public Double getSnr() {
        return snr;
    }

    public void setSnr(Double snr) {
        this.snr = snr;
    }

    public Double getWaterInner() {
        return waterInner;
    }

    public void setWaterInner(Double waterInner) {
        this.waterInner = waterInner;
    }

    public Double getWaterInnerOrg() {
        return waterInnerOrg;
    }

    public void setWaterInnerOrg(Double waterInnerOrg) {
        this.waterInnerOrg = waterInnerOrg;
    }

    public Double getWaterInnerFix() {
        return waterInnerFix;
    }

    public void setWaterInnerFix(Double waterInnerFix) {
        this.waterInnerFix = waterInnerFix;
    }

    public Double getWaterInnerBed() {
        return waterInnerBed;
    }

    public void setWaterInnerBed(Double waterInnerBed) {
        this.waterInnerBed = waterInnerBed;
    }

    public Double getRainInner() {
        return rainInner;
    }

    public void setRainInner(Double rainInner) {
        this.rainInner = rainInner;
    }

    public Double getBatteryvol() {
        return batteryvol;
    }

    public void setBatteryvol(Double batteryvol) {
        this.batteryvol = batteryvol;
    }

    public Double getBatteryvol2() {
        return batteryvol2;
    }

    public void setBatteryvol2(Double batteryvol2) {
        this.batteryvol2 = batteryvol2;
    }

    public Double getWarnLv1() {
        return warnLv1;
    }

    public void setWarnLv1(Double warnLv1) {
        this.warnLv1 = warnLv1;
    }

    public Double getWarnLv2() {
        return warnLv2;
    }

    public void setWarnLv2(Double warnLv2) {
        this.warnLv2 = warnLv2;
    }

    public Double getWarnLv3() {
        return warnLv3;
    }

    public void setWarnLv3(Double warnLv3) {
        this.warnLv3 = warnLv3;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    public String getTransFunc() {
        return transFunc;
    }

    public void setTransFunc(String transFunc) {
        this.transFunc = transFunc;
    }

    public String getMalfunction() {
        return malfunction;
    }

    public void setMalfunction(String malfunction) {
        this.malfunction = malfunction;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public String getVillage() {
        return village;
    }

    public void setVillage(String village) {
        this.village = village;
    }

    public Integer getSupplier() {
        return supplier;
    }

    public void setSupplier(Integer supplier) {
        this.supplier = supplier;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public Double getFullwater() {
        return fullwater;
    }

    public void setFullwater(Double fullwater) {
        this.fullwater = fullwater;
    }

    public Double getDeadwater() {
        return deadwater;
    }

    public void setDeadwater(Double deadwater) {
        this.deadwater = deadwater;
    }

    public Double getReservoir() {
        return reservoir;
    }

    public void setReservoir(Double reservoir) {
        this.reservoir = reservoir;
    }

    public Double getReservoirWarn1() {
        return reservoirWarn1;
    }

    public void setReservoirWarn1(Double reservoirWarn1) {
        this.reservoirWarn1 = reservoirWarn1;
    }

    public Double getReservoirWarn2() {
        return reservoirWarn2;
    }

    public void setReservoirWarn2(Double reservoirWarn2) {
        this.reservoirWarn2 = reservoirWarn2;
    }

    public Double getSpeed() {
        return speed;
    }

    public void setSpeed(Double speed) {
        this.speed = speed;
    }

    public Double getSpeedWarn1() {
        return speedWarn1;
    }

    public void setSpeedWarn1(Double speedWarn1) {
        this.speedWarn1 = speedWarn1;
    }

    public Double getSpeedWarn2() {
        return speedWarn2;
    }

    public void setSpeedWarn2(Double speedWarn2) {
        this.speedWarn2 = speedWarn2;
    }

    public Double getQpeRain() {
        return qpeRain;
    }

    public void setQpeRain(Double qpeRain) {
        this.qpeRain = qpeRain;
    }

    public Double getQpeRadar() {
        return qpeRadar;
    }

    public void setQpeRadar(Double qpeRadar) {
        this.qpeRadar = qpeRadar;
    }

    public Double getExValue1() {
        return exValue1;
    }

    public void setExValue1(Double exValue1) {
        this.exValue1 = exValue1;
    }

    public Double getExValue2() {
        return exValue2;
    }

    public void setExValue2(Double exValue2) {
        this.exValue2 = exValue2;
    }

    public Integer getDtype() {
        return dtype;
    }

    public void setDtype(Integer dtype) {
        this.dtype = dtype;
    }

    public Boolean getNotify() {
        return notify;
    }

    public void setNotify(Boolean notify) {
        this.notify = notify;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getDisplay() {
        return display;
    }

    public void setDisplay(String display) {
        this.display = display;
    }

    public String getGroupType() {
        return groupType;
    }

    public void setGroupType(String groupType) {
        this.groupType = groupType;
    }

    @Override
    public String toString() {
        return "FloodListAllDTO [stNo=" + stNo + ", stName=" + stName + ", stCode=" + stCode + ", macaddress="
                + macaddress + ", orgId=" + orgId + ", lon=" + lon + ", lat=" + lat + ", datatime=" + datatime
                + ", sendtime=" + sendtime + ", receivetime=" + receivetime + ", rssi=" + rssi + ", snr=" + snr
                + ", waterInner=" + waterInner + ", waterInnerOrg=" + waterInnerOrg + ", waterInnerFix=" + waterInnerFix
                + ", waterInnerBed=" + waterInnerBed + ", rainInner=" + rainInner + ", batteryvol=" + batteryvol
                + ", batteryvol2=" + batteryvol2 + ", warnLv1=" + warnLv1 + ", warnLv2=" + warnLv2 + ", warnLv3="
                + warnLv3 + ", description=" + description + ", deviceType=" + deviceType + ", transFunc=" + transFunc
                + ", malfunction=" + malfunction + ", city=" + city + ", town=" + town + ", village=" + village
                + ", supplier=" + supplier + ", version=" + version + ", fullwater=" + fullwater + ", deadwater="
                + deadwater + ", reservoir=" + reservoir + ", reservoirWarn1=" + reservoirWarn1 + ", reservoirWarn2="
                + reservoirWarn2 + ", speed=" + speed + ", speedWarn1=" + speedWarn1 + ", speedWarn2=" + speedWarn2
                + ", qpeRain=" + qpeRain + ", qpeRadar=" + qpeRadar + ", exValue1=" + exValue1 + ", exValue2="
                + exValue2 + ", dtype=" + dtype + ", notify=" + notify + ", source=" + source + ", unit=" + unit
                + ", display=" + display + ", groupType=" + groupType + "]";
    }
    

}
