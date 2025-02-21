package com.githu.comm.utils;


import java.util.Random;
import java.util.UUID;

/**
 * 字符串工具类
 *
 * @author Mr.xie
 * @Date 2025/2/19
 */
public class StringUtils {

    /**
     * 生成不带 - 的UUID 字符串
     */
    public  String uuid(){
        return UUID.fromString(UUID.randomUUID().toString()).toString().replaceAll("-" , "");
    }
}
