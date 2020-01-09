package com.kunlun.erp.core.service.account;

import com.kunlun.erp.core.dto.AbstractResponse;
import com.kunlun.erp.core.dto.user.HasPermissionRespDto;

/**
 * @InterfaceName PermissionService
 * @Description 权限接业务接口
 * @Author Jm.zhang
 * @Date 2019/11/22 17:51
 * @Version 1.0
 **/
public interface PermissionService {
    AbstractResponse initPermission();

    AbstractResponse<HasPermissionRespDto> getUserByPermission(String trans_no,String secret_key,String permission_key);

    String getPermissionKey(Object object);

}