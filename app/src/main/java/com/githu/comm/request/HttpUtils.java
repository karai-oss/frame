package com.githu.comm.request;

import android.content.Context;

import androidx.annotation.NonNull;

import com.blankj.utilcode.util.GsonUtils;
import com.blankj.utilcode.util.SPUtils;
import com.githu.comm.utils.UrlUtils;
import com.githu.frame.R;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * 封装Http请求  Android框架
 *
 * @author <a href="https://github.com/karai-oss">Mr.xie</a>
 * @Date 2025/2/20
 */
public class HttpUtils {

    private static OkHttpClient.Builder requestClientBuilder = null;

    private static OkHttpClient requestClient = null;

    private static Context context;

    private static Request request = null;
    private static Request.Builder requestBuilder = null;

    private Map<String, String> requestMap = new HashMap<>();

    private final int errorCode = 0;

    private static IRequestCallBack requestCallBack;


    /**
     * HTTP GET 请求方法
     *
     * @param url           请求的<b>后缀URL</b>
     * @param requestParams 请求参数 一个MAP 字典
     * @param isToken       是否需要携带token信息
     * @param tag           请求的标识 用于区分一个Activity/fragment的多个请求避免请求混乱
     */
    public void doGet(String url, Map<String, String> requestParams, boolean isToken, String tag) {
        if (isToken) {
            requestBuilder.addHeader(
                    context.getString(R.string.TOKEN),
                    SPUtils.getInstance().getString(context.getString(R.string.TOKEN))
            );
        }
        // 构建请求url
        url = UrlUtils.mapToUri(url, requestParams);

        // 和前缀拼接最终URL
        checkUrl(requestBuilder.get().getUrl$okhttp().url().toString(), url);
        requestBuilder.url(requestBuilder.get().getUrl$okhttp().url() + url);
        request = requestBuilder.build();
        requestClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                String message = e.getMessage();
                requestCallBack.requestError(new RequestCode(errorCode, message, tag, null));
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                int code = response.code();
                ResponseBody responseBody = response.body();
                if (code != 200) {
                    requestCallBack.requestError(new RequestCode(code, "数据请求失败", tag, null));
                    return;
                }
                requestCallBack.requestSuccess(new RequestCode(code, responseBody.string(), tag, null));
            }
        });

    }


    /**
     * HTTP POST 请求方法
     *
     * @param url           请求的<b>后缀URL</b>
     * @param requestParams 请求参数 一个MAP 字典
     * @param isToken       是否需要携带token信息
     * @param tag           请求的标识 用于区分一个Activity/fragment的多个请求避免请求混乱
     */
    public void doPost(String tag, String url, Map<String, String> requestParams, boolean isToken) {
        if (isToken) {
            requestBuilder.addHeader(
                    context.getString(R.string.TOKEN),
                    SPUtils.getInstance().getString(context.getString(R.string.TOKEN))
            );
        }
        RequestBody requestBody = RequestBody.create(
                MediaType.parse("application/json"),
                GsonUtils.toJson(requestParams)
        );
        requestBuilder.post(requestBody);
        requestBuilder.url(requestBuilder.getUrl$okhttp().url() + url);
        request = requestBuilder.build();
        requestClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                String message = e.getMessage();
                requestCallBack.requestError(new RequestCode(errorCode, message, tag, null));
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                int code = response.code();
                ResponseBody responseBody = response.body();
                if (code != 200) {
                    requestCallBack.requestError(new RequestCode(code, "数据请求失败", tag, null));
                    return;
                }
                requestCallBack.requestSuccess(new RequestCode(code, responseBody.string(), tag, null));
            }
        });

    }


    /**
     * HTTP PUT 请求方法
     *
     * @param url           请求的<b>后缀URL</b>
     * @param requestParams 请求参数 一个MAP 字典
     * @param isToken       是否需要携带token信息
     * @param tag           请求的标识 用于区分一个Activity/fragment的多个请求避免请求混乱
     */
    public void doPut(String tag, String url, Map<String, String> requestParams, boolean isToken) {
        if (isToken) {
            requestBuilder.addHeader(
                    context.getString(R.string.TOKEN),
                    SPUtils.getInstance().getString(context.getString(R.string.TOKEN))
            );
        }
        RequestBody requestBody = RequestBody.create(
                MediaType.parse("application/json"),
                GsonUtils.toJson(requestParams)
        );
        requestBuilder.put(requestBody);
        requestBuilder.url(requestBuilder.getUrl$okhttp().url() + url);
        request = requestBuilder.build();
        requestClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                String message = e.getMessage();
                requestCallBack.requestError(new RequestCode(errorCode, message, tag, null));
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                int code = response.code();
                ResponseBody responseBody = response.body();
                if (code != 200) {
                    requestCallBack.requestError(new RequestCode(code, "数据请求失败", tag, null));
                    return;
                }
                requestCallBack.requestSuccess(new RequestCode(code, responseBody.string(), tag, null));
            }
        });

    }


    public static class Builder {

        private long timeOut = 2000;

        public HttpUtils build() {
            if (requestCallBack == null) {
                throw new IllegalArgumentException("you should give a requestCallBack Object");
            }
            requestClientBuilder = new OkHttpClient.Builder();
            requestClientBuilder.readTimeout(timeOut, TimeUnit.MILLISECONDS);
            requestClientBuilder.writeTimeout(timeOut, TimeUnit.MILLISECONDS);
            requestClientBuilder.writeTimeout(timeOut, TimeUnit.MILLISECONDS);
            requestBuilder = new Request.Builder();
            requestBuilder.url(getContext().getString(R.string.BASE_URL));
            request = requestBuilder.build();
            requestClient = requestClientBuilder.build();

            return new HttpUtils();
        }

        public Context getContext() {
            return context;
        }

        public void setContext(Context _context) {
            context = _context;
        }

        public long getTimeOut() {
            return timeOut;
        }

        public void setTimeOut(long timeOut) {
            this.timeOut = timeOut;
        }


        public void setRequestCallBack(IRequestCallBack _requestCallBack) {
            requestCallBack = _requestCallBack;
        }
    }


    /**
     * 检查 URL 拼接之前 前缀URL 和 后缀URL 拼接是否合法
     *
     * @param prefixUrl 前缀URL
     * @param suffixUrl 后缀URL
     */
    public void checkUrl(String prefixUrl, String suffixUrl) {
        if (prefixUrl == null || suffixUrl == null) {
            throw new IllegalArgumentException("you should give a prefixUrl and suffixUrl Object");
        }
        if (prefixUrl.endsWith("/") && suffixUrl.startsWith("/")) {
            throw new IllegalArgumentException("suffix url is illegal");
        }
        if (!prefixUrl.endsWith("/") && !suffixUrl.startsWith("/")) {
            throw new IllegalArgumentException("url is illegal");
        }
    }

}
