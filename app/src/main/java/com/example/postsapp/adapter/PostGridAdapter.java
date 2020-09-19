package com.example.postsapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.postsapp.R;
import com.example.postsapp.model.PostModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PostGridAdapter  extends RecyclerView.Adapter<PostGridAdapter.PostGridViewHolder>{

    private List<PostModel> postsList = new ArrayList<>();
    private Context mContext;
    private FragmentManager mFragmentManager;


    private OnItemClickListener mListener;



    public interface OnItemClickListener {
        void onItemClicked(PostModel postItem);


    }

    public PostGridAdapter(Context context, FragmentManager fragmentManager, OnItemClickListener listener) {
        mContext = context;
        mFragmentManager = fragmentManager;
        mListener = listener;

    }
    @NonNull
    @Override
    public PostGridViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext)
                .inflate(R.layout.posts_grid_ite_list, parent, false);
        return new PostGridAdapter.PostGridViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PostGridViewHolder holder, int position) {
        holder.bindPost(postsList.get(position),mListener);
    }

    @Override
    public int getItemCount() {
        return postsList.size();
    }
    public void setList(List<PostModel> moviesList) {
        this.postsList = moviesList;
        notifyDataSetChanged();
    }
    public class PostGridViewHolder extends RecyclerView.ViewHolder {


        @BindView(R.id.title_tv1)
        TextView mPostTitle1;
        @BindView(R.id.delete_ib1)
        ImageButton mDeleteIb1;
        @BindView(R.id.post_iv1)
        ImageView mPostIv1;

        @BindView(R.id.grid_cl)
        ConstraintLayout mGCl;
        public PostGridViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }

        public void bindPost(PostModel postModel, OnItemClickListener listener) {
            mPostTitle1.setText("Title");

//            Glide.with(mContext).load(postModel.getThumbnailUrl()).into(mPostIv1);
         Glide.with(mContext).load(R.drawable.cat).into(mPostIv1);


            mDeleteIb1.setOnClickListener(v -> { {
                int position=getAdapterPosition();
                postsList.remove(position);
                notifyItemRemoved(position);
            }
            });
            mGCl.setOnClickListener(v -> { {
                listener.onItemClicked(postModel);
            }
            });

        }
    }
}