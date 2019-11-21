package com.keshar.daggerpractice.network.main;

import com.keshar.daggerpractice.models.Posts;

import java.util.List;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MainApi {

    @GET("posts")
    Flowable<List<Posts>> getPostsFromUser(
            @Query("userId") int id
    );
}
