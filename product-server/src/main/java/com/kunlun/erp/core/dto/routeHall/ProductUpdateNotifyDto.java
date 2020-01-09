package com.kunlun.erp.core.dto.routeHall;

import java.util.Date;

/**
 * @ClassName ProductUpdateNotifyDto
 * @Description 产品修改通知
 * @Author Jm.zhang
 * @Date 2019/12/18 18:50
 * @Version 1.0
 **/
public class ProductUpdateNotifyDto {
    /**
     * 产品编号
     */
    private String product_code;
    /**
     * 产品名
     */
    private String product_name;

    /**
     * 内部代码
     */
    private String internal_code;

    /**
     * 更新时间
     */
    private Date update_time;


    public String getProduct_code() {
        return product_code;
    }

    public void setProduct_code(String product_code) {
        this.product_code = product_code;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public Date getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(Date update_time) {
        this.update_time = update_time;
    }

    public String getInternal_code() {
        return internal_code;
    }

    public void setInternal_code(String internal_code) {
        this.internal_code = internal_code;
    }
}
