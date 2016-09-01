package com.ctrip.www.util;

/**
 * 项目名称：酷欧天气
 * 类描述：
 * 创建人：huangchengdu
 * 创建时间：16/9/1 下午10:41
 * 修改人：huangchengdu
 * 修改时间：16/9/1 下午10:41
 * 修改备注：
 */

import android.text.TextUtils;

import com.ctrip.www.db.CoolWeatherDB;
import com.ctrip.www.model.City;
import com.ctrip.www.model.County;
import com.ctrip.www.model.Province;

/**
 * 处理服务器返回的数据
 */
public class Utility {
    /**
     * 解析和处理服务器返回的省级数据
     * @param coolWeatherDB
     * @param response
     * @return
     */
   public synchronized static boolean handleProvincesResponse(CoolWeatherDB coolWeatherDB,String
           response){
       if (!TextUtils.isEmpty(response)){
           String[] allProvinces = response.split(",");
           if (allProvinces != null && allProvinces.length > 0){
               for (String p: allProvinces){
                   String[] array = p.split("\\|");
                   Province province = new Province();
                   province.setProvinceCode(array[0]);
                   province.setProvinceName(array[1]);
                   coolWeatherDB.saveProvince(province);
               }
               return true;
           }
       }
       return false;
   }

    /**
     * 解析和处理服务器返回的市级数据
     */
    public static boolean handleCitiesResponse(CoolWeatherDB coolWeatherDB, String response, int provinceId) {
        if (!TextUtils.isEmpty(response)) {
            String[] allCities = response.split(",");
            if (allCities != null && allCities.length > 0) {
                for (String c : allCities) {
                    String[] array = c.split("\\|");
                    City city = new City();
                    city.setCityCode(array[0]);
                    city.setCityName(array[1]);
                    city.setProvinceId(provinceId);
                    // 将解析出来的数据存储到City表
                    coolWeatherDB.saveCity(city);
                }
                return true;
            }
        }
        return false;
    }

    /**
     * 解析和处理服务器返回的县级数据
     */
    public static boolean handleCountiesResponse(CoolWeatherDB coolWeatherDB, String response, int cityId) {
        if (!TextUtils.isEmpty(response)) {
            String[] allCounties = response.split(",");
            if (allCounties != null && allCounties.length > 0) {
                for (String c : allCounties) {
                    String[] array = c.split("\\|");
                    County county = new County();
                    county.setCountyCode(array[0]);
                    county.setCountyName(array[1]);
                    county.setCityId(cityId);
                    // 将解析出来的数据存储到County表
                    coolWeatherDB.saveCounty(county);
                }
                return true;
            }
        }
        return false;
    }
}
