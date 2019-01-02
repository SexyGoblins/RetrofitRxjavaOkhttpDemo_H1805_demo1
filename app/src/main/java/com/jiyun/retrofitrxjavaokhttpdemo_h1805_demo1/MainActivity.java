package com.jiyun.retrofitrxjavaokhttpdemo_h1805_demo1;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.io.File;
import java.io.IOException;
import java.util.List;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private OkHttpClient okHttpClient;
    private HttpInterface httpInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();
    }

    private void initData() {
        //1.定义baseURL  //Basrurl 代表服务器根地址   baseurl必须以斜线（/）结尾
        //否则报IllegalArgumentException 非法参数异常
        String Basrurl = "Https://api.douban.com/v2/movie/";


        //2.创建retrofit实例对象，设置baseurl及gson解析器
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Basrurl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        //3.通过retrofit动态代理拿到接口对象实例
        httpInterface = retrofit.create(HttpInterface.class);

        //4.拿到getMovie返回的Call对象：

        Call<Movie250Modle> movie = httpInterface.getMovie(0, 10);

        //5.执行异步enqueue请求操作
        movie.enqueue(new Callback<Movie250Modle>() {
            @Override
            public void onResponse(Call<Movie250Modle> call, Response<Movie250Modle> response) {
                Movie250Modle movie250Modle = response.body();
                List<Movie250Modle.SubjectsBean> movie250ModleSubjects = movie250Modle.getSubjects();
                for(int i = 0; i < movie250ModleSubjects.size(); i++){
                    Log.e("TAG","实体类TOP250电影第"+(i+1)+"的名字：  "+movie250ModleSubjects.get(i).getTitle());
                }
            }

            @Override
            public void onFailure(Call<Movie250Modle> call, Throwable t) {

            }
        });


    }


     /**
          * 判断是否有网
          *
          * @param context
          * @return
          */
         public static boolean isNetworkAvailable(Context context) {
             if (context != null) {
                 ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
                 NetworkInfo info = cm.getActiveNetworkInfo();
                 if (info != null) {
                     return info.isAvailable();
                 }
             }
             return false;
         }





    }

