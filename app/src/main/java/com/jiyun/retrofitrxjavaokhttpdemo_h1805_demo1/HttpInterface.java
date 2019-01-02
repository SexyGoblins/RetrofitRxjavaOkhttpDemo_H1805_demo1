package com.jiyun.retrofitrxjavaokhttpdemo_h1805_demo1;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.HeaderMap;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface HttpInterface {

    /**
     *
     * @param start 开始位置
     * @param count  数量
     * @return
     */
    @GET("top250")
    Call<Movie250Modle> getMovie(@Query("start") int start, @Query("count") int count);


//动态添加头布局
    @POST("login")
    @FormUrlEncoded
    Call<Object> login2(@Header("User-Agent") String userAgent,
                        @HeaderMap Map<String,String> headers,
                        @Field("usename") String usename,
                        @Field("password") String password);

}
