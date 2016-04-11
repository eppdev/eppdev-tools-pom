/**
 * # AuthModule.java -- (2014年12月8日)
 * 作者：郝金隆
 * 联系方式：haojinlong@189.cn
 */
package cn.eppdev.tools.web.webauth.anno;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import cn.eppdev.tools.web.webauth.consts.OperType;

/**
 * @author 郝金隆
 * @since 2014年12月8日
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface AuthModule {

	/**
	 * 要验证的模块名称
	 */
	public String module();

	/**
	 * 操作类型
	 */
	public OperType operType();

}
