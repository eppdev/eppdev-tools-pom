/**
 * # UserProperties.java -- (2014年11月13日)
 * 作者：郝金隆
 * 联系方式：haojinlong@189.cn
 */
package cn.eppdev.tools.web.webauth.entity;

import java.lang.reflect.Method;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;

/**
 * @author 郝金隆
 * @since 2014年11月13日
 */
public class DefaultUserProps<T> extends AbstractUserProps {
	private static final long serialVersionUID = 8078729507812241367L;

	static Logger logger = LoggerFactory.getLogger(DefaultUserProps.class);

	/**
	 * 用户信息
	 */
	private T userInfo;

	/**
	 * 用户的Id
	 */
	private int userId = 0;

	/**
	 * 用户名
	 */
	private String userName;

	/**
	 * @return 用户信息
	 */
	public T getUserInfo() {
		return userInfo;
	}

	/**
	 * @param userInfo
	 *            用户信息
	 */
	public void setUserInfo(T userInfo) {
		this.userInfo = userInfo;
		Method method;
		if (userId == 0) {
			try {
				method = userInfo.getClass().getMethod("getId");
				if (method != null) {
					Object obj = method.invoke(userInfo);
					if (obj instanceof Integer) {
						this.userId = (Integer) obj;
					}
				}
			} catch (NoSuchMethodException e) {
				logger.debug("没有getId方法，无法获取用户id");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		if (userId == 0) {
			try {
				method = userInfo.getClass().getMethod("getUserId");
				if (method != null) {
					Object obj = method.invoke(userInfo);
					if (obj instanceof Integer) {
						this.userId = (Integer) obj;
					}
				}
			} catch (NoSuchMethodException e) {
				logger.debug("没有getUserId方法，无法获取用户id");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * @param userId
	 *            the userId to set
	 */
	public void setUserId(int userId) {
		this.userId = userId;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see UserProps#getUserName()
	 */
	@Override
	public String getUserName() {
		return userName;
	}

	/**
	 * @param userName
	 *            the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see UserProps#getUserId()
	 */
	@Override
	public int getUserId() {
		return userId;
	}
	
	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return JSON.toJSONString(this);
	}

	// public static void main(String[] args) {
	// DefaultUserProps<String> props = new DefaultUserProps<String>();
	// props.addAuth("user", "delete", 1);
	// System.out.println(props.hasAuth("user", OperType.delete, 2));
	// System.out.println(props.hasAuth("user", OperType.delete ));
	// }
}
