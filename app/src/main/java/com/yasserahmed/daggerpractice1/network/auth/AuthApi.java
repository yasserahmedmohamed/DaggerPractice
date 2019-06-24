package com.yasserahmed.daggerpractice1.network.auth;

import com.yasserahmed.daggerpractice1.Models.User;

import io.reactivex.Flowable;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface AuthApi {
    @GET("users/{id}")
    Flowable<User> getuser(@Path("id")int id);
}
