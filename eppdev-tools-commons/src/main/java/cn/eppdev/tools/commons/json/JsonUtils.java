/**
 * # JsonFactory.java -- (2014年8月9日)
 * 作者：郝金隆
 * 联系方式：haojinlong@189.cn
 */
package cn.eppdev.tools.commons.json;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;

/**
 * Json的工具类，调用fastjason的api实现javaBean与json的转换
 * 
 * @deprecated
 * 
 * @author 郝金隆
 *
 */
public class JsonUtils {
	static Logger logger = LoggerFactory.getLogger(JsonUtils.class);

	/**
	 * 根据javabean获取对应的json语句
	 * 
	 * @param obj
	 *            要转换的对象
	 * @return javabean对应的json语句
	 */
	public static String toJson(Object obj) {
		return JSON.toJSONString(obj);
	}

	/**
	 * 读取json语句，获取对应的javabean实例
	 * 
	 * @param json
	 *            json语句
	 * @param objType
	 *            javabean类
	 * @return 相应的javabean实例
	 */
	public static <T> T fromJson(String json, Class<T> objType) {
		return JSON.parseObject(json, objType);
	}

}
