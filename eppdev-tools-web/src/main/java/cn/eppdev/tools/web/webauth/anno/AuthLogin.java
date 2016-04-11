/**
 * # AuthLogin.java -- (2015年9月13日)
 * 作者：郝金隆
 * 联系方式：haojinlong@189.cn
 */
package cn.eppdev.tools.web.webauth.anno;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author 郝金隆
 * @since 2015年9月13日
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface AuthLogin {

}
