package com.kunlun.erp.core.dto.page;

import com.github.pagehelper.PageInfo;

/**
 * @ClassName ListPageResult
 * @Description 分页工具
 * @Author Jm.zhang
 * @Date 2019/11/26 16:35
 * @Version 1.0
 **/
public class ListPageResult<E> extends AbstractResult {
    /**
     * 总条数
     */
    private Long total_size;
    /**
     * 总页数
     */
    private Long total_page;
    /**
     * 每页条数
     */
    private Integer page_size;

    /**
     * 当前页数
     */
    private Integer page_index;


    public ListPageResult(PageInfo<E> page) {
        if (page != null) {
            setData(page.getList());
            setPage_index(page.getPageNum());
            setPage_size(page.getPageSize());
            setTotal_size(page.getTotal());
            this.total_page = (long) page.getPages();
        }

    }

    public Long getTotal_size() {
        return total_size;
    }

    public void setTotal_size(Long total_size) {
        this.total_size = total_size;
    }

    public Long getTotal_page() {
        return total_page;
    }

    public void setTotal_page(Long total_page) {
        this.total_page = total_page;
    }

    public Integer getPage_size() {
        return page_size;
    }

    public void setPage_size(Integer page_size) {
        this.page_size = page_size;
    }

    public Integer getPage_index() {
        return page_index;
    }

    public void setPage_index(Integer page_index) {
        this.page_index = page_index;
    }
}
