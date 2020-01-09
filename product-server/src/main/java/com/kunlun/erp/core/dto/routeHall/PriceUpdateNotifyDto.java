package com.kunlun.erp.core.dto.routeHall;

import com.kunlun.erp.core.entity.RoutePricePlanDetail;

import java.util.List;

/**
 * @ClassName PriceUpdateNotifyDto
 * @Description 通知线路大厅处理数据
 * @Author Jm.zhang
 * @Date 2019/12/18 15:42
 * @Version 1.0
 **/
public class PriceUpdateNotifyDto {
    /**
     * 需要创建的线路
     */
    private List<RoutePricePlanDetail> need_create;

    /**
     * 需要更新的线路
     */
    private List<RoutePricePlanDetail> need_update;

    /**
     * 需要删除的线路
     */
    private List<RoutePricePlanDetail> need_delete;


    public List<RoutePricePlanDetail> getNeed_create() {
        return need_create;
    }

    public void setNeed_create(List<RoutePricePlanDetail> need_create) {
        this.need_create = need_create;
    }

    public List<RoutePricePlanDetail> getNeed_update() {
        return need_update;
    }

    public void setNeed_update(List<RoutePricePlanDetail> need_update) {
        this.need_update = need_update;
    }

    public List<RoutePricePlanDetail> getNeed_delete() {
        return need_delete;
    }

    public void setNeed_delete(List<RoutePricePlanDetail> need_delete) {
        this.need_delete = need_delete;
    }


}
