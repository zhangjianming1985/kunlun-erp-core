package com.kunlun.erp.core.mapper;

import com.kunlun.erp.core.dto.company.response.CompanyListDto;
import com.kunlun.erp.core.dto.company.response.LikeNameDto;
import com.kunlun.erp.core.dto.condition.CompanyCondition;
import com.kunlun.erp.core.entity.CompanyInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CompanyInfoMapper {
    int insertSelective(CompanyInfo record);
    int deleteByCompanyCode(@Param("company_code")String company_code);
    int updateByPrimaryKeySelective(CompanyInfo record);
    CompanyInfo selectByPrimaryKey(Integer id);
    CompanyInfo selectByCompanyCode(@Param("company_code") String company_code);
    int countByCompanyName(@Param("company_name") String company_name);
    List<CompanyListDto> selectByCondition(CompanyCondition condition);
    List<LikeNameDto> selectLikeName(CompanyCondition condition);


}