/**
 * # AbstractUserProps.java -- (2015年8月19日)
 * 作者：郝金隆
 * 联系方式：haojinlong@189.cn
 */
package cn.eppdev.tools.web.webauth.entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.eppdev.tools.web.webauth.consts.OperType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author 郝金隆
 * @since 2015年8月19日
 */
public abstract class AbstractUserProps implements UserProps {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3428435094893855574L;
	static Logger logger = LoggerFactory.getLogger(AbstractUserProps.class);

	/**
	 * 用户权限列表，其中key是module，value是操作类型的列表
	 */
	private Map<String, List<String>> authMap = new HashMap<String, List<String>>();

	/**
	 * 用户权限列表，key是 {module}_{operType}，value是对应的实体列表
	 */
	private Map<String, List<Integer>> authMapWithId = new HashMap<String, List<Integer>>();

	/**
	 * 设置用户的权限，细化到模块和操作
	 * 
	 * @param module
	 *            模块Id
	 * @param operType
	 *            操作类型id
	 */
	public void addAuth(String module, String operType) {
		List<String> operTypeList = authMap.get(module);
		if (operTypeList == null) {
			operTypeList = new ArrayList<String>();
			authMap.put(module, operTypeList);
		}
		if (!operTypeList.contains(operType)) {
			operTypeList.add(operType);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * UserProps#hasAuth(java.lang.String,
	 * OperType)
	 */
	@Override
	public boolean hasAuth(String entityType, OperType operType) {
		List<String> operTypeList = authMap.get(entityType);
		if (operTypeList != null && operTypeList.contains(operType.toString())) {
			return true;
		}
		return false;
	}

	/**
	 * 设置用户的权限，仅细化到模块和操作类型
	 * 
	 * @param operType
	 *            操作类型
	 * @param module
	 *            模块名称
	 * @param entityId
	 *            对应的实体id
	 */
	public void addAuth(String module, String operType, int entityId) {
		addAuth(module, operType);
		String key = buildKey(module, operType);
		List<Integer> idList = authMapWithId.get(key);
		if (idList == null) {
			idList = new ArrayList<Integer>();
			authMapWithId.put(key, idList);
		}
		if (!idList.contains(entityId)) {
			idList.add(entityId);
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * UserProps#hasAuth(java.lang.String,
	 * OperType, int)
	 */
	@Override
	public boolean hasAuth(String entityType, OperType operType, int entityId) {
		if (!operType.hasId()) {
			return hasAuth(entityType, operType);
		}
		String key = buildKey(entityType, operType.toString());
		List<Integer> idList = authMapWithId.get(key);
		if (idList != null && idList.contains(entityId)) {
			return true;
		}
		return false;
	}

	/**
	 * 根据操作类型和模块名，生成authMapWithId的key
	 * 
	 * @param operType
	 * @param module
	 * @return
	 */
	private String buildKey(String module, String operType) {
		return module + "_" + operType;
	}

}
