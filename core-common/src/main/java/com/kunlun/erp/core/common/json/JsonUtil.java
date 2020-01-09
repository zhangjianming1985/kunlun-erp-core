package com.kunlun.erp.core.common.json;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.fastjson.serializer.SerializerFeature;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName JsonUtil
 * @Description JSON转换工具
 * @Author Jm.zhang
 * @Date 2019/11/14 11:32
 * @Version 1.0
 **/
public class JsonUtil {
    /**
     * json字符串-简单对象型到JSONObject的转换
     * @param json_str  要转为JSON对象的字符串
     * @return JSON OBJECT
     */
    private static JSONObject toJsonObj(String json_str) {
        return JSONObject.parseObject(json_str);
    }

    /**
     * 复杂JavaBean_obj到json对象的转换
     * @param java_obj  Object
     * @return JSONObject
     */
    public static JSONObject toJsonObj(Object java_obj) {
        String java_obj_str = JsonUtil.toStr(java_obj);
        return JsonUtil.toJsonObj(java_obj_str);
    }

    /**
     * 功能描述:json字符串-数组类型到JSONArray的转换
     *
     * @param json_str 字符串
     * @return JSONArray
     */
    public static JSONArray toJsonArr(String json_str) {
        return JSONArray.parseArray(json_str);
    }


    /**
     * JavaList到JsonArray的转换
     * @param java_list list集合
     * @return JSON Array
     */
    public static JSONArray toJsonArr(List java_list) {
        String java_list_str = JsonUtil.toStr(java_list);
        return JsonUtil.toJsonArr(java_list_str);
    }

    /**
     * 功能描述:json字符串-简单对象型与javaBean之间的转换
     *
     * @param json_str json字符串-简单对象型
     * @param type     对象类型
     * @return java bean
     */
    public static <T> T toBean(String json_str, TypeReference<?> type) {
        return (T) JSON.parseObject(json_str, type);
    }

    /**
     * 功能描述:简单json对象到JavaBean_obj的转换
     *
     * @param jsonObj 简单json对象
     * @param type     被转换java bean类型
     * @return java bean
     */
    public static <T> T toBean(JSONObject jsonObj, TypeReference<?> type) {
        String json_obj_str = JsonUtil.toStr(jsonObj);
        return JsonUtil.toBean(json_obj_str, type);
    }

    /**
     * 字符串到 respon dto 转换
     * @param json_str json 字符串
     * @param type 目标类型
     * @param <T> 目标类型
     * @return 目标类型
     */
/*    public static <T> AbstractResponse<T> toRespDto(String json_str, TypeReference<?> type) {
        return ((AbstractResponse<T>) JSON.parseObject(json_str, type));

    }*/

    /**
     *
     * @param json_str
     * @param type
     * @param <T>
     * @return
     */
/*    public static <T> AbstractRequest<T> toReqDto(String json_str, TypeReference<?> type) {
        return ((AbstractRequest<T>) JSON.parseObject(json_str, type));

    }*/


    /**
     * 功能描述:json字符串-数组类型到JavaBean_List的转换
     *
     * @param json_str 字符串-数组类型
     * @param type         List 对象类型
     * @return List
     */
    public static <T> List<T> toList(String json_str, TypeReference<ArrayList<T>> type) {
        return JSON.parseObject(json_str, type);

    }

    /**
     * 功能描述:JSONObject到json字符串-简单对象型的转换
     *
     * @param jsonObj JSON 对象
     * @return JSON STRING
     */
    public static String toStr(JSONObject jsonObj) {
        return JSONObject.toJSONString(jsonObj, SerializerFeature.SortField);
    }

    /**
     * 功能描述:  JSONArray到json字符串-数组类型的转换
     *
     * @param json_array JSON ARRAY
     * @return JSON ARRAY
     */
    public static String toStr(JSONArray json_array) {
        return JSONArray.toJSONString(json_array);
    }

    /**
     * 功能描述:JavaBean_List到json字符串-数组类型的转换
     *
     * @param list List 集合
     * @return String
     */
    @SuppressWarnings("rawtypes")
    public static String toStr(List list) {
        return JSONArray.toJSONString(list);
    }

    /**
     * 功能描述:JavaBean到json字符串-简单对象类型的转换
     *
     * @param object OBJECT
     * @return 字符串
     */
    public static String toStr(Object object) {
        return JSON.toJSONString(object, SerializerFeature.DisableCircularReferenceDetect);
    }

    /**
     * 功能描述:
     *
     * @param level level
     * @return string
     */
    private static String getLevelStr(int level) {
        StringBuilder levelStr = new StringBuilder();
        for (int levelI = 0; levelI < level; levelI++) {
            levelStr.append("\t");
        }
        return levelStr.toString();
    }

    /**
     *
     * @param gsonStr json  字符串
     * @return  字符串
     */
    public static StringBuilder formatJsonStr(String gsonStr) {
        int level = 0;
        //存放格式化的json字符串
        StringBuilder jsonForMatStr = new StringBuilder();
        for (int index = 0; index < gsonStr.length(); index++)//将字符串中的字符逐个按行输出
        {
            //获取s中的每个字符
            char c = gsonStr.charAt(index);

            //level大于0并且jsonForMatStr中的最后一个字符为\n,jsonForMatStr加入\t
            if (level > 0 && '\n' == jsonForMatStr.charAt(jsonForMatStr.length() - 1)) {
                jsonForMatStr.append(getLevelStr(level));
            }
            //遇到"{"和"["要增加空格和换行，遇到"}"和"]"要减少空格，以对应，遇到","要换行
            switch (c) {
                case '{':
                case '[':
                    jsonForMatStr.append(c).append("\n");
                    level++;
                    break;
                case ',':
                    jsonForMatStr.append(c).append("\n");
                    break;
                case '}':
                case ']':
                    jsonForMatStr.append("\n");
                    level--;
                    jsonForMatStr.append(getLevelStr(level));
                    jsonForMatStr.append(c);
                    break;
                default:
                    jsonForMatStr.append(c);
                    break;
            }
        }
        return jsonForMatStr;
    }

}
