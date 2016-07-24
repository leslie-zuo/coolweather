package com.example.leslie.coolweather.util;

import android.text.TextUtils;

import com.example.leslie.coolweather.db.CoolWeatherDB;
import com.example.leslie.coolweather.model.City;
import com.example.leslie.coolweather.model.County;
import com.example.leslie.coolweather.model.Province;

/**
 * Created by leslie on 2016/7/23.
 * 工具类，用于解析服务器返回的省市县数据格式
 */
public class Utility {
    //解析服务器返回的省级数据
    public synchronized static boolean handleProvincesResponse(CoolWeatherDB coolWeatherDB,String response){
        if(!TextUtils.isEmpty(response)){
            String[] allProvinces=response.split(",");
            if (allProvinces!=null&&allProvinces.length>0){
                for (String p:allProvinces){
                    String[] array=p.split("\\|");
                    Province province=new Province();
                    province.setProvinceCode(array[0]);
                    province.setProvinceName(array[1]);
                    coolWeatherDB.saveProvince(province);
                }
                return true;
            }
        }
        return false;
    }

    public static boolean handleCitiesResponse(CoolWeatherDB coolWeatherDB,String response,int provinceId){
        if (!TextUtils.isEmpty(response)){
            String[] allCities=response.split(",");
            if (allCities!=null&&allCities.length>0){
                for (String p:allCities){
                    String[] array=p.split("\\|");
                    City city=new City();
                    city.setCityCode(array[0]);
                    city.setCityName(array[1]);
                    city.setProvinceId(provinceId);
                    coolWeatherDB.saveCity(city);
                }
                return true;
            }
        }
        return false;
    }

    public static boolean handleCountiesResponse(CoolWeatherDB coolWeatherDB,String response,int cityId){
        if (!TextUtils.isEmpty(response)){
            String[] cities=response.split(",");
            if (cities!=null&&cities.length>0){
               for (String c:cities){
                   String[] array=c.split("\\|");
                   County county=new County();
                   county.setCountyCode(array[0]);
                   county.setCountyName(array[1]);
                   county.setCityId(cityId);
                   coolWeatherDB.saveCounty(county);
               }
                return true;
            }

        }
        return false;
    }
}
