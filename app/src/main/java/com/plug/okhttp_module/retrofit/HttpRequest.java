package com.plug.okhttp_module.retrofit;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.GET;

/**
 * Created by A35 on 2020/3/26
 * Email:yin13753884368@163.com
 * CSDN:http://blog.csdn.net/yin13753884368/article
 * Github:https://github.com/taxiao213
 */
public interface HttpRequest {

    @GET("main")
    Call<Object> getDate(@FieldMap HashMap<Object, Object> map);
}
