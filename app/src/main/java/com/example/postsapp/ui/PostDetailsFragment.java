package com.example.postsapp.ui;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.postsapp.utils.Constants;
import com.example.postsapp.model.PostModel;
import com.example.postsapp.R;

import butterknife.BindView;
import butterknife.ButterKnife;


public class PostDetailsFragment extends Fragment {
    private FragmentManager fragmentManager;

    private PostModel postModel;

    @BindView(R.id.title_tv)
    TextView textView;

    @BindView(R.id.post_ig)
    ImageView ig;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
          View view=inflater.inflate(R.layout.fragment_post_details, container, false);

        ButterKnife.bind(this, view);
        fragmentManager = getFragmentManager();

        postModel = (PostModel) getArguments().getSerializable(Constants.POST_OBJECT);
        textView.setText(postModel.getTitle());
//        Glide.with(getActivity()).load(postModel.getThumbnailUrl()).into(ig);
       Glide.with(getActivity()).load(R.drawable.cat).into(ig);

        return view; }
}