package com.example.postsapp.repository;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;


import com.example.postsapp.model.PostModel;
import com.example.postsapp.data.RetrofitClient;

import java.util.List;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


public class PostRepository {
    public static final String TAG = PostRepository.class.getSimpleName();
    private List<PostModel> mProjectItems;
    private MutableLiveData<List<PostModel>> mutableLiveData=new MutableLiveData<>();
    private Application mApplication;

    public PostRepository(Application application) {
        mApplication = application;
    }
    public MutableLiveData<List<PostModel>>getAllPro(){

        Single<List<PostModel>> obserable= RetrofitClient.getApi().getPosts()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
obserable.subscribe(o -> mutableLiveData.setValue(o), e -> Log.d(TAG, "getPosts: " + e));


        return mutableLiveData;

}
//             .enqueue(new Callback<List<PostModel>>() {
//            @Override
//            public void onResponse(Call<List<PostModel>> call, Response<List<PostModel>> response) {
//
//                mutableLiveData.setValue(response.body());
//            }
//
//            @Override
//            public void onFailure(Call<List<PostModel>> call, Throwable t) {
//
//            }
//        });
//        return mutableLiveData ;
//    }
}
