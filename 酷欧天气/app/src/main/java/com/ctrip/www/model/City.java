package com.ctrip.www.model;

/**
 * 项目名称：android酷欧天气
 * 类描述：
 * 创建人：huangchengdu
 * 创建时间：16/9/1 下午3:45
 * 修改人：huangchengdu
 * 修改时间：16/9/1 下午3:45
 * 修改备注：
 */
public class City {
    private int id;
    private String cityName;
    private String cityCode;
    private int provinceId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public int getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(int provinceId) {
        this.provinceId = provinceId;
    }
}
