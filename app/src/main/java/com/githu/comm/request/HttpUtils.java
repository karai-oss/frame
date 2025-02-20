package com.githu.comm.request;

import android.content.Context;

import androidx.annotation.NonNull;

import com.blankj.utilcode.util.GsonUtils;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.Utils;
import com.bumptech.glide.RequestBuilder;
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
 * 封装Http请求
 *
 * @author Mr.xie
 * @Date 2025/2/20
 */
public class HttpUtils {

    private static OkHttpClient.Builder requestClientBuilder = null;

    private static OkHttpClient requestClient = null;

    private static Context context;

    private static Request request = null;
    private static Request.Builder requestBuilder = null;

    private Map<String, String> requestMap = new HashMap<>();

    public void doGet(String url, Map<String, String> requestParams, boolean isToken) {
        if (isToken) {
            requestBuilder.addHeader(
                    context.getString(R.string.TOKEN),
                    SPUtils.getInstance().getString(context.getString(R.string.TOKEN))
            );
        }
        url = UrlUtils.mapToUri(url, requestMap);
        requestBuilder.url(context.getString(R.string.BASE_URL) + url);
        request = requestBuilder.build();
        requestClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {

            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {

            }
        });

    }


    public void doPost(String url, Map<String, String> requestParams, boolean isToken) {
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
        request = requestBuilder.build();
        requestClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {

            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {

            }
        });

    }

    public static class Builder {


        private long timeOut = 2000;


        public void build() {
            requestClientBuilder = new OkHttpClient.Builder();
            requestClientBuilder.readTimeout(timeOut, TimeUnit.MILLISECONDS);
            requestClientBuilder.writeTimeout(timeOut, TimeUnit.MILLISECONDS);
            requestClientBuilder.writeTimeout(timeOut, TimeUnit.MILLISECONDS);
            requestBuilder = new Request.Builder();

            request = requestBuilder.build();
            requestClient = requestClientBuilder.build();

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

    }

}
