package com.kunlun.erp.core.dto.page;

import com.github.pagehelper.PageInfo;

/**
 * @ClassName ListPageSumResult
 * @Description 分页加统计
 * @Author Jm.zhang
 * @Date 2019/11/26 16:40
 * @Version 1.0
 **/
public class ListPageSumResult <SE, E extends PageInfo<?>> extends ListPageResult<E> {
    private SE sum;

    public ListPageSumResult(SE sum, E page) {
        super((PageInfo<E>) page);
        this.sum = sum;
    }

    public SE getSum() {
        return sum;
    }

    public void setSum(SE sum) {
        this.sum = sum;
    }

}
