package com.kunlun.erp.core.mapper;

import com.kunlun.erp.core.dto.condition.PersonCondition;
import com.kunlun.erp.core.dto.person.LikeSearchPersonDto;
import com.kunlun.erp.core.dto.person.PersonDto;
import com.kunlun.erp.core.entity.PersonInfo;

import java.util.List;

public interface PersonInfoMapper {

    int insertSelective(PersonInfo record);

    int updateByPrimaryKeySelective(PersonInfo record);

    int deleteByPrimaryKey(Integer id);

    void deleteByCondition(PersonCondition condition);

    int deleteByCompanyCode(String company_code);

    PersonInfo selectByPrimaryKey(Integer id);

    PersonInfo selectByPersonCode(String person_code);

    PersonDto selectDtoByPersonCode(String person_code);

    List<PersonDto> selectDtoByCondition(PersonCondition condition);

    List<LikeSearchPersonDto> selectLikePerson(PersonCondition condition);
}