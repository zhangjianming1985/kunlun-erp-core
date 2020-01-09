package com.kunlun.erp.core.service.impl.order;

import com.kunlun.erp.core.common.constants.ErrorCodeConstant;
import com.kunlun.erp.core.common.constants.SysConstant;
import com.kunlun.erp.core.common.constants.Urls;
import com.kunlun.erp.core.common.util.DateUtil;
import com.kunlun.erp.core.common.util.IdCardVerification;
import com.kunlun.erp.core.common.util.RegexUtil;
import com.kunlun.erp.core.common.util.UniqueCodeUtil;
import com.kunlun.erp.core.dto.AbstractResponse;
import com.kunlun.erp.core.dto.routeOrder.OrderClientDto;
import com.kunlun.erp.core.dto.routeOrder.request.ClientAddRequest;
import com.kunlun.erp.core.dto.routeOrder.request.ClientListRequest;
import com.kunlun.erp.core.dto.routeOrder.response.ClientAddRespDto;
import com.kunlun.erp.core.dto.routeOrder.response.ClientListRespDto;
import com.kunlun.erp.core.dto.user.UserInfoRespDto;
import com.kunlun.erp.core.entity.OrderClient;
import com.kunlun.erp.core.entity.RouteOrder;
import com.kunlun.erp.core.mapper.OrderClientMapper;
import com.kunlun.erp.core.mapper.RouteOrderMapper;
import com.kunlun.erp.core.service.BaseService;
import com.kunlun.erp.core.service.order.OrderClientService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @ClassName OrderClientServiceImpl
 * @Description 订单的客户服务接口实现
 * @Author Jm.zhang
 * @Date 2019/12/24 11:50
 * @Version 1.0
 **/
@Service(value = "order_client_service")
public class OrderClientServiceImpl extends BaseService implements OrderClientService {
    @Resource
    private OrderClientMapper client_dao;
    @Resource
    private RouteOrderMapper order_dao;
    @Override
    public AbstractResponse<ClientAddRespDto> add(ClientAddRequest request) {
        AbstractResponse<UserInfoRespDto> user_info = super.getUserInfo(request, Urls.RouteHall.NAMESPACE);
        AbstractResponse<ClientAddRespDto> response = dtoFactory.createResponse(request.getHeader());
        ClientAddRespDto resp_body = new ClientAddRespDto();
        resp_body.setOrder_code(request.getBody().getOrder_code());
        if (request.getBody().getClient_data()== null || request.getBody().getClient_data().isEmpty()){
            //删除数据
            int del_count = client_dao.deleteByOrderCode(request.getBody().getOrder_code());
            return response;
        }else{
            for (OrderClientDto client : request.getBody().getClient_data()){
                String[] replace = {client.getClient_name()};
                //证件类型
                if (SysConstant.IdCardType.getIdCardType(client.getCard_type())==null){
                    Map<String, String> error_map = dto_message_util.getErrorsMessage(ErrorCodeConstant.ROUTE_CLIENT_ID_TYPE_INVALID,replace);
                    response.getHeader().setError_message(error_map);
                    return response;
                }
                //校验证件号码
                if (client.getCard_type()==SysConstant.IdCardType.identification.getValue()){
                    //身份证合法性
                    if (!IdCardVerification.VALIDITY.equals(IdCardVerification.IDCardValidate(client.getCard_number()))){
                        Map<String, String> error_map = dto_message_util.getErrorsMessage(ErrorCodeConstant.ROUTE_CLIENT_ID_NUMBER_INVALID,replace);
                        response.getHeader().setError_message(error_map);
                        return response;
                    }
                }else if (client.getCard_type()==SysConstant.IdCardType.passport.getValue()){
                    //护照
                    if (RegexUtil.isPassport(client.getCard_number())==false){
                        Map<String, String> error_map = dto_message_util.getErrorsMessage(ErrorCodeConstant.ROUTE_CLIENT_PASSPORT_NUMBER_INVALID,replace);
                        response.getHeader().setError_message(error_map);
                        return response;
                    }
                }
                //出生日期格式校验
                String client_birthday = client.getClient_birthday();
                if (RegexUtil.isDate(client_birthday) == false){
                    Map<String, String> error_map = dto_message_util.getErrorsMessage(ErrorCodeConstant.ROUTE_CLIENT_BIRTHDAY_INVALID,replace);
                    response.getHeader().setError_message(error_map);
                    return response;
                }
                if (client.getCard_type()==SysConstant.IdCardType.identification.getValue()){
                    //身份证 需要校验 出生日期 和 身份证的日期匹配
                    String birthday_card = IdCardVerification.getBirthday(client.getCard_number());
                    if (!birthday_card.equals(client_birthday)){
                        Map<String, String> error_map = dto_message_util.getErrorsMessage(ErrorCodeConstant.ROUTE_CLIENT_BIRTHDAY_NOT_MATCH_WITH_ID_NUMBER,replace);
                        response.getHeader().setError_message(error_map);
                        return response;
                    }
                }
                //校验年龄
                int client_age  = DateUtil.getAgeByBirthday(DateUtil.strToDateByFormat(DateUtil.FORMATTER_DATE,client_birthday));
                if (client_age != client.getClient_age()){
                    Map<String, String> error_map = dto_message_util.getErrorsMessage(ErrorCodeConstant.ROUTE_CLIENT_BIRTHDAY_NOT_MATCH_WITH_AGE,replace);
                    response.getHeader().setError_message(error_map);
                    return response;
                }

                //性别校验
                if (client.getCard_type()==SysConstant.IdCardType.identification.getValue()){
                    //身份证 需要校验 性别
                    Integer gender = IdCardVerification.getGender(client.getCard_number());
                    if (gender != client.getClient_sex()){
                        Map<String, String> error_map = dto_message_util.getErrorsMessage(ErrorCodeConstant.ROUTE_CLIENT_ID_NUMBER_NOT_MATCH_WITH_SEX,replace);
                        response.getHeader().setError_message(error_map);
                        return response;
                    }
                }
                //校验手机号码
                if (RegexUtil.isMobile(client.getClient_mobile()) == false){
                    Map<String, String> error_map = dto_message_util.getErrorsMessage(ErrorCodeConstant.ROUTE_CLIENT_MOBILE_INVALID,replace);
                    response.getHeader().setError_message(error_map);
                    return response;
                }
                //客户类型
                if (SysConstant.ClientType.getClientType(client.getClient_type())== null){
                    Map<String, String> error_map = dto_message_util.getErrorsMessage(ErrorCodeConstant.ROUTE_CLIENT_CATEGORY_INVALID,replace);
                    response.getHeader().setError_message(error_map);
                    return response;
                }
            }
            List<String> exist_code_list = new ArrayList<>();
            for (OrderClientDto dto :request.getBody().getClient_data()){
                if (StringUtils.isBlank(dto.getClient_code())){
                    //创建数据
                    OrderClient record = this.create(request.getBody().getOrder_code(),dto,user_info);
                    exist_code_list.add(record.getClient_code());
                }else{
                    //更新数据
                    OrderClient record = this.update(request.getBody().getOrder_code(),dto,user_info);
                    exist_code_list.add(record.getClient_code());
                }
            }
            if (exist_code_list.isEmpty()==false){
                //删除废弃的数据
                int del_count = client_dao.deleteByOrderCodeAndClientCode(request.getBody().getOrder_code(),exist_code_list);
            }
        }
        response.setBody(resp_body);
        return response;
    }

    @Override
    public AbstractResponse<ClientListRespDto> list(ClientListRequest request) {
        AbstractResponse<ClientListRespDto> response = dtoFactory.createResponse(request.getHeader());
        ClientListRespDto resp_body = new ClientListRespDto();
        List<OrderClientDto> list = client_dao.selectDtoByOrderCode(request.getBody().getOrder_code());
        resp_body.setClient_data(list);
        response.setBody(resp_body);
        return response;
    }


    public OrderClient create(String order_code, OrderClientDto dto, AbstractResponse<UserInfoRespDto> user_info){
        RouteOrder order_record = order_dao.selectByOrderCode(order_code);
        OrderClient record = new OrderClient();
        record.setClient_code(UniqueCodeUtil.generateUniqueCode(UniqueCodeUtil.UniquePrefix.order_client.getValue()));
        record.setOrder_code(order_record.getOrder_code());
        record.setGroup_code(order_record.getGroup_code());
        record.setClient_name(dto.getClient_name());
        record.setClient_mobile(dto.getClient_mobile());
        record.setClient_type(dto.getClient_type());
        record.setCard_number(dto.getCard_number());
        record.setClient_age(dto.getClient_age());
        record.setCard_type(dto.getCard_type());
        record.setClient_birthday(DateUtil.strToDateByFormat(DateUtil.FORMATTER_DATE,dto.getClient_birthday()));
        record.setClient_sex(dto.getClient_sex());
        if (StringUtils.isNotBlank(dto.getRemarks())){
            record.setRemarks(dto.getRemarks());
        }
        record.setCreate_time(new Date());
        record.setCreator_id(user_info.getBody().getUid());
        record.setCreator_name(user_info.getBody().getUserName());
        client_dao.insertSelective(record);
        return record;
    }

    public OrderClient update(String order_code, OrderClientDto dto, AbstractResponse<UserInfoRespDto> user_info){
        OrderClient old_record = client_dao.selectByClientCode(dto.getClient_code());
        OrderClient record = new OrderClient();
        record.setId(old_record.getId());
        record.setClient_code(old_record.getClient_code());
        record.setClient_name(dto.getClient_name());
        record.setClient_mobile(dto.getClient_mobile());
        record.setCard_type(dto.getCard_type());
        record.setCard_number(dto.getCard_number());
        record.setClient_age(dto.getClient_age());
        record.setClient_type(dto.getClient_type());
        record.setClient_birthday(DateUtil.strToDateByFormat(DateUtil.FORMATTER_DATE,dto.getClient_birthday()));
        record.setClient_sex(dto.getClient_sex());
        if (StringUtils.isNotBlank(dto.getRemarks())){
            record.setRemarks(dto.getRemarks());
        }
        record.setUpdate_time(new Date());
        client_dao.updateByPrimaryKeySelective(record);
        return record;
    }

    public void updateTrafficState(List<OrderClientDto> client_data){
        if (client_data==null || client_data.isEmpty())return;
        for (OrderClientDto client :client_data){
            client_dao.updateTrafficStatus(client);

        }


    }

    @Override
    public int deleteByOrderCode(String order_code) {
        return client_dao.deleteByOrderCode(order_code);
    }

    @Override
    public int deleteByCompanyCode(String company_code) {
        return client_dao.deleteByCompanyCode(company_code);
    }
}
