package com.codeliu.course_select_system.entity;

/**
 * @ClassName liuxiaoping
 * @Description 分页操作
 * @Author liu
 * @Date 2019/2/12 13:02
 * @Version 1.0
 */
public class PageVO {

    // 当前页码，默认为1
    private int currentPageNo = 1;
    // 总页数
    private int totalCount;
    // 每页的记录数
    private int pageSize = 5;
    // 上一页
    private int prePageNo;
    // 下一页
    private int nextPageNo;
    // 要前往的页码，默认为0
    private int toPageNo = 0;

    public int getCurrentPageNo() {
        return currentPageNo;
    }

    /**
     * @Author liuxiaoping
     * @Description 设置当前的页码
     * @Date 22:31 2019/2/12
     * @Param [currentPageNo]
     * @return void
     **/
    public void setCurrentPageNo(int currentPageNo) {
        if(currentPageNo != 1) {
            this.prePageNo = currentPageNo - 1;
        }

        this.nextPageNo = currentPageNo + 1;

        this.currentPageNo = currentPageNo;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        if(totalCount % pageSize > 0) {
            this.totalCount = (totalCount / pageSize) + 1;
        } else {
            this.totalCount = (totalCount / pageSize);
        }
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPrePageNo() {
        return prePageNo;
    }

    public void setPrePageNo(int prePageNo) {
        this.prePageNo = prePageNo;
    }

    public int getNextPageNo() {
        return nextPageNo;
    }

    public void setNextPageNo(int nextPageNo) {
        this.nextPageNo = nextPageNo;
    }

    public int getToPageNo() {
        return toPageNo;
    }

    public void setToPageNo(int toPageNo) {
        // 新一页
        this.toPageNo = (toPageNo - 1) * pageSize;
        // 设置跳转后当前的页码
        setCurrentPageNo(toPageNo);
    }
}
