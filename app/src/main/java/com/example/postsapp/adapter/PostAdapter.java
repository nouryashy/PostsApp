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

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.PostViewHolder> {
    private List<PostModel> postsList = new ArrayList<>();
    private Context mContext;
    private FragmentManager mFragmentManager;
    private PostModel postModel;

    private OnItemClickListener mListener;


    public interface OnItemClickListener {
        void onItemClick(PostModel postModel);


    }


    public PostAdapter(Context context, FragmentManager fragmentManager, OnItemClickListener listener) {
        mContext = context;
        mFragmentManager = fragmentManager;
        mListener = listener;


    }

    @NonNull
    @Override
    public PostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(mContext)
                .inflate(R.layout.posts_ver_ite_list, parent, false);
        return new PostAdapter.PostViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PostViewHolder holder, int position) {
        holder.bindPost(postsList.get(position), mListener);
    }

    @Override
    public int getItemCount() {
        return postsList.size();
    }

    public void setList(List<PostModel> moviesList) {
        this.postsList = moviesList;
        notifyDataSetChanged();
    }


    public class PostViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.title_tv)
        TextView mPostTitle;
        @BindView(R.id.delete_ib)
        ImageButton mDeleteIb;
        @BindView(R.id.post_iv)
        ImageView mPostIv;
        @BindView(R.id.ver_cl)
        ConstraintLayout mVerCl;

        public PostViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }


        public void bindPost(PostModel postModel, OnItemClickListener listener) {
            mPostTitle.setText(postModel.getTitle());


//            Glide.with(mContext).load(postModel.getThumbnailUrl()).into(mPostIv);
          Glide.with(mContext).load(R.drawable.cat).into(mPostIv);


            mDeleteIb.setOnClickListener(v -> { {
                int position=getAdapterPosition();
                postsList.remove(position);
                notifyItemRemoved(position);
            }
            });
            mVerCl.setOnClickListener(v -> { {
                listener.onItemClick(postModel);
            }
            });
        }
    }
}
