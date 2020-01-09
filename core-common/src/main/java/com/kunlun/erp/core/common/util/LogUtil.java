package com.kunlun.erp.core.common.util;


import com.kunlun.erp.core.common.constants.LogConstant;
import com.kunlun.erp.core.common.constants.Urls;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @ClassName LogUtil
 * @Description 日志工具类
 * @Author Jm.zhang
 * @Date 2019/11/13 17:00
 * @Version 1.0
 **/
public class LogUtil {
    public Logger logger = LoggerFactory.getLogger("");
    private static LogUtil logUtil;
    public static LogUtil getInstance(String name_space) {
        if (logUtil == null) {
            logUtil = new LogUtil();
        }
        if (StringUtils.isNotBlank(name_space)){
            logUtil.logger = LoggerFactory.getLogger(getLoggerName(name_space));
        }else{
            logUtil.logger=LoggerFactory.getLogger(LogUtil.class);
        }

        return logUtil;
    }
    public static String toMessage(String class_name, String method_name, String description, String content){
        String sb = "\n" +
                "<=================================>" + "\n" +
                "	[call class]:" + class_name + "\n" +
                "	[call method]:" + method_name + "\n" +
                "	[call key]:" + description + "\n" +
                "	[call content]:" + content + "\n" +
                "<=================================>" + "\n";
        return sb;
    }

    /**
     * 自定义日志，INFO 级别输出
     * @param class_name
     * @param method_name
     * @param description
     * @param content
     * @param name_space
     */
    public static void writeLogInfo(String class_name, String method_name, String description, String content, String name_space) {
        LogUtil logUtil = LogUtil.getInstance(name_space);
        String message = toMessage(class_name,method_name,description,content);
        logUtil.logger.info(message);
    }

    /**
     * 自定义日志，DEBUG级别输出
     * @param class_name
     * @param method_name
     * @param description
     * @param content
     * @param name_space
     */
    public static void writeLogDebug(String class_name, String method_name, String description, String content, String name_space) {
        LogUtil logUtil = LogUtil.getInstance(name_space);
        String message = toMessage(class_name,method_name,description,content);
        logUtil.logger.debug(message);
    }


    public static void writeLog(String name_space, String class_name, String message) {
        LogUtil logUtil = LogUtil.getInstance(name_space);
        String sb = "\n" +
                "<=================================>" + "\n" +
                "	[class]:" + class_name + "\n" +
                "	[messages]:" + message + "\n" +
                "<=================================>" + "\n";
        logUtil.logger.info(sb);

    }
    private static String getLoggerName(String name_space) {
        if (StringUtils.isBlank(name_space)){
            return LogConstant.LoggerName.other_logger.getValue();
        }
        if (name_space.equals(Urls.USER_NAME_SPACE)){
            return LogConstant.LoggerName.user_logger.getValue();
        }
        if (name_space.equals(Urls.Company.NAMESPACE) || name_space.equals(Urls.Person.NAMESPACE)){
            return LogConstant.LoggerName.company_logger.getValue();
        }
        if (name_space.equals(Urls.Product.NAMESPACE)||name_space.equals(Urls.RouteHall.NAMESPACE)){
            return LogConstant.LoggerName.product_logger.getValue();
        }
        if (name_space.equals(Urls.RouteOrder.NAMESPACE)){
            return LogConstant.LoggerName.order_logger.getValue();
        }
        if (name_space.equals(Urls.FinanceManage.NAMESPACE)){
            return LogConstant.LoggerName.finance_logger.getValue();
        }



        return LogConstant.LoggerName.other_logger.getValue();

    }

}
