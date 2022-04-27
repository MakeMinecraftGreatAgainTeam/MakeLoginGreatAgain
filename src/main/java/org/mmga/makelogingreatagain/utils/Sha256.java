package org.mmga.makelogingreatagain.utils;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author wzp
 * @version 1.0.0
 * @date 2022/4/27
 */
public class Sha256 {
    public static String get256(String str){
        String passWord = "";
        try {
            MessageDigest instance = MessageDigest.getInstance("SHA-256");
            instance.update(str.getBytes(StandardCharsets.UTF_8));
            byte[] digest = instance.digest();
            passWord = byte2Hex(digest);
        } catch (NoSuchAlgorithmException ignored) {}
        return passWord;
    }
    /**
     * 将byte转为16进制
     *
     * @param bytes 字节数据
     * @return 16进制文本
     */
    private static String byte2Hex(byte[] bytes) {
        StringBuilder stringBuffer = new StringBuilder();
        String temp = null;
        for (byte aByte : bytes) {
            temp = Integer.toHexString(aByte & 0xFF);
            if (temp.length() == 1) {
                // 1得到一位的进行补0操作
                stringBuffer.append("0");
            }
            stringBuffer.append(temp);
        }
        return stringBuffer.toString();
    }
}
