package com.kunlun.erp.core.common.constants;

/**
 * @ClassName LogConstant
 * @Description 日志常量
 * @Author Jm.zhang
 * @Date 2019/11/13 16:57
 * @Version 1.0
 **/
public class LogConstant {
    /**
     * 日志级别
     */
    public enum LoggerLevel{
        OFF("OFF","OFF"),
        FATAL("FATAL","FATAL"),
        ERROR("ERROR","ERROR"),
        WARN("WARN","WARN"),
        INFO("INFO","INFO"),
        DEBUG("DEBUG","DEBUG"),
        TRACE("TRACE","TRACE"),
        ALL("ALL","ALL");
        private String name,value;
        LoggerLevel(String name,String value){
            this.name=name;
            this.value=value;
        }
    }
    /**
     * logger name
     */
    public enum LoggerName{
        user_logger("账户日志","user_logger"),
        company_logger("企业信息，包含了供应商 销售渠道","company_logger"),
        product_logger("产品信息，包含了产品类别 和产品","product_logger"),
        order_logger("订单信息，包含了报名大厅  订单","order_logger"),
        finance_logger("财务管理","finance_logger"),
        other_logger("其他拦截","other_logger");
        private final String name,value;
        LoggerName(String name,String value){
            this.name=name;
            this.value=value;
        }
        public static LoggerName getLoggerName(String val){
            for (LoggerName sm : LoggerName.values()){
                if (val.equals(sm.getValue())){
                    return sm;
                }
            }
            return null;
        }
        public String getName() {
            return name;
        }

        public String getValue() {
            return value;
        }

    }
}
