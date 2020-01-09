package com.kunlun.erp.core.common.util;

import java.util.Random;
import java.util.UUID;

/**
 * @ClassName UniqueCodeUtil
 * @Description 唯一编号工具
 * @Author Jm.zhang
 * @Date 2019/11/19 17:20
 * @Version 1.0
 **/
public class UniqueCodeUtil {
    /**
     * 各模块数据唯一编码前缀
     */
    public  enum UniquePrefix {
        company_info("企业信息表、包含 销售渠道、供应商", "1"),
        financial_account("金融账户表，包含 销售渠道、  供应商", "2"),
        person_info("人员信息表，包含 销售渠道联系人、供应商联系人、导游", "3"),
        sales_channel_cost("销售渠道费用数据", "4"),
        product_category("产品类别", "5"),
        product("产品", "6"),
        route("线路", "7"),
        route_plan("线路计划", "8"),
        route_plan_node("线路计划节点", "9"),
        route_plan_price("线路计划价格", "11"),
        route_price_plan("线路价格方案", "12"),
        route_price_detail("价格明细", "13"),
        route_cost_detail("成本明细", "14"),
        group_supplier("线路团关联的供应商", "15"),
        route_order("线路团订单", "16"),
        order_client("出游人", "17");
        private final String name, value;

        UniquePrefix(String name, String value) {
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

    public static String generateUniqueCode(String type) {
        String uniqueCode;
        int hashCodeV = UUID.randomUUID().toString().hashCode();
        if (hashCodeV < 0) {//有可能是负数
            hashCodeV = -hashCodeV;
        }
        // 0 代表前面补充0 // 15代表长度为15 // d 代表参数为正数型
        uniqueCode = type + String.format("%015d", hashCodeV);
        return uniqueCode;
    }

    public static String randomNumber(int start, int end) {
        Random random = new Random();
        int n = random.nextInt(end) % (end - start + 1) + start;
        return String.valueOf(n);
    }
}
