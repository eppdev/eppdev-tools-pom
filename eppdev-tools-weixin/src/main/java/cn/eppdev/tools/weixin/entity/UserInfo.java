package cn.eppdev.tools.weixin.entity;

import java.io.Serializable;

/**
 * Created by haojinlong on 16-4-3.
 */
public class UserInfo implements Serializable {

    private static final long serialVersionUID = 5717418371099084431L;


    /**
     * 性别男
     */
    public static final int MALE = 1;
    /**
     * 性别女
     */
    public static final int FEMALE = 2;

    /**
     * 性别未知
     */
    public static final int UNONE = 0;




    /**
     * 用户的唯一标识
     */
    private String openid;

    /**
     * 用户的昵称
     */
    private String nickname;


    /**
     * 用户的性别，值为1时是男性，值为2时是女性，值为0时是未知
     */
    private int sex;


    /**
     * 用户所在城市
     */
    private String city;


    /**
     * 用户所在国家
     */
    private String country;


    /**
     * 用户所在省份
     */
    private String province;


    /**
     * 用户头像，最后一个数值代表正方形头像大小（有0、46、64、96、132数值可选，
     * 0代表640*640正方形头像），用户没有头像时该项为空。
     * 若用户更换头像，原有头像URL将失效。
     */
    private String headimgurl;





    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }


    public String getHeadimgurl() {
        return headimgurl;
    }

    public void setHeadimgurl(String headimgurl) {
        this.headimgurl = headimgurl;
    }

}
