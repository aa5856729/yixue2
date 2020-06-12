
package com.yixue.loxc.commons;

import java.util.List;

/**
 * <p>分页处理类 .</p>
 *
 * @version v1.0
 * @author XX
 * @date Sep 21, 2011
 *
 */
public class Page<T> {
    /**
     * 当前页面
     */
    private Integer currentPage;
    /**
     * 总记录数 .
     */
    private Integer totalPage;
    /**
     * 每页行数 .
     */
    private Integer pageSize;
    /**
     * 页面的总数  .
     */
    private Integer pageCount;
    /**
     * 结果集中数据的起始位置  .
     */
    private Integer beginPos;
    /**
     * List 集合.
     */
    private List<T> listData;

    public Page() {

    }
    /**
     * 当前页面 .
     * 页面的大小 .
     * @param curpage .
     * @param pagesize .
     */
    public Page(int curpage, Integer pagesize) {
        this.currentPage = curpage;
        this.pageSize = pagesize;
    }
    /**
     * @param curpage .
     * @param totalPage
     * @param pagesize .
     */
    public Page(int curpage,Integer pagesize,Integer totalPage) {
        super();
        this.currentPage = curpage;//当前页码
        this.totalPage = totalPage;//总记录数
        this.pageSize = pagesize;//页码容量
        //总页数=总记录数total/pageSize（+1）
        this.pageCount = (totalPage + this.pageSize - 1) /this.pageSize;
        //下标起始位置：(curPage-1)*pageSize
        this.beginPos = (currentPage-1)*pageSize;
    }
    /**
     * 总页面数 .
     * @return Integer .
     */
    public Integer getPageCount() {
        return pageCount;
    }
    /**
     *  得到页面的当前位置 .
     * @return Integer .
     */
    public Integer getBeginPos() {
        return beginPos;
    }
    /**
     * @param currentPage
     *            the curPage to set
     */
    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public List<T> getListData() {
        return listData;
    }

    public void setListData(List<T> listData) {
        this.listData = listData;
    }
    /**
     * @param pageSize
     *            the pageSize to set
     */
    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    /**
     * @param rowCount
     *            the rowCount to set
     */
    public void setRowCount(Integer rowCount) {
        this.totalPage = rowCount;
    }

    /**
     * @param beginPos
     *            the beginPos to set
     */
    public void setBeginPos(Integer beginPos) {
        this.beginPos = beginPos;
    }
    public Integer getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }

    public void setPageCount(Integer pageCount) {
        this.pageCount = pageCount;
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getPageSize() {
        return pageSize;
    }
}
