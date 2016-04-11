package cn.eppdev.tools.weixin.utils;

import cn.eppdev.tools.weixin.commons.WeixinConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by haojinlong on 16-4-4.
 */
public class WeixinConfigReader {

    static Logger logger  = LoggerFactory.getLogger(WeixinConfigReader.class);

    private static WeixinConfigReader instance;

    private String appid = null;
    private String mchid = null;
    private String appsecret = null;
    private String token = null;
    private String key = null;

    public static final String ACCESS_TOKEN_URL = "https://api.weixin.qq.com/sns/oauth2/access_token";
    public static final String USERINFO_URL = "https://api.weixin.qq.com/sns/userinfo";
    public static final String ORDER_URL = "https://api.mch.weixin.qq.com/pay/unifiedorder";

    public static WeixinConfigReader newInstance(String className){
        if(instance == null){
            instance = new WeixinConfigReader(className);
        }
        return instance;
    }

    public static WeixinConfigReader getInstance(){
        return instance;
    }

    private WeixinConfigReader(String className){
        try {
            WeixinConfig weixinConfig = (WeixinConfig)Class.
                    forName(className).newInstance();
            appid = weixinConfig.getAppid();
            mchid = weixinConfig.getMchid();
            appsecret = weixinConfig.getAppsecret();
            token = weixinConfig.getToken();
            key = weixinConfig.getKey();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }



    public String getAppid() {
        return appid;
    }

    public String getMchid() {
        return mchid;
    }

    public String getToken() {
        return token;
    }

    public String getAppsecret() {
        return appsecret;
    }

    public String getKey() {
        return key;
    }
}
