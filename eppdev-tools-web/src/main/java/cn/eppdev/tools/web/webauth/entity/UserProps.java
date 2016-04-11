/**
 * # UserProps.java -- (2015年8月19日)
 * 作者：郝金隆
 * 联系方式：haojinlong@189.cn
 */
package cn.eppdev.tools.web.webauth.entity;

import java.io.Serializable;

import cn.eppdev.tools.web.webauth.consts.OperType;

/**
 * @author 郝金隆
 * @since 2015年8月19日
 */
public interface UserProps extends Serializable {

	/**
	 * 判断用户是否具有对某种实体进行某种操作的权限
	 * 
	 * @param entityType
	 *            实体类型
	 * @param operType
	 *            操作类型
	 * @return 是否具有相应的权限
	 */
	public boolean hasAuth(String entityType, OperType operType);

	/**
	 * 判断用户是否具有对某一个实体进行某种操作的权限
	 * 
	 * @param entityType
	 *            实体类型
	 * @param operType
	 *            操作类型
	 * @param entityId
	 *            实体id
	 * @return 是否具有相应的权限
	 */
	public boolean hasAuth(String entityType, OperType operType, int entityId);

	/**
	 * 获取用户id
	 * 
	 * @return 用户id
	 */
	public int getUserId();
	
	/**
	 * 获取用户名
	 * @return 用户名
	 */
	public String getUserName();

}
