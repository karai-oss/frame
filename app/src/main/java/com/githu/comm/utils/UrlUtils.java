package com.githu.comm.utils;

import java.util.Map;
import java.util.Set;

/**
 * Url 工具类
 *
 * @author Mr.xie
 * @Date 2025/2/21
 */
public class UrlUtils {


    /**
     * 使用 map 去填充 整个URL 用于GET 请求
     *
     * @param url 没有填充 请求参数的URL
     * @param map 请求参数
     */
    public static String mapToUri(String url, Map<String, String> map) {
        StringBuffer urlBuffer = new StringBuffer();
        if (url.endsWith("/")) {
            url = url.substring(0, url.lastIndexOf("/"));
        }

        urlBuffer.append(url).append("?");
        Set<String> keys = map.keySet();

        for (String key : keys) {
            String value = map.get(key);
            urlBuffer.append(key).append("=").append(value).append("&");

        }
        String finalUrl = urlBuffer.substring(0, urlBuffer.lastIndexOf("&"));
        return finalUrl;

    }
}
