package com.kunlun.erp.core.common.constants;

/**
 * @ClassName ErrorCodeConstant
 * @Description 错误代码常量
 * @Author Jm.zhang
 * @Date 2019/11/14 10:46
 * @Version 1.0
 **/
public class ErrorCodeConstant {
    public static final String test_code = "001";
    public static final String test_code_a = "002";

    //非法请求
    public static final String REQUEST_ILLEGAL="0001";
    //系统内部异常
    public static final String INTERNAL_ERROR ="0002";
    //消息头异常代码
    public static final String REQUEST_HEADER_IS_INVALID = "1000";
    //请求消息体为空
    public static final String REQUEST_BODY_IS_INVALID = "1001";
    //请求流水号为空
    public static final String REQUEST_TRANS_CODE_IS_INVALID = "1002";
    //请求密钥为空
    public static final String REQUEST_SECRET_KEY_IS_INVALID = "1003";
    //校验密钥无响应
    public static final String NO_RESPONSE_CHECKING_SECRET_KEY = "1004";
    //没有权限
    public static final String NO_PERMISSIONS = "1005";
    //校验权限无响应
    public static final String NO_RESPONSE_VALIDATE_PERMISSIONS = "1006";
    //=============================================================================
    //货币
    public static final String SOURCE_CURRENCY_INVALID="1020";
    public static final String TARGET_CURRENCY_INVALID="1021";


    //=============================================================================
    //地区
    //国家类型无效
    public static final String AREA_COUNTRY_TYPE_INVALID = "1030";
    //国家ID无效
    public static final String AREA_COUNTRY_ID_INVALID = "1031";
    //大区ID无效
    public static final String AREA_DISTRICT_ID_INVALID = "1032";
    //省份ID无效
    public static final String AREA_PROVINCE_ID_INVALID = "1033";
    //城市ID无效
    public static final String AREA_CITY_ID_INVALID = "1034";
    //区域类型无效
    public static final String AREA_TYPE_INVALID = "1035";

    //=============================================================================
    //企业基础信息数据为空
    public static final String COMPANY_BASE_INFO_NULL = "500";
    //企业类型无效
    public static final String COMPANY_TYPE_IS_INVALID = "501";
    //企业名称无效
    public static final String COMPANY_NAME_MISSING = "502";
    //企业名称字符长度
    public static final String COMPANY_NAME_CHAR_OUT_LENGTH = "503";
    //所属平台无效
    public static final String COMPANY_BELONG_PLATFORM_IS_INVALID = "504";
    //经营许可证URL 超出最大长度
    public static final String BUSINESS_CERTIFICATE_URL_OUT_LENGTH = "505";
    //营业执照URL 超出最大长度
    public static final String BUSINESS_LICENCE_URL_OUT_LENGTH = "506";
    //合同附件URL 超出最大长度
    public static final String CONTRACT_DOCUMENT_URL_OUT_LENGTH = "507";
    //备注内容超出最大长度
    public static final String REMARKS_OUT_LENGTH = "508";
    //企业地址字符长度
    public static final String COMPANY_ADDRESS_CHAR_OUT_LENGTH = "509";
    //企业简介字符长度
    public static final String COMPANY_INTRODUCTION_CHAR_OUT_LENGTH = "510";
    //合作状态无效
    public static final String COOPERATION_STATE_MISSING= "511";
    //对接人无效
    public static final String CONTACT_PERSON_MISSING= "512";
    //合同状态无效
    public static final String CONTRACT_DOCUMENT_STATE_MISSING= "513";
    //合作开始日期
    public static final String COOPERATION_START_DATE_FORMAT_MISSING= "514";
    //合作结束日期
    public static final String COOPERATION_END_DATE_FORMAT_MISSING= "515";
    //合作结束日期
    public static final String COMPANY_NAME_EXIST= "516";
    //企业编号无效
    public static final String COMPANY_CODE_INVALID= "517";
    //=============================================================================
    //无效的区域数据
    public static final String AREA_DATA_NULL = "1050";
    //境内 境外数据无效
    public static final String REGION_TYPE_INVALID="1051";
    //区域ID无效
    public static final String DISTRICT_ID_INVALID="1052";
    //国家ID称无效
    public static final String COUNTRY_ID_INVALID="1053";
    //国家名称无效
    public static final String COUNTRY_NAME_INVALID="1054";
    //省份ID称无效
    public static final String PROVINCE_ID_INVALID="1055";
    //省份名称无效
    public static final String PROVINCE_NAME_INVALID="1056";
    //城市ID称无效
    public static final String CITY_ID_INVALID="1057";
    //城市名称无效
    public static final String CITY_NAME_INVALID="1058";
    //区县ID称无效
    public static final String COUNTY_ID_INVALID="1059";
    //区县名称无效
    public static final String COUNTY_NAME_INVALID="1060";
    //=============================================================================
    //无效的负责人数据
    public static final String LEADER_DATA_NULL = "1150";
    //无效的负责人数据【姓名】
    public static final String LEADER_NAME_INVALID = "1151";
    //无效的负责人数据【姓名最多32个字符】
    public static final String LEADER_NAME_OUT_LENGTH = "1152";
    //无效的负责人数据【手机号码】
    public static final String LEADER_MOBILE_INVALID = "1153";
    //无效的负责人数据【微信号】
    public static final String LEADER_WECHAT_INVALID = "1154";
    //无效的负责人数据【微信号】
    public static final String LEADER_ADDRESS_INVALID = "1155";
    //=============================================================================
    //无效的财务数据
    public static final String FINANCE_DATA_NULL = "1170";
    //结算周期
    public static final String FINANCE_SETTLEMENT_MODE_INVALID = "1171";
    //信用等级
    public static final String FINANCE_CREDIT_LEVEL_INVALID = "1172";
    //是否常用
    public static final String FINANCE_COMMON_USED_INVALID = "1173";
    //金融账户类型
    public static final String FINANCE_ACCOUNT_TYPE_INVALID = "1174";
    //金融账户名称
    public static final String FINANCE_ACCOUNT_NAME_INVALID = "1175";
    //金融账户号码
    public static final String FINANCE_ACCOUNT_NUMBER_INVALID = "1176";
    //金融账户开户行
    public static final String FINANCE_ACCOUNT_BANK_NAME_INVALID = "1177";
    //金融账户默认
    public static final String FINANCE_ACCOUNT_DEFAULT_INVALID = "1178";
    //金融账户编号无效
    public static final String FINANCE_ACCOUNT_CODE_INVALID = "1179";
    //=============================================================================
    //无效的联系人数据
    public static final String CONTACT_PERSON_DATA_NULL = "1200";
    //姓名
    public static final String CONTACT_PERSON_NAME_INVALID = "1201";
    //手机号码
    public static final String CONTACT_MOBILE_INVALID = "1202";
    //微信号
    public static final String CONTACT_WECHAT_INVALID = "1203";
    //固话号
    public static final String CONTACT_PHONE_INVALID = "1204";
    //qq号
    public static final String CONTACT_QQ_INVALID = "1205";
    //职务
    public static final String CONTACT_POSITION_INVALID = "1206";
    //人员类型
    public static final String CONTACT_TYPE_INVALID = "1207";
    //人员编号
    public static final String CONTACT_CODE_INVALID = "1208";

    //=============================================================================
    //销售渠道费用编号
    public static final String SALES_COST_CODE_INVALID = "1300";
    //渠道费用类型
    public static final String SALES_COST_TYPE_INVALID = "1301";
    //产品类型
    public static final String PRODUCT_CATEGORY_INVALID = "1302";
    //渠道结算模式
    public static final String SALES_COST_SETTLEMENT_MODEL_INVALID = "1303";
    //收费模式
    public static final String SALES_CHARGE_MODEL_INVALID = "1304";
    //费用
    public static final String SALES_COST_FEE_INVALID = "1305";
    //费率
    public static final String SALES_COST_RATE_INVALID = "1306";
    //=============================================================================
    //产品
    public static final String PRODUCT_CATEGORY_NAME_INVALID = "1400";
    //类别编号
    public static final String PRODUCT_CATEGORY_CODE_INVALID = "1401";
    //类别状态
    public static final String PRODUCT_CATEGORY_STATE_INVALID = "1402";
    //产品名称
    public static final String PRODUCT_NAME_INVALID = "1500";
    //是否购物
    public static  final String PRODUCT_IS_SHOPPING_INVALID = "1501";
    //是否购物
    public static  final String PRODUCT_OWNER_EXPENSE_INVALID = "1502";
    //用房信息
    public static  final String PRODUCT_ROOM_TYPE_INVALID = "1503";
    //保险信息
    public static  final String PRODUCT_INSURANCE_TYPE_INVALID = "1504";
    //线路产品基础信息块
    public static  final String PRODUCT_ROUTE_BASE_INFO_INVALID = "1505";
    //线路产品计划行程块
    public static  final String PRODUCT_ROUTE_PLAN_INFO_INVALID = "1506";
    //产品名称已存在
    public static final String PRODUCT_NAME_IS_EXIST = "1507";
    //产品不存在
    public static final String PRODUCT_IS_NOT_EXIST = "1508";
    //产品类别 不能被删除，因使用中
    public static final String PRODUCT_CATEGORY_DELETE_FAIL_DUE_TO_USING="1509";

    //线路行程计划名
    public static  final String PRODUCT_ROUTE_PLAN_NAME_INVALID = "1520";
    //线路行程计是否默认
    public static  final String PRODUCT_ROUTE_PLAN_DEFAULT_INVALID = "1521";
    //线路行程天数、白天
    public static  final String PRODUCT_ROUTE_PLAN_DAYS_INVALID = "1522";
    //线路行程天数、夜晚
    public static  final String PRODUCT_ROUTE_PLAN_NIGHTS_INVALID = "1523";

    //线路行程不存在
    public static  final String PRODUCT_ROUTE_PLAN_NOT_EXIST = "1524";
    //=============================================================================
    //行程节点
    //日期，第几天
    public static  final String PRODUCT_ROUTE_PLAN_NODE_DAY_INVALID = "2000";
    //交通工具
    public static  final String PRODUCT_ROUTE_PLAN_NODE_TRAFFIC_TYPE_INVALID = "2001";
    //早餐
    public static  final String PRODUCT_ROUTE_PLAN_NODE_HAS_BREAKFAST_INVALID = "2002";
    //午餐
    public static  final String PRODUCT_ROUTE_PLAN_NODE_HAS_LUNCH_INVALID = "2003";
    //晚餐
    public static  final String PRODUCT_ROUTE_PLAN_NODE_HAS_DINNER_INVALID = "2004";
    //节点块无效
    public static  final String PRODUCT_ROUTE_PLAN_NODE_INVALID = "2005";
    //节点不存在
    public static  final String PRODUCT_ROUTE_PLAN_NODE_NOT_EXIST = "2006";

    //=============================================================================
    //行程报价
    public static  final String PRODUCT_ROUTE_PLAN_FEE_TYPE_INVALID = "2050";
    //货币
    public static  final String PRODUCT_ROUTE_PLAN_CURRENCY_INVALID = "2051";
    //货币
    public static  final String PRODUCT_ROUTE_PLAN_PRICE_INVALID = "2052";
    //数量
    public static  final String PRODUCT_ROUTE_PLAN_QUANTITY_INVALID = "2053";
    //总价
    public static  final String PRODUCT_ROUTE_PLAN_TOTAL_PRICE_INVALID = "2054";
    //总价
    public static  final String PRODUCT_ROUTE_PLAN_BASE_PRICE_NOT_EXIST = "2055";
    //=============================================================================
    //价格方案
    public static  final String PRICE_PLAN_DETAIL_INVALID = "2100";
    public static  final String PRICE_DETAIL_DEPARTURE_DATE_INVALID = "2101";
    public static  final String PRICE_DETAIL_ADULT_TRADE_PRICE_INVALID = "2102";
    public static  final String PRICE_DETAIL_CHILDREN_TRADE_PRICE_INVALID = "2103";
    public static  final String PRICE_DETAIL_ADULT_SALES_PRICE_INVALID = "2104";
    public static  final String PRICE_DETAIL_CHILDREN_SALES_PRICE_INVALID = "2105";
    public static  final String PRICE_DETAIL_ROOM_DIFF_PRICE_INVALID = "2106";
    public static  final String PRICE_DETAIL_INVENTORY_INVALID = "2107";
    public static  final String PRICE_DETAIL_COST_PRICE_INVALID = "2108";
    public static  final String PRICE_PLAN_CODE_INVALID = "2109";
    public static  final String PRICE_PLAN_MAX_SIZE_LIMIT = "2110";
    public static  final String PRICE_PLAN_NAME_DUPLICATE = "2111";
    public static  final String PRICE_PLAN_NAME_INVALID = "2112";
    //=============================================================================
    //价格日历的成本明细
    public static  final String ROUTE_COST_CODE_INVALID = "2130";
    //成本类型
    public static  final String ROUTE_COST_TYPE_INVALID = "2131";
    //货币
    public static  final String ROUTE_COST_CURRENCY_INVALID = "2132";
    //单价
    public static  final String ROUTE_COST_PRICE_INVALID = "2133";
    //数量
    public static  final String ROUTE_COST_QUANTITY_INVALID = "2134";
    //总价
    public static  final String ROUTE_COST_TOTAL_PRICE_INVALID = "2135";
    //=============================================================================
    //线路团状态无效
    public static  final String HALL_DAILY_STATE_INVALID = "2199";
    //线路团号不存在
    public static  final String HALL_DAILY_CODE_INVALID = "2200";
    //导服-带团人数
    public static  final String GUIDE_CLIENT_COUNT_INVALID = "2201";
    //导服-带团日期
    public static  final String GUIDE_START_DATE_INVALID = "2202";
    //导服-送团日期
    public static  final String GUIDE_END_DATE_INVALID = "2203";
    //导服-带团天数
    public static  final String GUIDE_DAYS_INVALID = "2204";
    //导服-货币
    public static  final String GUIDE_CURRENCY_INVALID = "2205";
    //导服-服务费
    public static  final String GUIDE_DAILY_FEE_INVALID = "2206";
    //导服-服务费总计
    public static  final String GUIDE_TOTAL_FEE_INVALID = "2207";
    //导服-记录不存在
    public static  final String GUIDE_CODE_INVALID = "2208";
    //住宿-房间类型
    public static  final String RESIDE_ROOM_TYPE_INVALID = "2220";
    //住宿-入住日期
    public static  final String RESIDE_START_DATE_INVALID = "2221";
    //住宿-离店日期
    public static  final String RESIDE_END_DATE_INVALID = "2222";
    //住宿-入住天数
    public static  final String RESIDE_DAYS_INVALID = "2223";
    //住宿-货币
    public static  final String RESIDE_CURRENCY_INVALID = "2224";
    //住宿-单价
    public static  final String RESIDE_FEE_INVALID = "2225";
    //住宿-房间数量
    public static  final String RESIDE_ROOM_COUNT_INVALID = "2226";
    //住宿-总价
    public static  final String RESIDE_TOTAL_FEE_INVALID = "2227";
    //住宿-记录不存在
    public static  final String RESIDE_CODE_INVALID = "2228";


    //景点门票-门票类型
    public static  final String TICKET_TYPE_INVALID = "2250";
    //景点门票-游玩日期
    public static  final String TICKET_DATE_INVALID = "2251";
    //景点门票-货币
    public static  final String TICKET_CURRENCY_INVALID = "2252";
    //景点门票-单价
    public static  final String TICKET_PRICE_INVALID = "2253";
    //景点门票-数量
    public static  final String TICKET_COUNT_INVALID = "2254";
    //景点门票-总价
    public static  final String TICKET_TOTAL_PRICE_INVALID = "2255";
    //景点门票-记录不存在
    public static  final String TICKET_CODE_INVALID = "2256";

    //用餐-类型
    public static  final String MEAL_TYPE_INVALID = "2270";
    //用餐-日期
    public static  final String MEAL_DATE_INVALID = "2271";
    //用餐-货币
    public static  final String MEAL_CURRENCY_INVALID = "2272";
    //用餐-单价
    public static  final String MEAL_PRICE_INVALID = "2273";
    //用餐-人数
    public static  final String MEAL_DINNERS_COUNT_INVALID = "2274";
    //用餐-免费人数
    public static  final String MEAL_FREE_COUNT_INVALID = "2275";
    //用餐-总价
    public static  final String MEAL_TOTAL_PRICE_INVALID = "2276";
    //用餐-记录不存在
    public static  final String MEAL_CODE_INVALID = "2277";

    //车队-用车日期
    public static  final String MOTORCADE_START_DATE_INVALID = "2280";
    //车队-返程日期
    public static  final String MOTORCADE_END_DATE_INVALID = "2281";
    //用车-货币
    public static  final String MOTORCADE_CURRENCY_INVALID = "2282";
    //用车-单价
    public static  final String MOTORCADE_PRICE_INVALID = "2283";
    //用车-数量
    public static  final String MOTORCADE_CAR_COUNT_INVALID = "2284";
    //用车-总价
    public static  final String MOTORCADE_TOTAL_PRICE_INVALID = "2285";
    //用车-记录不存在
    public static  final String MOTORCADE_CODE_INVALID = "2286";

    //保险-类型
    public static  final String INSURANCE_TYPE_INVALID = "2290";
    //保险-开始日期
    public static  final String INSURANCE_START_DATE_INVALID = "2291";
    //保险-结束日期
    public static  final String INSURANCE_END_DATE_INVALID = "2292";
    //保险-货币
    public static  final String INSURANCE_CURRENCY_INVALID = "2293";
    //保险-单价
    public static  final String INSURANCE_PRICE_INVALID = "2294";
    //保险-数量
    public static  final String INSURANCE_COUNT_INVALID = "2295";
    //保险-总价
    public static  final String INSURANCE_TOTAL_PRICE_INVALID = "2296";
    //保险-记录不存在
    public static  final String INSURANCE_CODE_INVALID = "2297";

    //其他-费用类型
    public static  final String OTHER_FEE_TYPE_INVALID = "2300";
    //其他-货币
    public static  final String OTHER_CURRENCY_INVALID = "2301";
    //其他-单价
    public static  final String OTHER_PRICE_INVALID = "2302";
    //其他-数量
    public static  final String OTHER_COUNT_INVALID = "2303";
    //其他-总价
    public static  final String OTHER_TOTAL_PRICE_INVALID = "2304";
    //其他-记录不存在
    public static  final String OTHER_CODE_INVALID = "2305";

    //交通票务-交通工具
    public static  final String ROUTE_TRAFFIC_TYPE_INVALID = "2310";
    //交通票务-出发日期
    public static  final String ROUTE_TRAFFIC_DEPARTURE_DATE_INVALID = "2311";
    //交通票务-始发地
    public static  final String ROUTE_TRAFFIC_DEPARTURE_INVALID = "2312";
    //交通票务-目的地
    public static  final String ROUTE_TRAFFIC_DESTINATION_INVALID = "2313";
    //交通票务-货币
    public static  final String ROUTE_TRAFFIC_CURRENCY_INVALID = "2314";
    //交通票务-单价
    public static  final String ROUTE_TRAFFIC_FEE_INVALID = "2315";
    //交通票务-数量
    public static  final String ROUTE_TRAFFIC_COUNT_INVALID = "2316";
    //交通票务-总价
    public static  final String ROUTE_TRAFFIC_TOTAL_FEE_INVALID = "2317";
    //交通票务-不存在的数据
    public static  final String ROUTE_TRAFFIC_CODE_INVALID = "2318";


    //订单不可编辑
    public static  final String ROUTE_ORDER_NO_EDIT = "2399";
    //线路订单-不存在
    public static  final String ROUTE_ORDER_CODE_INVALID = "2400";
    //线路订单-用房标准
    public static  final String ROUTE_ORDER_ROOM_LEVEL_INVALID = "2401";
    //线路订单-标准房数量
    public static  final String ROUTE_ORDER_STANDARD_ROOM_COUNT_INVALID = "2402";
    //线路订单-大床房数量
    public static  final String ROUTE_ORDER_BIG_ROOM_COUNT_INVALID = "2403";
    //线路订单-三人间数量
    public static  final String ROUTE_ORDER_THREE_ROOM_COUNT_INVALID = "2404";
    //线路订单-陪房数量
    public static  final String ROUTE_ORDER_ACCOMPANY_ROOM_COUNT_INVALID = "2405";
    //线路订单-不用房数量
    public static  final String ROUTE_ORDER_NO_ROOM_COUNT_INVALID = "2406";
    //线路订单-成人数量
    public static  final String ROUTE_ORDER_ADULT_COUNT_INVALID = "2407";
    //线路订单-儿童数量
    public static  final String ROUTE_ORDER_CHILDREN_COUNT_INVALID = "2408";
    //线路订单-状态
    public static  final String ROUTE_ORDER_STATE_INVALID = "2409";
    //报名人数已上限
    public static  final String ROUTE_ORDER_PEOPLE_UPPER_LIMIT = "2410";
    //报名截止
    public static  final String ROUTE_ORDER_NO_CREATE_DUE_TO_STATUS = "2411";
    //销售团款编号不存在
    public static  final String ROUTE_ORDER_INCOME_CODE_INVALID = "2422";
    //销售团款类型
    public static  final String ROUTE_ORDER_INCOME_FEE_TYPE_INVALID = "2423";
    //销售团款货币
    public static  final String ROUTE_ORDER_INCOME_CURRENCY_INVALID = "2424";
    //销售团款单价
    public static  final String ROUTE_ORDER_INCOME_PRICE_INVALID = "2425";
    //销售团款数量
    public static  final String ROUTE_ORDER_INCOME_SIZE_INVALID = "2426";
    //销售团款总价
    public static  final String ROUTE_ORDER_INCOME_TOTAL_PRICE_INVALID = "2427";

    //上传出游人数量大于设定值
    public static  final String ROUTE_CLIENT_GREATER_THAN_SETTING = "2430";
    //手机号码无效
    public static  final String ROUTE_CLIENT_MOBILE_INVALID = "2432";
    //出游人证件类型无效
    public static  final String ROUTE_CLIENT_ID_TYPE_INVALID = "2433";
    //出游人身份证号码无效
    public static  final String ROUTE_CLIENT_ID_NUMBER_INVALID = "2434";
    //出游人护照号码无效
    public static  final String ROUTE_CLIENT_PASSPORT_NUMBER_INVALID = "2435";
    //出游人类别无效
    public static  final String ROUTE_CLIENT_CATEGORY_INVALID = "2436";

    //出游人出生日期
    public static  final String ROUTE_CLIENT_BIRTHDAY_INVALID = "2437";
    //出生日期与身份号不匹配
    public static  final String ROUTE_CLIENT_BIRTHDAY_NOT_MATCH_WITH_ID_NUMBER = "2438";
    //出生日期与年龄
    public static  final String ROUTE_CLIENT_BIRTHDAY_NOT_MATCH_WITH_AGE = "2439";
    //身份证与性别不匹配
    public static  final String ROUTE_CLIENT_ID_NUMBER_NOT_MATCH_WITH_SEX = "2440";
    //出游人不存在
    public static  final String ROUTE_CLIENT_CODE_INVALID = "2441";
    //出游人交通票务状态无效
    public static  final String ROUTE_CLIENT_WITH_TRAFFIC_TICKET_STATUS_INVALID = "2442";

    //旅行社不存在
    public static  final String ROUTE_TRAVEL_AGENCY_CODE_INVALID = "2460";
    //地接款数据不存在
    public static  final String ROUTE_TRAVEL_INCOME_CODE_INVALID = "2461";
    //地接款类型
    public static  final String ROUTE_TRAVEL_INCOME_FEE_TYPE_INVALID = "2462";
    //地接款货币
    public static  final String ROUTE_TRAVEL_INCOME_CURRENCY_INVALID = "2463";
    //地接款单价
    public static  final String ROUTE_TRAVEL_INCOME_PRICE_INVALID = "2464";
    //地接款数量
    public static  final String ROUTE_TRAVEL_INCOME_SIZE_INVALID = "2465";
    //地接款总价
    public static  final String ROUTE_TRAVEL_INCOME_TOTAL_PRICE_INVALID = "2466";

    //财务，审核状态无效
    public static  final String ROUTE_AUDIT_STATUS_INVALID = "2500";







}
