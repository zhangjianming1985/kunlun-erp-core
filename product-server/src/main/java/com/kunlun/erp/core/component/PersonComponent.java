package com.kunlun.erp.core.component;

import com.kunlun.erp.core.dto.condition.BaseCondition;
import com.kunlun.erp.core.dto.condition.PersonCondition;
import com.kunlun.erp.core.dto.person.request.PersonListRequest;
import org.springframework.stereotype.Component;

/**
 * @ClassName PersonComponent
 * @Description 人员业务组件
 * @Author Jm.zhang
 * @Date 2019-12-02 22:46
 * @Version 1.0
 **/
@Component(value = "component_person")
public class PersonComponent extends BaseCondition {


    public PersonCondition convert(PersonListRequest request){
        PersonCondition condition = new PersonCondition();
        condition.setPage_index(request.getBody().getPage_index());
        condition.setPage_size(request.getBody().getPage_size());
        condition.setCompany_code(request.getBody().getCompany_code());
        condition.setPerson_type(request.getBody().getPerson_type());
        condition.setPerson_name_like(true);
        condition.setPerson_name(request.getBody().getPerson_name());
        condition.setPerson_mobile(request.getBody().getPerson_mobile());
        return condition;

    }


}
