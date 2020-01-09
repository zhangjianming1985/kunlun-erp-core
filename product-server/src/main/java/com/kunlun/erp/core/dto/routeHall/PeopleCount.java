package com.kunlun.erp.core.dto.routeHall;

/**
 * @ClassName PeopleCount
 * @Description 人数统计
 * @Author Jm.zhang
 * @Date 2019-12-24 1:27
 * @Version 1.0
 **/
public class PeopleCount {

    private int adult_count;

    private int children_count;

    public int getAdult_count() {
        return adult_count;
    }

    public void setAdult_count(int adult_count) {
        this.adult_count = adult_count;
    }

    public int getChildren_count() {
        return children_count;
    }

    public void setChildren_count(int children_count) {
        this.children_count = children_count;
    }
}
