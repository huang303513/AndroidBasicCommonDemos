package com.ctrip.www.util;

/**
 * 项目名称：酷欧天气
 * 类描述：
 * 创建人：huangchengdu
 * 创建时间：16/9/1 下午10:31
 * 修改人：huangchengdu
 * 修改时间：16/9/1 下午10:31
 * 修改备注：
 */

/**
 * 这个接口用于网络回调返回
 */
public interface HttpCallbackListener {
    void onFinish(String response);
    void onError(Exception e);
}
