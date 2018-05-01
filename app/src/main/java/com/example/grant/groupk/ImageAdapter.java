package com.example.grant.groupk;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ImageViewHolder>{
    private Context mContext;
    private List<Post> mPost;
    private OnClickListener mListener;

    public ImageAdapter(Context context, List<Post> post){
        mContext =context;
        mPost = post;
    }
    @Override
    public ImageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.catalog_tem, parent, false);
        return new ImageViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ImageViewHolder holder, int position) {
        Post postCurrent = mPost.get(position);
        holder.textView.setText(postCurrent.getTitle());
        holder.authorView.setText(postCurrent.getAuthor());
        Picasso.with(mContext)
                .load(postCurrent.getImageU())
                .fit()
                .placeholder(R.mipmap.ic_launcher)
                .centerCrop()
                .into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        return mPost.size();
    }

    public class ImageViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
            public TextView textView;
            public ImageView imageView;
            public TextView authorView;

            public ImageViewHolder(View itemView){
                super(itemView);
                authorView = itemView.findViewById(R.id.author);
                textView = itemView.findViewById(R.id.Title_C);
                imageView = itemView.findViewById(R.id.imageA);

            }

        public void onClick(View v) {
            if (mListener != null) {
                int position = getAdapterPosition();
                if (position != RecyclerView.NO_POSITION) {
                    mListener.onItemClick(v);
                }
            }
        }





    }
    public interface OnClickListener {
        void onItemClick(View v);
    }

    public void setOnItemClickListener(OnClickListener listener) {
        mListener = listener;
    }




}
