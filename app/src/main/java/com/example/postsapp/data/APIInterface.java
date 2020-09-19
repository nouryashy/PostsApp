package com.example.postsapp.data;

import com.example.postsapp.model.PostModel;

import java.util.List;

import io.reactivex.Single;
import retrofit2.http.GET;

public interface APIInterface {
    @GET("posts")
    public Single<List<PostModel>> getPosts();
}
