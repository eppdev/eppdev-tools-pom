/**
 * # Pager.java -- (2014年11月12日)
 * 作者：郝金隆
 * 联系方式：haojinlong@189.cn
 */
package cn.eppdev.tools.web.web.entity;

import java.io.Serializable;
import java.util.List;

import com.alibaba.fastjson.JSON;

/**
 * @author 郝金隆
 * @since 2014年11月12日
 */
public class Pager<T> implements Serializable {
	private static final long serialVersionUID = -3755040126268461836L;
	private Integer totalCount;
	private Integer countPerPage;
	private Integer currentPage;
	private Integer previousPage;
	private Integer nextPage;
	private Integer maxPage;
	private List<T> list;

	/**
	 * 初始化
	 * 
	 * @param totalCount
	 *            总体条数
	 * @param countPerPage
	 *            每页条数
	 * @param currentPage
	 *            当前页
	 */
	public Pager(Integer totalCount, Integer countPerPage, Integer currentPage) {
		super();
		this.totalCount = totalCount;
		this.countPerPage = countPerPage;
		this.currentPage = currentPage;
		this.maxPage = totalCount % countPerPage > 0 ? totalCount / countPerPage + 1 : totalCount / countPerPage;
		if (this.maxPage == 0){
			this.maxPage = 1;
		}
		if (this.currentPage == null || this.currentPage < 1) {
			this.currentPage = 1;
		} else if (this.currentPage > this.maxPage) {
			this.currentPage = this.maxPage;
		}

		this.previousPage = this.currentPage <= 1 ? this.currentPage : this.currentPage - 1;
		this.nextPage = this.currentPage == this.maxPage ? this.currentPage : this.currentPage + 1;
	}

	/**
	 * @return the totalCount
	 */
	public Integer getTotalCount() {
		return totalCount;
	}

	/**
	 * @return the countPerPage
	 */
	public Integer getCountPerPage() {
		return countPerPage;
	}

	/**
	 * @return the currentPage
	 */
	public Integer getCurrentPage() {
		return currentPage;
	}

	/**
	 * @return the previousPage
	 */
	public Integer getPreviousPage() {
		return previousPage;
	}

	/**
	 * @return the nextPage
	 */
	public Integer getNextPage() {
		return nextPage;
	}

	/**
	 * @return the maxPage
	 */
	public Integer getMaxPage() {
		return maxPage;
	}

	/**
	 * @return 分页的实体列表
	 */
	public List<T> getList() {
		return list;
	}

	/**
	 * @param list
	 *            已分页的实体列表
	 */
	public void setList(List<T> list) {
		this.list = list;
	}
	
	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return JSON.toJSONString(this);
	}

}
