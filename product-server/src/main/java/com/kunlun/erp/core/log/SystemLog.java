package com.kunlun.erp.core.log;

import com.kunlun.erp.core.common.constants.SysConstant;

import java.lang.annotation.*;

/**
 * @ClassName SystemLog
 * @Description 系统日志
 * @Author Jm.zhang
 * @Date 2019-11-17 12:00
 * @Version 1.0
 **/
@Target({ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface SystemLog {
    boolean value() default true;
    //日志输入，是否输出response body内容， 对于body内容很大的，没必要输出，可设置false
    boolean show_resp_body()default true;
    String logLevel() default SysConstant.LogLevel.info;
}
