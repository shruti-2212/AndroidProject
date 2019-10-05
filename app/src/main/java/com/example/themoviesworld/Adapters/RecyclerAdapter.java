package com.example.themoviesworld.Adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.themoviesworld.Activities.MovieDetailActivity;
import com.example.themoviesworld.Models.Result;
import com.example.themoviesworld.R;

import java.util.ArrayList;
import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder> {
    private Context mContext;
    private RequestOptions options;
    private List<Result> mdata = new ArrayList<>();


    public RecyclerAdapter(Context mContext) {
        this.mContext = mContext;
        options = new RequestOptions().centerCrop().override(100, 150).placeholder(R.drawable.drawable_loading_shape).error(R.drawable.drawable_loading_shape);
    }

    public void setMdata(List<Result> mdata) {
        this.mdata = mdata;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        final LayoutInflater inflater = LayoutInflater.from(mContext);
        view = inflater.inflate(R.layout.recycler_item_view, parent, false);
        final MyViewHolder viewHolder = new MyViewHolder(view);
        viewHolder.container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, MovieDetailActivity.class);
                Bundle bundle = new Bundle();
                Log.i("TAG", "onClick: " + viewHolder.container);
                bundle.putString("Title", mdata.get(viewHolder.getAdapterPosition()).getTitle());
                bundle.putString("Release Date", mdata.get(viewHolder.getAdapterPosition()).getReleaseDate());
                bundle.putString("Description", mdata.get(viewHolder.getAdapterPosition()).getOverview());
                bundle.putString("ImagePath", mdata.get(viewHolder.getAdapterPosition()).getPosterPath());

                intent.putExtra("Bundle", bundle);

                mContext.startActivity(intent);


            }
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.title.setText(mdata.get(position).getTitle());
        // holder.rating.setText( mdata.get(position).getPopularity());

        holder.releaseDate.setText(mdata.get(position).getReleaseDate());


        Glide.with(mContext).load(mdata.get(position).getPosterPath()).apply(options).into(holder.im_thumbnail);

    }

    @Override
    public int getItemCount() {
        return mdata.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView title;
        TextView rating;
        TextView releaseDate;
        TextView studio;
        ImageView im_thumbnail;
        LinearLayout container;


        public MyViewHolder(@NonNull View itemView) {

            super(itemView);

            title = itemView.findViewById(R.id.movie_name);
            rating = itemView.findViewById(R.id.popularity);
            releaseDate = itemView.findViewById(R.id.release_date);
            studio = itemView.findViewById(R.id.studio);
            im_thumbnail = itemView.findViewById(R.id.thumbnail);
            container = itemView.findViewById(R.id.container);

        }
    }
}
