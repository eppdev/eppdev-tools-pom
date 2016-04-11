/**
 * # AuthEntity.java -- (2014年12月8日)
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
public @interface AuthEntity {

	/**
	 * 要验证的实体名称
	 */
	public String module();

	/**
	 * 要验证的操作名称
	 */
	public OperType operType();

	/**
	 * 是否是使用pathVariable
	 */
	public boolean usePathVariable() default true;

	/**
	 * 获取采用REST方式的模块实例id的前缀，默认会是OperType.getURI的方法，
	 * 如/table/upate/1/column/list，将获取到"/update/"， 仅usePathVariable设置为true时有效
	 * 
	 * @see OperType
	 */
	public String prefix() default "";

	/**
	 * 从request中获取id的请求字段名称，仅usePathVariable为false的时候有效
	 */
	public String idKey() default "id";
}
