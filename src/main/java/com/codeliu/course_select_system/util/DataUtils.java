package com.codeliu.course_select_system.util;

import java.security.SecureRandom;

import org.apache.commons.codec.binary.Hex;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

/**
 * @ClassName liuxiaoping
 * @Description 用户密码类
 * @Author liu
 * @Date 2019/2/3 15:02
 * @Version 1.0
 */
public class DataUtils {

    /**
     * MD5加盐加密方法，传入密码和盐值后计算对应的哈希值并返回结果
     * @param str 传入的密码
     * @param salt 传入的盐值
     * @return 哈希加盐后的结果
     */
    public static String getMD5Str(String str, String salt) {
        return new SimpleHash("MD5", str, ByteSource.Util.bytes(salt), 1).toString();
    }

    /**
     * 随机生成盐值的函数，在用户修改密码或者注册账户时都应该调用该函数获取对应的盐值并保存在数据库中
     * 在以后登陆校验时不需要再次生成，直接从数据库中读出
     * @return
     */
    public static String getSalt() {
        SecureRandom random = new SecureRandom();
        byte[] seed = new byte[20];
        random.nextBytes(seed);
        String salt = Hex.encodeHexString(seed);
        return salt;
    }

    public static void main(String[] args) {
        // 生成一个盐
        String salt = getSalt();
        String password = getMD5Str("123456", salt);
        System.out.println("salt = " + salt);
        System.out.println("password = " + password);
    }
}
