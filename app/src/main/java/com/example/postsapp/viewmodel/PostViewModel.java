package com.example.postsapp.viewmodel;


import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.postsapp.model.PostModel;
import com.example.postsapp.repository.PostRepository;

import java.util.List;


public class PostViewModel  extends AndroidViewModel {
    private PostRepository mPostRepository;
    public PostViewModel(@NonNull Application application) {
        super(application);
        mPostRepository  =new PostRepository(application);

    }
    public LiveData<List<PostModel>> getAllPosts(){
        return mPostRepository.getAllPro();
    }


}
