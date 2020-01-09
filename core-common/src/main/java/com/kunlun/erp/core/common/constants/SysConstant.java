package com.kunlun.erp.core.common.constants;

import org.apache.commons.lang3.StringUtils;

/**
 * @ClassName SysConstant
 * @Description 系统常量
 * @Author Jm.zhang
 * @Date 2019/11/13 17:07
 * @Version 1.0
 **/
public class SysConstant {
    /**
     * 系统日志级别
     */
    public static class LogLevel {
        public static final String info="info";
        public static final String debug="debug";
        public static final String warning="warning";
        public static final String error="error";
        public static final String trace="trace";
    }


    /**
     * 系统全局响应状态
     */
    public  enum RespStatus {
        resp_status_success("响应成功", "success"), resp_status_fail("响应失败", "fail"), resp_status_pending("等待结果", "pending");
        private final String name, value;

        RespStatus(String name, String value) {
            this.name = name;
            this.value = value;
        }

        public String getName() {
            return name;
        }

        public String getValue() {
            return value;
        }
    }

    /**
     * 文件上传响应状态
     */
    public  enum FileUploadStatus {
        resp_status_success("响应成功", "done"), resp_status_fail("响应失败", "error");
        private final String name, value;

        FileUploadStatus(String name, String value) {
            this.name = name;
            this.value = value;
        }

        public String getName() {
            return name;
        }

        public String getValue() {
            return value;
        }
    }

    /**
     * 企业类型：0=线上OT销售渠道、1=线下门店销售渠道、2=其他销售渠道、3=旅行社、4=车队供应商、5=酒店住宿供应商、6=餐饮供应商、7=景区供应商、8=票务供应商、9=保险供应商、10=导服供应商、11=其他供应商
     */
    public  enum CompanyType {
        sales_channel_online("线上OT销售渠道", 0),
        sales_channel_offline("线下门店销售渠道", 1),
        sales_channel_other("其他销售渠道", 2),
        supplier_travel_agency("旅行社供应商",3),
        supplier_car("车队供应商",4),
        supplier_hotel("酒店住宿供应商",5),
        supplier_meal("餐饮供应商",6),
        supplier_ticket("景区供应商",7),
        supplier_traffic("票务供应商",8),
        supplier_insurance("保险供应商",9),
        supplier_guides("导服供应商",10),
        supplier_other("其他供应商",11),
        all_sales_channel("所有销售渠道",20),
        all_supplier("所有供应商",21),
        all("所有企业",22);

        private final String name;
        private final Integer value;

        CompanyType(String name, Integer value) {
            this.name = name;
            this.value = value;
        }
        public static CompanyType getCompanyType(Integer val){
            for (CompanyType ps :CompanyType.values()){
                if (val == ps.getValue()){
                    return ps;
                }
            }
            return null;
        }

        public String getName() {
            return name;
        }

        public Integer getValue() {
            return value;
        }
    }

    /**
     * 销售渠道所属平台，0=飞猪、1=携程、2=美团、3=去哪儿、4=马蜂窝、5=同程艺龙、6=途牛、7=驴妈妈、8=蜗友行、9=魔方云仓
     */
    public  enum BelongPlatform {
        feizhu("飞猪", 0),
        xiecheng("携程", 1),
        meituan("美团", 2),
        quna("去哪儿",3),
        mafengwo("马蜂窝",4),
        yilong("同城艺龙",5),
        tuniu("途牛",6),
        lvmama("驴妈妈",7),
        woyouxing("蜗友行",8),
        mofangyuncang("魔方云仓",9);
        private final String name;
        private final Integer value;

        BelongPlatform(String name, Integer value) {
            this.name = name;
            this.value = value;
        }
        public static BelongPlatform getBelongPlatform(Integer val){
            for (BelongPlatform ps : BelongPlatform.values()){
                if (val == ps.getValue()){
                    return ps;
                }
            }
            return null;
        }

        public String getName() {
            return name;
        }

        public Integer getValue() {
            return value;
        }
    }



    /**
     * 合作状态  0=未合作、1=已合作、2=洽谈中
     */
    public enum  CooperationState{
        no_cooperation("未合作", 0),
        cooperation_in_progress("已合作", 1),
        in_negotiation("洽谈中", 2);
        private final String name;
        private final Integer value;

        CooperationState(String name, Integer value) {
            this.name = name;
            this.value = value;
        }

        public static CooperationState getCooperationState(Integer val){
            for (CooperationState ps :CooperationState.values()){
                if (val == ps.getValue()){
                    return ps;
                }
            }
            return null;
        }

        public String getName() {
            return name;
        }

        public Integer getValue() {
            return value;
        }
    }

    /**
     *  合同签署状态，0=未签署、1=已签署
     */
    public enum  ContractDocumentState{
        no_contract ("未签署", 0),
        contract_in_progress("已签署", 1);
        private final String name;
        private final Integer value;

        ContractDocumentState(String name, Integer value) {
            this.name = name;
            this.value = value;
        }
        public static ContractDocumentState getContractDocumentState(Integer val){
            if (val==null)return null;
            for (ContractDocumentState ps :ContractDocumentState.values()){
                if (val == ps.getValue()){
                    return ps;
                }
            }
            return null;
        }
        public String getName() {
            return name;
        }

        public Integer getValue() {
            return value;
        }

    }


    /**
     *  财务结算模式：0=现结（实时）结算、1=周结、2=月结、3=季结、4=年结
     */
    public enum  FinanceSettlementMode{
        real_time ("实时", 0),
        week("周结", 1),
        month("月结",2),
        quarter("季结",3),
        year("年结",4);
        private final String name;
        private final Integer value;
        FinanceSettlementMode(String name, Integer value) {
            this.name = name;
            this.value = value;
        }

        public static FinanceSettlementMode getFinanceSettlementMode(Integer val){
            if (val==null)return null;
            for (FinanceSettlementMode ps :FinanceSettlementMode.values()){
                if (val == ps.getValue()){
                    return ps;
                }
            }
            return null;
        }
        public String getName() {
            return name;
        }

        public Integer getValue() {
            return value;
        }
    }

    /**
     * 信用等级 A级、B级、C级、D级“四个级别，默认D级；
     */
    public enum  CreditLevel{
        A("A", "A"),
        B("B", "B"),
        C("C","C"),
        D("D","D");
        private final String name;
        private final String value;
        CreditLevel(String name, String value) {
            this.name = name;
            this.value = value;
        }

        public static CreditLevel getCreditLevel(String val){
            if (StringUtils.isBlank(val))return null;
            for (CreditLevel ps : CreditLevel.values()){
                if (val.equals(ps.getValue())){
                    return ps;
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

    /**
     *  常用供应商，是或否，默认否； 0=不常用、1=常用
     */
    public enum  FinanceCommonUsed{
        common_used ("常用", 0),
        not_common("不常用", 1);
        private final String name;
        private final Integer value;
        FinanceCommonUsed(String name, Integer value) {
            this.name = name;
            this.value = value;
        }

        public static FinanceCommonUsed getFinanceCommonUsed(Integer val){
            if (val==null)return null;
            for (FinanceCommonUsed ps : FinanceCommonUsed.values()){
                if (val == ps.getValue()){
                    return ps;
                }
            }
            return null;
        }
        public String getName() {
            return name;
        }

        public Integer getValue() {
            return value;
        }
    }
    /**
     * 金融账号类型 ：0=银行卡、1=支付宝、2=微信
     */
    public enum  FinanceAccountType{
        Bank ("银行卡", 0),
        Alipay("支付宝", 1),
        Wechat("微信",2);
        private final String name;
        private final Integer value;
        FinanceAccountType(String name, Integer value) {
            this.name = name;
            this.value = value;
        }
        public static FinanceAccountType getFinanceAccountType(Integer val){
            if (val==null)return null;
            for (FinanceAccountType ps : FinanceAccountType.values()){
                if (val == ps.getValue()){
                    return ps;
                }
            }
            return null;
        }
        public String getName() {
            return name;
        }

        public Integer getValue() {
            return value;
        }
    }
    /**
     * 人员类型：0=普通联系人、1=导游
     */
    public enum  PersonType{
        contact ("普通联系人", 0),
        guides("导游", 1);
        private final String name;
        private final Integer value;
        PersonType(String name, Integer value) {
            this.name = name;
            this.value = value;
        }
        public static PersonType getPersonType(Integer val){
            if (val==null)return null;
            for (PersonType ps : PersonType.values()){
                if (val == ps.getValue()){
                    return ps;
                }
            }
            return null;
        }
        public String getName() {
            return name;
        }

        public Integer getValue() {
            return value;
        }
    }

    /**
     * 游客类型：0=成人、1=儿童
     */
    public enum  ClientType{
        adult("成人", 0),
        children("儿童", 1);
        private final String name;
        private final Integer value;
        ClientType(String name, Integer value) {
            this.name = name;
            this.value = value;
        }
        public static ClientType getClientType(Integer val){
            if (val==null)return null;
            for (ClientType ps : ClientType.values()){
                if (val == ps.getValue()){
                    return ps;
                }
            }
            return null;
        }
        public String getName() {
            return name;
        }

        public Integer getValue() {
            return value;
        }
    }

    /**
     * 性别
     */
    public  enum Sex {
        woman("女", 2), man("男", 1);
        private final String name;
        private final Integer value;

        Sex(String name, Integer value) {
            this.name = name;
            this.value = value;
        }
        public static Sex getSex(Integer val){
            for (Sex ps :Sex.values()){
                if (val == ps.getValue()){
                    return ps;
                }
            }
            return null;
        }
        public String getName() {
            return name;
        }

        public Integer getValue() {
            return value;
        }
    }
    /**
     * 证件类型定义
     */
    public  enum IdCardType {
        identification("身份证", 1), passport("护照", 2),other("其他",99);
        private final String name;
        private final Integer value;

        IdCardType(String name, Integer value) {
            this.name = name;
            this.value = value;
        }
        public static IdCardType getIdCardType(Integer val){
            for (IdCardType type :IdCardType.values()){
                if (type.getValue()==val){
                    return type;
                }
            }
            return null;
        }

        public String getName() {
            return name;
        }

        public Integer getValue() {
            return value;
        }
    }

    /**
     * 销售渠道费用类型
     */
    public enum  SalesFeeType{
        ad_fee("广告费用", 0),
        trade_fee("交易费用", 1),
        other_fee("其他费用", 9);
        private final String name;
        private final Integer value;
        SalesFeeType(String name, Integer value) {
            this.name = name;
            this.value = value;
        }
        public static SalesFeeType getSalesFeeType(Integer val){
            if (val==null)return null;
            for (SalesFeeType ps : SalesFeeType.values()){
                if (val == ps.getValue()){
                    return ps;
                }
            }
            return null;
        }
        public String getName() {
            return name;
        }

        public Integer getValue() {
            return value;
        }
    }
    /**
     * 销售渠道费用结算模式
     */
    public enum  SalesCostSettlementMode{
        bottom_price("底价模式", 0),
        commission("佣金模式", 1),
        other("其他模式", 9);
        private final String name;
        private final Integer value;
        SalesCostSettlementMode(String name, Integer value) {
            this.name = name;
            this.value = value;
        }
        public static SalesCostSettlementMode getSalesCostSettlementMode(Integer val){
            if (val==null)return null;
            for (SalesCostSettlementMode ps : SalesCostSettlementMode.values()){
                if (val == ps.getValue()){
                    return ps;
                }
            }
            return null;
        }
        public String getName() {
            return name;
        }

        public Integer getValue() {
            return value;
        }
    }
    /**
     * 销售渠道收费模式
     */
    public enum  ChargingModel{
        annual_fee("年费", 0),
        other("其他", 9);
        private final String name;
        private final Integer value;
        ChargingModel(String name, Integer value) {
            this.name = name;
            this.value = value;
        }
        public static ChargingModel getChargingModel(Integer val){
            if (val==null)return null;
            for (ChargingModel ps : ChargingModel.values()){
                if (val == ps.getValue()){
                    return ps;
                }
            }
            return null;
        }
        public String getName() {
            return name;
        }

        public Integer getValue() {
            return value;
        }
    }

    /**
     * 区域类型
     */
    public enum AreaType{
        foreign_country("获取境外国家", 0),
        district("获取中国区域", 1),
        province("获取省份",2),
        all("所有数据",3);

        private final String name;
        private final Integer value;
        AreaType(String name, Integer value) {
            this.name = name;
            this.value = value;
        }
        public static AreaType getAreaType(Integer val){
            if (val==null)return null;
            for (AreaType ps : AreaType.values()){
                if (val == ps.getValue()){
                    return ps;
                }
            }
            return null;
        }
        public String getName() {
            return name;
        }

        public Integer getValue() {
            return value;
        }
    }


    /**
     * 产品类别状态  0=启用、1=禁用
     */
    public enum  ProductCategoryState{
        enable("启用", 0),
        disable("禁用", 1);
        private final String name;
        private final Integer value;

        ProductCategoryState(String name, Integer value) {
            this.name = name;
            this.value = value;
        }

        public static ProductCategoryState getProductCategoryState(Integer val){
            for (ProductCategoryState ps : ProductCategoryState.values()){
                if (val == ps.getValue()){
                    return ps;
                }
            }
            return null;
        }

        public String getName() {
            return name;
        }

        public Integer getValue() {
            return value;
        }
    }

    /**
     * 线路产品费用类型
     */
    public enum  RouteFeeType{
        adult("成人打包价", 0),
        children("儿童打包价", 1),
        guide("导游/领队", 2),
        traffic("交通票务", 3),
        reside("住宿", 4),
        ticket("景点门票", 5),
        meal("用餐", 6),
        car("用车", 7),
        insurance("保险", 8),
        travel_agency("地接", 9),
        other("地接", 10);
        private final String name;
        private final Integer value;
        RouteFeeType(String name, Integer value) {
            this.name = name;
            this.value = value;
        }
        public static RouteFeeType getSRouteFeeType(Integer val){
            if (val==null)return null;
            for (RouteFeeType ps : RouteFeeType.values()){
                if (val == ps.getValue()){
                    return ps;
                }
            }
            return null;
        }
        public String getName() {
            return name;
        }

        public Integer getValue() {
            return value;
        }
    }

    /**
     * 货币
     */
    public enum  Currency{
        USD("美元", "USD"),
        CNY("人民币", "CNY");
        private final String name;
        private final String value;
        Currency(String name, String value) {
            this.name = name;
            this.value = value;
        }

        public static Currency getCurrency(String val){
            if (StringUtils.isBlank(val))return null;
            for (Currency ps : Currency.values()){
                if (val.equals(ps.getValue())){
                    return ps;
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

    /**
     * 交通工具
     */
    public enum  TrafficType{
        oneSelf("自理", 0),airplane("飞机", 1),train("火车", 2),car("汽车", 3),steamer("轮船", 4),motor_car("动车", 5),high_speed_rail("高铁", 6),
        speedboat("快艇", 7),other("其他", 9);
        private final String name;
        private final Integer value;
        TrafficType(String name, Integer value) {
            this.name = name;
            this.value = value;
        }
        public static TrafficType getTrafficType(Integer val){
            if (val==null)return null;
            for (TrafficType ps : TrafficType.values()){
                if (val == ps.getValue()){
                    return ps;
                }
            }
            return null;
        }
        public String getName() {
            return name;
        }

        public Integer getValue() {
            return value;
        }
    }

    /**
     * 客户交通票务预定状态
     */
    public  enum ClientTrafficState {
        no_scheduled("不预定", 0), confirm("确认", 1),hold("占位",2);
        private final String name;
        private final Integer value;

        ClientTrafficState(String name, Integer value) {
            this.name = name;
            this.value = value;
        }
        public static ClientTrafficState getClientTrafficState(Integer val){
            for (ClientTrafficState type : ClientTrafficState.values()){
                if (type.getValue()==val){
                    return type;
                }
            }
            return null;
        }

        public String getName() {
            return name;
        }

        public Integer getValue() {
            return value;
        }
    }

    /**
     *  用餐类型：0=早餐、1=中餐、2=晚餐
     */
    public enum  MealType{
        breakfast ("早餐", 0),
        lunch("午餐", 1),
        dinner("晚餐", 1);
        private final String name;
        private final Integer value;
        MealType(String name, Integer value) {
            this.name = name;
            this.value = value;
        }
        public static MealType getMealType(Integer val){
            if (val==null)return null;
            for (MealType ps : MealType.values()){
                if (val == ps.getValue()){
                    return ps;
                }
            }
            return null;
        }
        public String getName() {
            return name;
        }

        public Integer getValue() {
            return value;
        }
    }


    /**
     *  是否包含用餐（早餐 中餐 晚餐）； 0=包含、1=不包含
     */
    public enum  IncludeMeal{
        include ("包含", 0),
        not_include("不包含", 1);
        private final String name;
        private final Integer value;
        IncludeMeal(String name, Integer value) {
            this.name = name;
            this.value = value;
        }
        public static IncludeMeal getIncludeMeal(Integer val){
            if (val==null)return null;
            for (IncludeMeal ps : IncludeMeal.values()){
                if (val == ps.getValue()){
                    return ps;
                }
            }
            return null;
        }
        public String getName() {
            return name;
        }

        public Integer getValue() {
            return value;
        }
    }

    /**
     *  是否包含用餐（早餐 中餐 晚餐）； 0=包含、1=不包含
     */
    public enum  IsShopping{
        no_shopping ("纯玩不购物", 0),
        shopping("购物", 1),
        recommend_shopping("推荐购物", 2);
        private final String name;
        private final Integer value;
        IsShopping(String name, Integer value) {
            this.name = name;
            this.value = value;
        }
        public static IsShopping getIsShopping(Integer val){
            if (val==null)return null;
            for (IsShopping ps : IsShopping.values()){
                if (val == ps.getValue()){
                    return ps;
                }
            }
            return null;
        }
        public String getName() {
            return name;
        }

        public Integer getValue() {
            return value;
        }
    }


    /**
     * 是否有自费:0=无自费、1=有自费、2=推荐自费
     */
    public enum  IsOwnerExpense{
        no_cost ("无自费", 0),
        cost("自费", 1),
        recommend_cost("推荐自费", 2);
        private final String name;
        private final Integer value;
        IsOwnerExpense(String name, Integer value) {
            this.name = name;
            this.value = value;
        }
        public static IsOwnerExpense getIsOwnerExpense(Integer val){
            if (val==null)return null;
            for (IsOwnerExpense ps : IsOwnerExpense.values()){
                if (val == ps.getValue()){
                    return ps;
                }
            }
            return null;
        }
        public String getName() {
            return name;
        }

        public Integer getValue() {
            return value;
        }
    }



    /**
     * 用房信息 0=标准间、1=大床房、2=三人间、3=陪房、4=不用房
     */
    public enum  RoomType{
        standard("标准间", 0),
        big_bed_room("大床房", 1),
        three_bed_room("三人间", 2),
        accompany_room("陪房", 3),
        no_room("不用房",4);
        private final String name;
        private final Integer value;
        RoomType(String name, Integer value) {
            this.name = name;
            this.value = value;
        }
        public static RoomType getRoomType(Integer val){
            if (val==null)return null;
            for (RoomType ps : RoomType.values()){
                if (val == ps.getValue()){
                    return ps;
                }
            }
            return null;
        }
        public String getName() {
            return name;
        }

        public Integer getValue() {
            return value;
        }
    }

    /**
     * 用房标准：0=准三星、1=挂三星、2=准四星、3=挂四星、4=准五星、5=挂五星、6其他
     */
    public enum  RoomLevel{
        three("准三星", 0),
        pre_three("挂三星", 1),
        four("准四星", 2),
        pre_four("挂四星", 3),
        five("准五星", 4),
        pre_five("挂五星", 5),
        other("其他",6);
        private final String name;
        private final Integer value;
        RoomLevel(String name, Integer value) {
            this.name = name;
            this.value = value;
        }
        public static RoomLevel getRoomLevel(Integer val){
            if (val==null)return null;
            for (RoomLevel ps : RoomLevel.values()){
                if (val == ps.getValue()){
                    return ps;
                }
            }
            return null;
        }
        public String getName() {
            return name;
        }

        public Integer getValue() {
            return value;
        }
    }



    /**
     * 保险信息 0=意外险、1=责任险、2=统一险、3=线路含保险
     */
    public enum  InsuranceType{
        accident("意外险", 0),
        duty("责任险", 1),
        unity("统一险", 2),
        has_include("线路含保险", 3);
        private final String name;
        private final Integer value;
        InsuranceType(String name, Integer value) {
            this.name = name;
            this.value = value;
        }
        public static InsuranceType getInsuranceType(Integer val){
            if (val==null)return null;
            for (InsuranceType ps : InsuranceType.values()){
                if (val == ps.getValue()){
                    return ps;
                }
            }
            return null;
        }
        public String getName() {
            return name;
        }

        public Integer getValue() {
            return value;
        }
    }


    /**
     *  是否默认
     */
    public enum IsDefaultType{
        is_default ("默认", 0),
        not_default("不默认", 1);
        private final String name;
        private final Integer value;
        IsDefaultType(String name, Integer value) {
            this.name = name;
            this.value = value;
        }
        public static IsDefaultType getIsDefaultType(Integer val){
            if (val==null)return null;
            for (IsDefaultType ps : IsDefaultType.values()){
                if (val == ps.getValue()){
                    return ps;
                }
            }
            return null;
        }
        public String getName() {
            return name;
        }

        public Integer getValue() {
            return value;
        }
    }



    /**
     *  报名大厅线路状态
     */
    public enum HallProductStatus{
        pending_trip ("待出团", 0),
        on_trip("行程中", 1),
        end_trip("行程结束",2),
        cancel_trip("行程取消",3),
        delete_trip("已删除",4);
        private final String name;
        private final Integer value;
        HallProductStatus(String name, Integer value) {
            this.name = name;
            this.value = value;
        }
        public static HallProductStatus getHallProductStatus(Integer val){
            if (val==null)return null;
            for (HallProductStatus ps : HallProductStatus.values()){
                if (val == ps.getValue()){
                    return ps;
                }
            }
            return null;
        }
        public String getName() {
            return name;
        }

        public Integer getValue() {
            return value;
        }
    }

    /**
     * 景点门票类型
     */
    public enum  TicketType{
        full("全价", 0),
        preferential("优惠票", 1),
        free("免费", 2);
        private final String name;
        private final Integer value;
        TicketType(String name, Integer value) {
            this.name = name;
            this.value = value;
        }
        public static TicketType getTicketType(Integer val){
            if (val==null)return null;
            for (TicketType ps : TicketType.values()){
                if (val == ps.getValue()){
                    return ps;
                }
            }
            return null;
        }
        public String getName() {
            return name;
        }

        public Integer getValue() {
            return value;
        }
    }

    /**
     * 线路订单状态 0=确认、1=占位、2=取消
     */
    public enum  RouteOrderState{
        confirm ("确认", 0),
        hold("占位", 1),
        cancel("取消",2);
        private final String name;
        private final Integer value;
        RouteOrderState(String name, Integer value) {
            this.name = name;
            this.value = value;
        }
        public static RouteOrderState getRouteOrderState(Integer val){
            if (val==null)return null;
            for (RouteOrderState ps : RouteOrderState.values()){
                if (val == ps.getValue()){
                    return ps;
                }
            }
            return null;
        }
        public String getName() {
            return name;
        }

        public Integer getValue() {
            return value;
        }
    }



    /**
     * 金额类型 0=收入、1=支出
     */
    public enum  AmountType{
        income ("收入", 0),
        cost("支出", 1);
        private final String name;
        private final Integer value;
        AmountType(String name, Integer value) {
            this.name = name;
            this.value = value;
        }
        public static AmountType getAmountType(Integer val){
            if (val==null)return null;
            for (AmountType ps : AmountType.values()){
                if (val == ps.getValue()){
                    return ps;
                }
            }
            return null;
        }
        public String getName() {
            return name;
        }

        public Integer getValue() {
            return value;
        }
    }


    /**
     * 审核状态：0=未审核、1=通过、2=驳回
     */
    public enum  FinanceAuditStatus{
        init ("未审核", 0),
        approved("通过", 1),
        rebut("驳回", 2);
        private final String name;
        private final Integer value;
        FinanceAuditStatus(String name, Integer value) {
            this.name = name;
            this.value = value;
        }
        public static FinanceAuditStatus getFinanceAuditStatus(Integer val){
            if (val==null)return null;
            for (FinanceAuditStatus ps : FinanceAuditStatus.values()){
                if (val == ps.getValue()){
                    return ps;
                }
            }
            return null;
        }
        public String getName() {
            return name;
        }

        public Integer getValue() {
            return value;
        }
    }
}
