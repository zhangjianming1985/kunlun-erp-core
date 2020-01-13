package com.kunlun.erp.core.service.impl.product;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kunlun.erp.core.common.configuration.PermissionKeyProperties;
import com.kunlun.erp.core.common.constants.SysConstant;
import com.kunlun.erp.core.common.constants.Urls;
import com.kunlun.erp.core.common.util.UniqueCodeUtil;
import com.kunlun.erp.core.dto.AbstractResponse;
import com.kunlun.erp.core.dto.product.ProductCategoryDto;
import com.kunlun.erp.core.dto.product.request.*;
import com.kunlun.erp.core.dto.product.response.*;
import com.kunlun.erp.core.dto.user.HasPermissionRespDto;
import com.kunlun.erp.core.dto.user.UserInfoRespDto;
import com.kunlun.erp.core.entity.ProductCategory;
import com.kunlun.erp.core.mapper.ProductCategoryMapper;
import com.kunlun.erp.core.service.BaseService;
import com.kunlun.erp.core.service.account.PermissionService;
import com.kunlun.erp.core.service.product.ProductCategoryService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @ClassName ProductCategoryServiceImpl
 * @Description 产品类别服务实现
 * @Author Jm.zhang
 * @Date 2019/12/6 10:14
 * @Version 1.0
 **/
@Service(value = "product_category_service")
public class ProductCategoryServiceImpl extends BaseService implements ProductCategoryService {
    @Resource
    private ProductCategoryMapper product_category_dao;
    @Resource(name = "permission_service")
    private PermissionService permission_service;
    @Resource
    private PermissionKeyProperties per_properties;

    @Override
    public ProductCategory getRecordByCategoryCode(String category_code) {
        return product_category_dao.selectByCategoryCode(category_code);
    }

    @Override
    public AbstractResponse<ProductCategoryListRespDto> list(ProductCategoryListRequest request) {
        AbstractResponse<ProductCategoryListRespDto> response = dtoFactory.createResponse(request.getHeader());
        ProductCategoryListRespDto resp_body = new ProductCategoryListRespDto();
        PageHelper.startPage(request.getBody().getPage_index(), request.getBody().getPage_size(), true);

        Integer uid=null;
        AbstractResponse<HasPermissionRespDto> permission_dto = permission_service.getUserByPermission(request.getHeader().getTrans_no(),request.getHeader().getSecret_key(),per_properties.getQuery_all_data());
        if (permission_dto.getHeader().getState().equals(SysConstant.RespStatus.resp_status_fail.getValue())){
            uid=permission_dto.getBody().getUid();
        }
        List<ProductCategoryDto> list  = product_category_dao.selectDtoList(request.getBody().getCategory_name(),request.getBody().getCategory_state(),uid);
        PageInfo<ProductCategoryDto> page_info = new PageInfo<>(list);
        resp_body.setPage_data(page_info);
        response.setBody(resp_body);
        return response;
    }
    @Transactional(rollbackFor = Exception.class)
    @Override
    public AbstractResponse<ProductCategoryAddRespDto> add(ProductCategoryAddRequest request) {
        AbstractResponse<UserInfoRespDto> user_info = super.getUserInfo(request, Urls.Product.NAMESPACE);
        AbstractResponse<ProductCategoryAddRespDto> response = dtoFactory.createResponse(request.getHeader());
        ProductCategoryAddRespDto  resp_body = new ProductCategoryAddRespDto();
        ProductCategory record = new ProductCategory();
        record.setCategory_code(UniqueCodeUtil.generateUniqueCode(UniqueCodeUtil.UniquePrefix.product_category.getValue()));
        record.setCategory_name(request.getBody().getCategory_name());
        record.setCategory_name_defined(request.getBody().getCategory_name());
        record.setCategory_state(SysConstant.ProductCategoryState.enable.getValue());
        record.setCreate_time(new Date());
        record.setCreator_id(user_info.getBody().getUid());
        record.setCreator_name(user_info.getBody().getUserName());
        int count_result = product_category_dao.insertSelective(record);
        resp_body.setCategory_code(record.getCategory_code());
        response.setBody(resp_body);
        return response;
    }

    @Override
    public AbstractResponse<ProductCategoryDetailRespDto> detail(ProductCategoryDetailRequest request) {
        AbstractResponse<ProductCategoryDetailRespDto> response = dtoFactory.createResponse(request.getHeader());
        ProductCategoryDetailRespDto resp_body = new ProductCategoryDetailRespDto();
        ProductCategory record = product_category_dao.selectByCategoryCode(request.getBody().getCategory_code());
        ProductCategoryDto dto = new ProductCategoryDto();
        dto.setCategory_code(record.getCategory_code());
        dto.setCategory_name(record.getCategory_name());
        dto.setCategory_state(record.getCategory_state());
        resp_body.setCategory_info(dto);
        response.setBody(resp_body);
        return response;
    }

    @Override
    public AbstractResponse<ProductCategoryUpdateRespDto> update(ProductCategoryUpdateRequest request) {
        AbstractResponse<ProductCategoryUpdateRespDto> response = dtoFactory.createResponse(request.getHeader());
        ProductCategoryUpdateRespDto  resp_body = new ProductCategoryUpdateRespDto();
        ProductCategory record = product_category_dao.selectByCategoryCode(request.getBody().getCategory_code());
        ProductCategory update_record =new ProductCategory();
        update_record.setId(record.getId());
        if (StringUtils.isNotBlank(request.getBody().getCategory_name())){
            update_record.setCategory_name_defined(request.getBody().getCategory_name());
            update_record.setCategory_name(request.getBody().getCategory_name());
        }
        if (request.getBody().getCategory_state()!=null){
            update_record.setCategory_state(request.getBody().getCategory_state());
        }
        update_record.setUpdate_time(new Date());
        int count_result = product_category_dao.updateByPrimaryKeySelective(update_record);
        resp_body.setCategory_code(update_record.getCategory_code());
        response.setBody(resp_body);
        return response;
    }

    @Override
    public AbstractResponse<ProductCategoryDelRespDto> delete(ProductCategoryDelRequest request) {
        AbstractResponse<ProductCategoryDelRespDto> response = dtoFactory.createResponse(request.getHeader());
        ProductCategoryDelRespDto body = new ProductCategoryDelRespDto();
        int count = product_category_dao.deleteByCategoryCode(request.getBody().getCategory_code());
        body.setDel_count(count);
        body.setCategory_code(request.getBody().getCategory_code());
        response.setBody(body);
        return response;
    }
}
