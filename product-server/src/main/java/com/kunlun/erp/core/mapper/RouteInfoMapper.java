package com.kunlun.erp.core.mapper;

import com.kunlun.erp.core.entity.RouteInfo;
import org.apache.ibatis.annotations.Param;

public interface RouteInfoMapper {

    int deleteByPrimaryKey(Integer id);

    int deleteByProductCode(@Param("product_code") String product_code);

    int insertSelective(RouteInfo record);

    RouteInfo selectByPrimaryKey(Integer id);

    RouteInfo selectByProductCode(String product_code);

    int updateByPrimaryKeySelective(RouteInfo record);


}