package cn.eppdev.tools.weixin.utils;

import cn.eppdev.tools.weixin.entity.AccessToken;
import cn.eppdev.tools.weixin.entity.WeixinUserInfo;
import com.alibaba.fastjson.JSON;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * Created by haojinlong on 16-4-1.
 */
public class WeixinAuthorityUtils {


    private static Logger logger = LoggerFactory.getLogger(WeixinAuthorityUtils.class);


    /**
     * 根据code值获取用户的access_token
     *
     * @param code 链接传入的code值
     * @return 对应的token信息，包括token、openid等信息
     */
    public static synchronized AccessToken getToken(String code) {

        String url = WeixinConfigReader.ACCESS_TOKEN_URL + "?appid=" +
                WeixinConfigReader.getInstance().getAppid() + "&secret=" +
                WeixinConfigReader.getInstance().getAppsecret() +
                "&code=" + code + "&grant_type=authorization_code";

        HttpPost post = new HttpPost(url);
        HttpClient httpClient = HttpClients.createDefault();
        try {
            HttpResponse response = httpClient.execute(post);
            HttpEntity entity = response.getEntity();
            String json = EntityUtils.toString(entity, "UTF-8");
            return JSON.parseObject(json, AccessToken.class);
        } catch (IOException e) {
            logger.error("error:{}\n{}", e.getMessage(), e.getStackTrace());
        }

        return null;
    }


    /**
     * 根据token和openid获取用户的基本信息
     *
     * @param token  用户的token
     * @param openid openid
     * @return 用户基本信息
     */
    public static synchronized WeixinUserInfo getUserInfo(String token, String openid) {
        String url = WeixinConfigReader.USERINFO_URL + "?access_token=" + token +
                "&openid=" + openid + "&lang=zh_CN";
        HttpPost post = new HttpPost(url);
        HttpClient httpClient = HttpClients.createDefault();
        try {
            HttpResponse response = httpClient.execute(post);
            HttpEntity entity = response.getEntity();
            String json = EntityUtils.toString(entity, "UTF-8");
            return JSON.parseObject(json, WeixinUserInfo.class);
        } catch (IOException e) {
            logger.error("error:{}\n{}", e.getMessage(), e.getStackTrace());
        }

        return null;
    }
}
