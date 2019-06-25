package com.yasserahmed.daggerpractice1.network.main;

import com.yasserahmed.daggerpractice1.Models.Posts;

import java.util.List;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MainApi {
    // posts?userId=1
    @GET("posts")
    Flowable<List<Posts>> getPostForUser(@Query("userId") int id);

}
