package com.kunlun.erp.core.common.security;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @ClassName Md5Secure
 * @Description MD5加密
 * @Author Jm.zhang
 * @Date 2019/11/14 11:52
 * @Version 1.0
 **/
public class Md5Secure {
    private static final char[] hexChars = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e',
            'f'};
    /**
     * 32位MD5加密算法
     *
     * @param s 被加密字符串
     * @return 加密结果
     */
    public String convert32(String s) throws NoSuchAlgorithmException {
        byte[] bytes = s.getBytes(StandardCharsets.UTF_8);
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(bytes);
        bytes = md.digest();
        int j = bytes.length;
        char[] chars = new char[j * 2];
        int k = 0;
        for (byte b : bytes) {
            chars[k++] = hexChars[b >>> 4 & 0xf];
            chars[k++] = hexChars[b & 0xf];
        }
        return new String(chars);
    }

    /**
     * 16位MD5加密算法
     *
     * @param s 被加密字符串
     * @return 加密结果
     */
    public String convert16(String s) throws NoSuchAlgorithmException {
        byte[] bytes = s.getBytes(StandardCharsets.UTF_8);
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(bytes);
        bytes = md.digest();
        int j = bytes.length;
        char[] chars = new char[j * 2];
        int k = 0;
        for (byte b : bytes) {
            chars[k++] = hexChars[b >>> 4 & 0x0f];
            chars[k++] = hexChars[b & 0x0f];
        }
        return new String(chars).substring(8, 24);
    }

}
