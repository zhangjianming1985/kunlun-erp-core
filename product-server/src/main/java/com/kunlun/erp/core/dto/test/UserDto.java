package com.kunlun.erp.core.dto.test;

/**
 * @ClassName UserDto
 * @Description
 * @Author Jm.zhang
 * @Date 2019/10/29 17:21
 * @Version 1.0
 **/
public class UserDto {
    public UserDto(){

    }
    public UserDto(Integer id, String name){
        this.id=id;
        this.name=name;
    }
    private Integer id;
    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
