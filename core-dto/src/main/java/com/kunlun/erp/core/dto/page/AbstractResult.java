package com.kunlun.erp.core.dto.page;

/**
 * @ClassName AbstractResult
 * @Description 抽象的分页
 * @Author Jm.zhang
 * @Date 2019/11/26 16:38
 * @Version 1.0
 **/
public abstract class AbstractResult {
    private Object data = null;

    public Object getData() {
        return this.data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
