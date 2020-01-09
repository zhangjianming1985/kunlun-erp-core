package com.kunlun.erp.core.common.constants;

/**
 * @ClassName Urls
 * @Description 接口地址
 * @Author Jm.zhang
 * @Date 2019/11/14 11:19
 * @Version 1.0
 **/
public class Urls {
    public static final String test = "/api/v1/test";


    /**
     * 根据请求地址，获取命名空间
     * @param request_url
     * @return
     */
    public static String getUrlNameSpace(String request_url){
        if (request_url.indexOf(Company.NAMESPACE)>-1 || request_url.indexOf(Financial.NAMESPACE)>-1){
            return Company.NAMESPACE;
        }else if (request_url.indexOf(Person.NAMESPACE)>-1){
            return Person.NAMESPACE;
        }else if (request_url.indexOf(Area.NAMESPACE)>-1){
            return Area.NAMESPACE;
        }else if (request_url.indexOf(Product.NAMESPACE)>-1){
            return Product.NAMESPACE;
        }else if (request_url.indexOf(RouteHall.NAMESPACE)>-1){
            return RouteHall.NAMESPACE;
        }else if (request_url.indexOf(RouteOrder.NAMESPACE)>-1){
            return RouteOrder.NAMESPACE;
        }else if (request_url.indexOf(FinanceManage.NAMESPACE)>-1){
            return FinanceManage.NAMESPACE;
        }
        return null;
    }

    public static final String USER_NAME_SPACE="/user/";
    public static final String CONTENT_TYPE_JSON = "application/json";
    public static final String CONTENT_TYPE_PLAIN = "application/plain";

    public static final String VERSION = "api/v1";
    /**
     * 销售渠道
     */
    public class SalesChannel {
        /**
         * 命名空间
         */
        public static final String NAMESPACE = "/salesChannel/";
        /**
         * 列表
         */
        public static final String LIST = "/" + VERSION  + NAMESPACE + "list";
        /**
         * 增加
         */
        public static final String ADD = "/" + VERSION + NAMESPACE + "add";
        /**
         * 详情
         */
        public static final String DETAIL = "/" + VERSION  + NAMESPACE + "detail";
        /**
         * 更新
         */
        public static final String UPDATE = "/" + VERSION + NAMESPACE + "update";
        /**
         * 删除
         */
        public static final String DELETE = "/" + VERSION + NAMESPACE + "delete";
    }

    /**
     * 企业
     */
    public class Company {
        /**
         * 命名空间
         */
        public static final String NAMESPACE = "/company/";
        /**
         * 列表
         */
        public static final String LIST = "/" + VERSION  + NAMESPACE + "list";
        /**
         * 增加
         */
        public static final String ADD = "/" + VERSION + NAMESPACE + "add";
        /**
         * 详情
         */
        public static final String DETAIL = "/" + VERSION  + NAMESPACE + "detail";
        /**
         * 更新
         */
        public static final String UPDATE = "/" + VERSION + NAMESPACE + "update";
        /**
         * 删除
         */
        public static final String DELETE = "/" + VERSION + NAMESPACE + "delete";

        /**
         * 模糊检索企业名称
         */
        public static final String LIKE_NAME = "/" + VERSION + NAMESPACE + "likeName";
    }

    /**
     * 财务
     */
    public class Financial {
        /**
         * 命名空间
         */
        public static final String NAMESPACE = "/financial/";
        /**
         * 金融账户列表
         */
        public static final String ACCOUNT_LIST = "/" + VERSION  + NAMESPACE + "account/list";

    }

    /**
     * 人员
     */
    public class Person {
        /**
         * 命名空间
         */
        public static final String NAMESPACE = "/person/";
        /**
         * 列表
         */
        public static final String LIST = "/" + VERSION  + NAMESPACE + "list";
        /**
         * 增加
         */
        public static final String ADD = "/" + VERSION + NAMESPACE + "add";
        /**
         * 详情
         */
        public static final String DETAIL = "/" + VERSION  + NAMESPACE + "detail";
        /**
         * 更新
         */
        public static final String UPDATE = "/" + VERSION + NAMESPACE + "update";
        /**
         * 删除
         */
        public static final String DELETE = "/" + VERSION + NAMESPACE + "delete";
        /**
         * 模糊检索人员数据
         */
        public static final String LIKE_PERSON = "/" + VERSION + NAMESPACE + "likePerson";
    }
    /**
     * 地区
     */
    public class Area {
        /**
         * 命名空间
         */
        public static final String NAMESPACE = "/area/";
        /**
         * 检索
         */
        public static final String QUERY = "/" + VERSION  + NAMESPACE + "query";
        /**
         * 列表
         */
        public static final String LIST = "/" + VERSION  + NAMESPACE + "list";
        /**
         * 增加
         */
        public static final String ADD = "/" + VERSION + NAMESPACE + "add";
        /**
         * 详情
         */
        public static final String DETAIL = "/" + VERSION  + NAMESPACE + "detail";
        /**
         * 更新
         */
        public static final String UPDATE = "/" + VERSION + NAMESPACE + "update";
        /**
         * 删除
         */
        public static final String DELETE = "/" + VERSION + NAMESPACE + "delete";
    }

    /**
     * 产品
     */
    public class Product {
        /**
         * 命名空间
         */
        public static final String NAMESPACE = "/product/";

        /**
         * 分类列表
         */
        public static final String CATEGORY_LIST = "/" + VERSION  + NAMESPACE + "category/list";
        /**
         * 分类增加
         */
        public static final String CATEGORY_ADD = "/" + VERSION + NAMESPACE + "category/add";
        /**
         * 分类详情
         */
        public static final String CATEGORY_DETAIL = "/" + VERSION  + NAMESPACE + "category/detail";
        /**
         * 分类更新
         */
        public static final String CATEGORY_UPDATE = "/" + VERSION + NAMESPACE + "category/update";
        /**
         * 分类删除
         */
        public static final String CATEGORY_DELETE = "/" + VERSION + NAMESPACE + "category/delete";


        /**
         * 产品列表
         */
        public static final String LIST = "/" + VERSION  + NAMESPACE + "list";
        /**
         * 增加产品
         */
        public static final String ADD = "/" + VERSION + NAMESPACE + "add";
        /**
         * 产品详情
         */
        public static final String DETAIL = "/" + VERSION  + NAMESPACE + "detail";
        /**
         * 更新更新
         */
        public static final String UPDATE = "/" + VERSION + NAMESPACE + "update";
        /**
         * 复制产品
         */
        public static final String COPY = "/" + VERSION + NAMESPACE + "copy";
        /**
         * 删除产品
         */
        public static final String DELETE = "/" + VERSION + NAMESPACE + "delete";
    }

    /**
     * 线路产品计划方案
     */
    public class RoutePlan {
        /**
         * 命名空间
         */
        public static final String NAMESPACE = "/product/route/plan/";

        /**
         * 列表
         */
        public static final String LIST = "/" + VERSION  + NAMESPACE + "list";

    }
    /**
     * 线路产品价格
     */
    public class RoutePrice {
        /**
         * 命名空间
         */
        public static final String NAMESPACE = "/product/route/price/";
        /**
         * 设置价格套餐
         */
        public static final String ADD = "/" + VERSION + NAMESPACE + "add";
        /**
         * 修改价格套餐名称
         */
        public static final String UPDATE_PRICE_PLAN_NAME = "/" + VERSION + NAMESPACE + "name/update";
        /**
         * 列表
         */
        public static final String LIST = "/" + VERSION  + NAMESPACE + "list";
        /**
         * 详情
         */
        public static final String DETAIL = "/" + VERSION  + NAMESPACE + "detail";
        /**
         * 删除价格套餐
         */
        public static final String DELETE = "/" + VERSION  + NAMESPACE + "delete";
        /**
         * 删除价格日期
         */
        public static final String DELETE_DATE = "/" + VERSION  + NAMESPACE + "deleteDate";
        /**
         * 成本明细
         */
        public static final String COST_DETAIL = "/" + VERSION  + NAMESPACE + "costDetail";




    }

    public class FileHandler{
        /**
         * 命名空间
         */
        public static final String NAMESPACE = "/file/";
        /**
         * 上传
         */
        public static final String upload = "/" + VERSION + NAMESPACE + "upload";
    }

    /**
     * 财务管理
     */
    public class FinanceManage{
        /**
         * 命名空间
         */
        public static final String NAMESPACE = "/finance/";
        /**
         * 发团审核产品列表，行程结束
         */
        public static final String FINISH_ROUTE = "/" + VERSION  + NAMESPACE + "route/finish";
        /**
         * 审核已结束团的财务数据
         */
        public static final String AUDIT_ROUTE = "/" + VERSION  + NAMESPACE + "route/audit";
        /**
         * 财务审核结果
         */
        public static final String AUDIT_RESULT = "/" + VERSION  + NAMESPACE + "route/audit/result";

        /**
         * 销售渠道应收款列表
         */
        public static final String COLLECT_LIST = "/" + VERSION  + NAMESPACE + "collect/list";
        /**
         * 供应商应收应付款列表
         */
        public static final String PAYMENT_LIST = "/" + VERSION  + NAMESPACE + "payment/list";
    }

    /**
     * 报名大厅
     */
    public class RouteHall {
        /**
         * 命名空间
         */
        public static final String NAMESPACE = "/routeHall/";

        /**
         * 产品列表
         */
        public static final String LIST = "/" + VERSION  + NAMESPACE + "list";




        /**
         * 导服列表
         */
        public static final String LIST_GUIDES = "/" + VERSION  + NAMESPACE + "guide/list";
        /**
         * 住宿列表
         */
        public static final String LIST_RESIDE = "/" + VERSION  + NAMESPACE + "reside/list";
        /**
         * 景点门票列表
         */
        public static final String LIST_TICKET = "/" + VERSION  + NAMESPACE + "ticket/list";
        /**
         * 用餐列表
         */
        public static final String LIST_MEAL = "/" + VERSION  + NAMESPACE + "meal/list";
        /**
         * 车队列表
         */
        public static final String LIST_MOTORCADE = "/" + VERSION  + NAMESPACE + "motorcade/list";
        /**
         * 地接旅行社
         */
        public static final String DETAIL_TRAVEL_AGENCY = "/" + VERSION  + NAMESPACE + "travelAgency/detail";
        /**
         * 交通票务列表
         */
        public static final String LIST_TRAFFIC = "/" + VERSION  + NAMESPACE + "traffic/list";
        /**
         * 出游人列表
         */
        public static final String LIST_CLIENT = "/" + VERSION  + NAMESPACE + "client/list";
        /**
         * 其他列表
         */
        public static final String LIST_OTHER = "/" + VERSION  + NAMESPACE + "other/list";
        /**
         * 保险列表
         */
        public static final String LIST_INSURANCE = "/" + VERSION  + NAMESPACE + "insurance/list";

        /**
         * 增加产品
         */
        public static final String ADD = "/" + VERSION + NAMESPACE + "add";

        /**
         * 设置导服数据
         */
        public static final String ADD_GUIDE = "/" + VERSION + NAMESPACE + "guide/add";
        /**
         * 设置住宿数据
         */
        public static final String ADD_RESIDE = "/" + VERSION + NAMESPACE + "reside/add";
        /**
         * 设置景点门票数据
         */
        public static final String ADD_TICKET = "/" + VERSION + NAMESPACE + "ticket/add";
        /**
         * 设置用餐数据
         */
        public static final String ADD_MEAL = "/" + VERSION + NAMESPACE + "meal/add";
        /**
         * 设置车队数据
         */
        public static final String ADD_MOTORCADE = "/" + VERSION + NAMESPACE + "motorcade/add";
        /**
         * 设置地接旅行社数据
         */
        public static final String ADD_TRAVEL_AGENCY = "/" + VERSION + NAMESPACE + "travelAgency/add";
        /**
         * 设置交通票务数据
         */
        public static final String ADD_TRAFFIC = "/" + VERSION + NAMESPACE + "traffic/add";
        /**
         * 设置其他数据
         */
        public static final String ADD_OTHER = "/" + VERSION + NAMESPACE + "other/add";
        /**
         * 设置保险数据
         */
        public static final String ADD_INSURANCE = "/" + VERSION + NAMESPACE + "insurance/add";
        /**
         * 产品详情
         */
        public static final String DETAIL = "/" + VERSION  + NAMESPACE + "detail";
        /**
         * 状态更新
         */
        public static final String UPDATE_STATE = "/" + VERSION + NAMESPACE + "/state/update";
        /**
         * 删除产品
         */
        public static final String DELETE = "/" + VERSION + NAMESPACE + "delete";
        /**
         * 收支统计
         */
        public static final String STATISICS="/" + VERSION + NAMESPACE + "statistics";
    }

    /**
     * 线路订单
     */
    public class RouteOrder {
        /**
         * 命名空间
         */
        public static final String NAMESPACE = "/routeOrder/";
        /**
         * 订单列表
         */
        public static final String LIST = "/" + VERSION  + NAMESPACE + "list";
        /**
         * 出游人列表
         */
        public static final String LIST_CLIENT = "/" + VERSION  + NAMESPACE + "client/list";
        /**
         * 创建订单
         */
        public static final String ADD = "/" + VERSION + NAMESPACE + "add";

        /**
         * 出游人管理
         */
        public static final String ADD_CLIENT = "/" + VERSION + NAMESPACE + "client/add";

        /**
         * 订单详情
         */
        public static final String DETAIL = "/" + VERSION  + NAMESPACE + "detail";
        /**
         * 更新更新
         */
        public static final String UPDATE = "/" + VERSION + NAMESPACE + "update";
        /**
         * 删除订单
         */
        public static final String DELETE = "/" + VERSION + NAMESPACE + "delete";
    }

    /**
     * 通用
     */
    public class Common{
        /**
         * 命名空间
         */
        public static final String NAMESPACE = "/common/";
        /**
         * 汇率
         */
        public static final String RATE="/" + VERSION + NAMESPACE + "rate";

    }
}
