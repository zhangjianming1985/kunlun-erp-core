package com.kunlun.erp.core.service.company;

import com.kunlun.erp.core.dto.AbstractResponse;
import com.kunlun.erp.core.dto.condition.PersonCondition;
import com.kunlun.erp.core.dto.person.PersonDto;
import com.kunlun.erp.core.dto.person.request.*;
import com.kunlun.erp.core.dto.person.response.*;
import com.kunlun.erp.core.entity.PersonInfo;

import java.util.List;

/**
 * @InterfaceName PersonService
 * @Description 人员信息业务接口
 * @Author Jm.zhang
 * @Date 2019/11/28 17:26
 * @Version 1.0
 **/
public interface PersonService {
    int insert(PersonInfo record);

    int deleteByCompanyCode(String company_code);

    PersonInfo getPersonByPersonCode(String person_code);

    List<PersonDto> getDtoByCondition(PersonCondition condition);

    void deleteByCondition(PersonCondition condition);

    int updatePerson(PersonInfo record);

    AbstractResponse<PersonListRespDto> list(PersonListRequest request);

    AbstractResponse<PersonAddRespDto> add(PersonAddRequest request);

    AbstractResponse<PersonDetailRespDto> detail (PersonDetailRequest request);

    AbstractResponse<PersonUpdateRespDto> update (PersonUpdateRequest request);

    AbstractResponse<LikeSearchPersonRespDto> likeSearch(LikeSearchPersonRequest request);

}