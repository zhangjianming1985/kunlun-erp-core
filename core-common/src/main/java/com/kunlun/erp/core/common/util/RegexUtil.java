package com.kunlun.erp.core.common.util;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @ClassName RegexUtil
 * @Description 正则工具
 * @Author Jm.zhang
 * @Date 2019/11/14 11:31
 * @Version 1.0
 **/
public class RegexUtil {
    private static Logger LOGGER = LoggerFactory.getLogger(RegexUtil.class);

    public static final String  special_character  = "[、【】{}~!@#$%^&*()_+=-]";

    public static String trimSpaceTag(String str) {
        if (StringUtils.isBlank(str)) return "";
        String regEx_space = "\\s*|\t|\r|\n|\\'|\\‘";//定义空格回车换行符
        Pattern p_space = Pattern.compile(regEx_space, Pattern.CASE_INSENSITIVE);
        Matcher m_space = p_space.matcher(str);
        str = m_space.replaceAll(""); // 过滤空格回车标签
        return str.trim(); // 返回文本字符串
    }
    /**
     * 是否是一个有效的手机号
     * @param mobile 手机号码
     * @return boolean
     */
    public static boolean isMobile(String mobile) {
        if (StringUtils.isBlank(mobile)) {
            return false;
        }
//        String REGEX_MOBILE = "^((17[0-9])|(14[0-9])|(13[0-9])|(15[^4,\\D])|(18[0-9])|(19[0-9]))\\d{8}$";
        String REGEX_MOBILE = "^1[3|4|5|7|8|9][0-9]\\d{4,8}$";

        return mobile.matches(REGEX_MOBILE);
    }


    /**
     * 是否是一个有效的固话
     * @param phone 固话
     * @return boolean
     */
    public static boolean isPhone(String phone) {
/*        if (StringUtils.isBlank(phone)) {
            return false;
        }
        String REGEX = "^([0-9]{3,4}-)?[0-9]{7,8}$";

        return phone.matches(REGEX);*/
        return commonStrCheck(phone,5,20,true);

    }

    /**
     * 返回指定正则表达式内容
     *
     * @param content 查找到内容
     * @param regex   正则表达式
     * @return 匹配后的内容
     */
    public static String getMatcherStr(String content, String regex) {
        return getMatcherStr(content, regex, null);
    }

    /**
     * 返回指定正则表达式内容数组
     *
     * @param content 查找到内容
     * @param regex   正则表达式
     * @return null or empty
     */
    public static String[] getMatcherArray(String content, String regex) {
        String split = ";-;";
        String arrays = getMatcherStr(content, regex, split);
        return "".equals(arrays) ? null : arrays.split(split);
    }

    /**
     * 返回指定正则表达式内容
     *
     * @param content    查找到内容
     * @param regex      正则表达式
     * @param groupSplit 指定匹配组后添加分配内容
     * @return 匹配后的内容
     */
    private static String getMatcherStr(String content, String regex, String groupSplit) {
        try {
            StringBuilder sb = new StringBuilder();
            Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(content);
            while (matcher.find()) {
                sb.append(matcher.group());
                if (groupSplit != null) {
                    sb.append(groupSplit);
                }
            }
            return sb.toString();
        } catch (Exception e) {
            LOGGER.error("正则匹配异常!", e);
        }
        return "";
    }

    /**
     * 判断输入内容是否包含字符[a-zA-Z0-9]或者汉字
     *
     * @param input 字符
     * @return true/false
     */
    public static boolean includeChar(String input) {
        return Pattern.matches(".*(\\d|[a-zA-Z]|[\\u4E00-\\u9FA5])+.*", input);
    }

    /**
     * 是否是一个[0-9]的整数
     *
     * @param input 匹配内容
     * @return true/false
     */
    public static boolean isNumber(String input) {
        if (input == null) {
            return false;
        }
        return input.matches("\\d+");
    }

    /**
     * 判断字符串是否为数字,0-9重复0次或者多次
     * @param strnum
     * @return true, 符合; false, 不符合。
     */
    public static boolean isNumeric(String strnum) {
        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(strnum);
        return isNum.matches();
    }


    //两位小数金额校验
    public static boolean isDecimal(Object obj) {
        boolean flag = false;
        try {
            if (obj != null) {
                String source = obj.toString();
                // 判断是否是整数或者是携带一位或者两位的小数
                Pattern pattern = Pattern.compile("^[+]?([0-9]+(.[0-9]{1,2})?)$");
                if (pattern.matcher(source).matches()) {
                    flag = true;
                }
            }
        } catch (Exception e) {
            e.getMessage();
        }
        return flag;
    }
    /**
     * 是否是一个有效的邮件地址
     *
     * @param email 邮件地址
     * @return 是否有效
     */
    public static boolean isEmail(String email) {
        String regex = "^([a-z0-9A-Z]+[-|.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
        return Pattern.matches(regex, email);
    }

    /**
     * 验证是否是有效IP地址 0.0.0.0-255.255.255.255
     *
     * @param ip IP地址
     * @return true/false
     */
    public static boolean isIp(String ip) {
        String regex = "(25[0-5]|2[0-4][0-9]|1[0-9][0-9]|[1-9]?[0-9])\\.(25[0-5]|2[0-4][0-9]|1[0-9][0-9]|[1-9]?[0-9])\\.(25[0-5]|2[0-4][0-9]|1[0-9][0-9]|[1-9]?[0-9])\\.(25[0-5]|2[0-4][0-9]|1[0-9][0-9]|[1-9]?[0-9])";
        return Pattern.matches(regex, ip);
    }

    /**
     * 功能：判断字符串出生日期是否符合正则表达式：包括年月日，闰年、平年和每月31天、30天和闰月的28天或者29天
     * @param strDate
     * @return true, 符合; false, 不符合。
     */
    public static boolean isDate(String strDate) {
        if (StringUtils.isBlank(strDate)){
            return false;
        }
        Pattern pattern = Pattern.compile(
                "^((\\d{2}(([02468][048])|([13579][26]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))?$");
        Matcher m = pattern.matcher(strDate);
        return m.matches();
    }

    /**
     * 验证护照格式
     * @param passport_number
     * @return
     */
    public static boolean isPassport(String passport_number){
        return !StringUtils.isBlank(passport_number);
    }

    /**
     * 密码规则：1： 长度大于6位且小于20位  2：需要同时包含数字和字母 3：不能包含特殊字符 ~!@#$%^&*()_+-
     * @param password
     * @return
     */
    public static boolean checkPassword(String password){
        if (StringUtils.isBlank(password)){
            return false;
        }
        if (password.length() < 6 || password.length() >20){
            return false;
        }
        Pattern pattern = Pattern.compile("[\\u4E00-\\u9FA5]");
        if (pattern.matcher(password).find()){
            return false;
        }
        pattern = Pattern.compile(special_character);
        if(pattern.matcher(password).find()){
            return false;
        }
        int match_count =0;
        pattern = Pattern.compile("[0-9]");
        if(pattern.matcher(password).find()){
            match_count++;
        }
        pattern = Pattern.compile("[a-zA-Z]");
        if(pattern.matcher(password).find()){
            match_count++;
        }
        return match_count == 2;
    }


    /**
     * 登陆账户： 可以是手机号码  或者 以下条件
     * 登陆账户：1： 长度大于6位且小于20位  2：必须以字母开头  3：不能包含特殊字符 ~!@#$%^&*()_+-=
     * @param login_name
     * @return
     */
    public static boolean checkLoginName(String login_name){
        if (StringUtils.isBlank(login_name)){
            return false;
        }
        if (login_name.length() < 6 || login_name.length() >20){
            return false;
        }
        Pattern pattern = Pattern.compile("[\\u4E00-\\u9FA5]");
        if (pattern.matcher(login_name).find()){
            return false;
        }
        pattern = Pattern.compile(special_character);
        if(pattern.matcher(login_name).find()){
            return false;
        }
        pattern = Pattern.compile("[a-zA-Z]");
        String first_char = String.valueOf(login_name.charAt(0));
        return pattern.matcher(first_char).find();
    }

    /**
     *
     * @param content
     * @param min_length
     * @param max_length
     * @param sc 是否可以包含特殊字符
     * @return
     */
    public static boolean commonStrCheck(String content,int min_length,int max_length,boolean sc){
        if (StringUtils.isBlank(content)){
            return false;
        }
        if (content.length() < min_length || content.length() >max_length){
            return false;
        }
        if (!sc){
            Pattern pattern = Pattern.compile(special_character);
            return !pattern.matcher(content).find();
        }

        return true;
    }


    /**
     *
     * @param content
     * @param min_length
     * @param max_length
     * @param not_chart 不包含包含特殊字符
     * @return
     */
    public static boolean commonStrCheck(String content,int min_length,int max_length,String not_chart){
        if (StringUtils.isBlank(content)){
            return false;
        }
        if (content.length() < min_length || content.length() >max_length){
            return false;
        }
        Pattern pattern = Pattern.compile(not_chart);
        return !pattern.matcher(content).find();
    }

    /**
     * 是否微信号
     * @param wechat
     * @return
     */
    public static boolean isWeChat(String wechat){
        if (StringUtils.isBlank(wechat))return false;
        if (wechat.length()<1 || wechat.length()>16)return false;
        Pattern pattern = Pattern.compile("[、【】{}~!@#$%^&*()+=-]");
        return !pattern.matcher(wechat).find();
    }

    /**
     * 是否QQ号
     * @param qq
     * @return
     */
    public static boolean isQQ(String qq){
        if (StringUtils.isBlank(qq))return false;
        if (qq.length()<1 || qq.length()>15)return false;
        return RegexUtil.isNumeric(qq) != false;
    }

    public static void main(String[] arg){
        String sc_str = "1111【】";
        System.out.println(RegexUtil.commonStrCheck(sc_str,1,100,false));
    }


}
