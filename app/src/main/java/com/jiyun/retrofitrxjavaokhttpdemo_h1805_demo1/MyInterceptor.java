package com.jiyun.retrofitrxjavaokhttpdemo_h1805_demo1;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

class MyInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        HttpUrl httpUrl = request.url()
                .newBuilder()
                // add common parameter
                .addQueryParameter("token", "123")
                .addQueryParameter("username", "tt")
                .build();
        Request build = request.newBuilder()
                // add common header
                .addHeader("contentType", "text/json")
                .url(httpUrl)
                .build();
        Response response = chain.proceed(build);
        return response;
    }
}
