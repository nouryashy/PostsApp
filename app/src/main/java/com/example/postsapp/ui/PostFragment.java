package com.example.postsapp.ui;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.postsapp.utils.Constants;
import com.example.postsapp.adapter.PostAdapter;
import com.example.postsapp.adapter.PostGridAdapter;
import com.example.postsapp.model.PostModel;
import com.example.postsapp.viewmodel.PostViewModel;
import com.example.postsapp.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class PostFragment extends Fragment {
    @BindView(R.id.posts_recycle_view)
    RecyclerView recyclerView;

    @BindView(R.id.grid_d_bt)
    Button mGridDBt;
    @BindView(R.id.grid_l_bt)
    Button mGridLBt;


    @BindView(R.id.ver_d_bt)
    Button mVerDBt;
    @BindView(R.id.ver_l_bt)
    Button mVerLBt;


    @BindView(R.id.hor_d_bt)
    Button mHorDBt;
    @BindView(R.id.hor_l_bt)
    Button mHorLBt;

    GridLayoutManager mGridLayoutManager;

    PostViewModel mPostViewModel;
    FragmentManager mFragmentManager;
    PostAdapter mPostAdapter;
    PostGridAdapter mPostGridAdapter;
    List<PostModel> mPostModels;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_post, container, false);
        ButterKnife.bind(this, view);
        mFragmentManager = getFragmentManager();
        controlButtonsAtVerView();
        mPostViewModel = ViewModelProviders.of(this).get(PostViewModel.class);
        intRecList();
        return view;
    }


    private void intRecList() {
        fillAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(mPostAdapter);


    }

    private void intRecGrid() {
        mPostGridAdapter = new PostGridAdapter(getContext(), mFragmentManager, postModel ->
        {
            Bundle bundle = new Bundle();
            bundle.putSerializable(Constants.POST_OBJECT, postModel);
            replaceFragmentWithBundle(new PostDetailsFragment(), mFragmentManager,
                    R.id.main_frame_layout, bundle, PostDetailsFragment.class.getSimpleName(), true);
        });


        mGridLayoutManager = new GridLayoutManager(getContext(), 2);
        recyclerView.setLayoutManager(mGridLayoutManager);
        recyclerView.setAdapter(mPostGridAdapter);

        mPostViewModel.getAllPosts().observe(this, new Observer<List<PostModel>>() {
            @Override
            public void onChanged(List<PostModel> postModels) {
                mPostGridAdapter.setList(postModels);
            }
        });

    }

    private void intRecListHor() {


        fillAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, true));
        recyclerView.setAdapter(mPostAdapter);

    }

    private void fillAdapter() {
        mPostAdapter = new PostAdapter(getContext(), mFragmentManager, postModel ->
        {

            Bundle bundle = new Bundle();
            bundle.putSerializable(Constants.POST_OBJECT, postModel);
            replaceFragmentWithBundle(new PostDetailsFragment(), mFragmentManager,
                    R.id.main_frame_layout, bundle, PostDetailsFragment.class.getSimpleName(), true);
        });

        mPostViewModel.getAllPosts().observe(this, new Observer<List<PostModel>>() {
            @Override
            public void onChanged(List<PostModel> postModels) {
                mPostAdapter.setList(postModels);
            }
        });
    }

    @OnClick(R.id.ver_l_bt)
    public void openList() {
        controlButtonsAtVerView();
        intRecList();


    }

    @OnClick(R.id.hor_l_bt)
    public void openHor() {
        controlButtonsAtHorView();
        intRecListHor();
    }


    @OnClick(R.id.grid_l_bt)
    public void openMenu() {
        controlButtonsAtGridView();
        intRecGrid();
    }

    private void controlButtonsAtVerView() {
        mVerDBt.setVisibility(View.VISIBLE);
        mVerLBt.setVisibility(View.INVISIBLE);

        mGridDBt.setVisibility(View.INVISIBLE);
        mGridLBt.setVisibility(View.VISIBLE);

        mHorDBt.setVisibility(View.INVISIBLE);
        mHorLBt.setVisibility(View.VISIBLE);

    }
    private void controlButtonsAtGridView() {
        mGridDBt.setVisibility(View.VISIBLE);
        mGridLBt.setVisibility(View.INVISIBLE);

        mHorDBt.setVisibility(View.INVISIBLE);
        mHorLBt.setVisibility(View.VISIBLE);

        mVerDBt.setVisibility(View.INVISIBLE);
        mVerLBt.setVisibility(View.VISIBLE);

    }

    private void controlButtonsAtHorView() {
        mHorDBt.setVisibility(View.VISIBLE);
        mHorLBt.setVisibility(View.INVISIBLE);

        mVerDBt.setVisibility(View.INVISIBLE);
        mVerLBt.setVisibility(View.VISIBLE);

        mGridDBt.setVisibility(View.INVISIBLE);
        mGridLBt.setVisibility(View.VISIBLE);

    }


    public static void replaceFragmentWithBundle(Fragment fragment, FragmentManager fragmentManager,
                                                 int containerViewId, Bundle bundle, String TAG,
                                                 boolean isAddToBackStack) {
        assert fragmentManager != null;
        fragment.setArguments(bundle);
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        if (isAddToBackStack) {
            fragmentTransaction.addToBackStack(null);
        }
        fragmentTransaction.replace(containerViewId, fragment, TAG)
                .commit();
    }
}