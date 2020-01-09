package com.kunlun.erp.core.service.impl.company;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kunlun.erp.core.common.constants.Urls;
import com.kunlun.erp.core.common.util.UniqueCodeUtil;
import com.kunlun.erp.core.component.PersonComponent;
import com.kunlun.erp.core.dto.AbstractResponse;
import com.kunlun.erp.core.dto.condition.PersonCondition;
import com.kunlun.erp.core.dto.person.LikeSearchPersonDto;
import com.kunlun.erp.core.dto.person.PersonDto;
import com.kunlun.erp.core.dto.person.request.*;
import com.kunlun.erp.core.dto.person.response.*;
import com.kunlun.erp.core.dto.user.UserInfoRespDto;
import com.kunlun.erp.core.entity.PersonInfo;
import com.kunlun.erp.core.mapper.PersonInfoMapper;
import com.kunlun.erp.core.service.BaseService;
import com.kunlun.erp.core.service.company.PersonService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @ClassName PersonServiceImpl
 * @Description 人员服务接口实现
 * @Author Jm.zhang
 * @Date 2019/11/28 17:33
 * @Version 1.0
 **/
@Service(value = "person_service")
public class PersonServiceImpl extends BaseService implements PersonService {
    @Resource
    private PersonInfoMapper person_dao;
    @Resource(name = "component_person")
    private PersonComponent component_person;

    @Override
    public int insert(PersonInfo record) {
        return person_dao.insertSelective(record);
    }

    @Override
    public int deleteByCompanyCode(String company_code) {
        return person_dao.deleteByCompanyCode(company_code);
    }

    @Override
    public PersonInfo getPersonByPersonCode(String person_code) {
        return person_dao.selectByPersonCode(person_code);
    }

    @Override
    public List<PersonDto> getDtoByCondition(PersonCondition condition) {
        return person_dao.selectDtoByCondition(condition);
    }

    @Override
    public void deleteByCondition(PersonCondition condition) {
        person_dao.deleteByCondition(condition);
    }

    @Override
    public int updatePerson(PersonInfo record) {
        return person_dao.updateByPrimaryKeySelective(record);
    }

    @Override
    public AbstractResponse<PersonListRespDto> list(PersonListRequest request) {
        AbstractResponse<PersonListRespDto> response = dtoFactory.createResponse(request.getHeader());
        PersonListRespDto resp_body = new PersonListRespDto();
        PersonCondition condition = component_person.convert(request);
        PageHelper.startPage(condition.getPage_index(), condition.getPage_size(), true);
        List<PersonDto> list = person_dao.selectDtoByCondition(condition);
        PageInfo<PersonDto> page_list = new PageInfo<>(list);
        resp_body.setPage_data(page_list);
        response.setBody(resp_body);
        return response;
    }

    @Override
    public AbstractResponse<PersonAddRespDto> add(PersonAddRequest request) {
        AbstractResponse<UserInfoRespDto> user_info = super.getUserInfo(request, Urls.Person.NAMESPACE);
        AbstractResponse<PersonAddRespDto>  response = dtoFactory.createResponse(request.getHeader());
        PersonAddRespDto resp_body = new PersonAddRespDto();
        PersonAddReqDto req_body = request.getBody();
        PersonInfo person_record = new PersonInfo();
        person_record.setCompany_code(req_body.getCompany_code());
        person_record.setPerson_code(UniqueCodeUtil.generateUniqueCode(UniqueCodeUtil.UniquePrefix.person_info.getValue()));
        person_record.setPerson_type(req_body.getPerson_type());
        person_record.setPerson_name(req_body.getPerson_name());
        person_record.setPerson_mobile(req_body.getPerson_mobile());
        person_record.setPerson_wechat(req_body.getPerson_wechat());
        person_record.setPerson_phone(req_body.getPerson_phone());
        person_record.setPerson_qq(req_body.getPerson_qq());
        person_record.setPosition(req_body.getPosition());
        person_record.setRemarks(req_body.getRemarks());
        person_record.setCreate_time(new Date());
        person_record.setCreator_id(user_info.getBody().getUid());
        person_record.setCreator_name(user_info.getBody().getUserName());
        int result_id = person_dao.insertSelective(person_record);
        resp_body.setPerson_code(person_record.getPerson_code());
        response.setBody(resp_body);
        return response;
    }

    @Override
    public AbstractResponse<PersonDetailRespDto> detail(PersonDetailRequest request) {
        AbstractResponse<PersonDetailRespDto> response = dtoFactory.createResponse(request.getHeader());
        PersonDetailRespDto resp_body = new PersonDetailRespDto();
        resp_body.setPerson_info(person_dao.selectDtoByPersonCode(request.getBody().getPerson_code()));
        response.setBody(resp_body);
        return response;
    }

    @Override
    public AbstractResponse<PersonUpdateRespDto> update(PersonUpdateRequest request) {
        AbstractResponse<PersonUpdateRespDto> response = dtoFactory.createResponse(request.getHeader());
        PersonUpdateRespDto resp_body = new PersonUpdateRespDto();
        PersonInfo  record = person_dao.selectByPersonCode(request.getBody().getPerson_code());
        PersonInfo update_record = new  PersonInfo();
        update_record.setId(record.getId());
        update_record.setPerson_name(request.getBody().getPerson_name());
        update_record.setPerson_mobile(request.getBody().getPerson_mobile());
        update_record.setPerson_wechat(request.getBody().getPerson_wechat());
        update_record.setPerson_phone(request.getBody().getPerson_phone());
        update_record.setPerson_qq(request.getBody().getPerson_qq());
        update_record.setPosition(request.getBody().getPosition());
        update_record.setRemarks(request.getBody().getRemarks());
        update_record.setUpdate_time(new Date());
        person_dao.updateByPrimaryKeySelective(update_record);
        resp_body.setPerson_code(record.getPerson_code());
        response.setBody(resp_body);
        return response;
    }

    @Override
    public AbstractResponse<LikeSearchPersonRespDto> likeSearch(LikeSearchPersonRequest request) {
        AbstractResponse<LikeSearchPersonRespDto> response = dtoFactory.createResponse(request.getHeader());
        PersonCondition condition = new PersonCondition();
        condition.setCompany_code(request.getBody().getCompany_code());
        condition.setPerson_type(request.getBody().getPerson_type());
        if (StringUtils.isNotBlank(request.getBody().getPerson_name())){
            condition.setPerson_name(request.getBody().getPerson_name());
            condition.setPerson_name_like(true);
        }
        List<LikeSearchPersonDto> list = person_dao.selectLikePerson(condition);
        LikeSearchPersonRespDto resp_body = new LikeSearchPersonRespDto();
        resp_body.setPerson_data(list);
        response.setBody(resp_body);
        return response;
    }
}
