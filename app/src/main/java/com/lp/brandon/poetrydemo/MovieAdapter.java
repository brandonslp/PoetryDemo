package com.lp.brandon.poetrydemo;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.lp.brandon.poetrydemo.models.Movie;

import java.util.List;

/**
 * Created by brand on 10/11/2016.
 */
public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder>{

    private List<Movie> movies;
    private Context context;
    private final OnItemClickListener listener;

    public MovieAdapter(List<Movie> movies, Context context, OnItemClickListener listener) {
        this.movies = movies;
        this.context = context;
        this.listener = listener;
    }

    @Override
    public MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list, parent, false);
        MovieViewHolder viewHolder = new MovieViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MovieViewHolder holder, int position) {
        holder.title.setText(movies.get(position).getTitle());
        holder.desc.setText(movies.get(position).getPlot());
        Picasso.with(context).load(movies.get(position).getPoster()).resize(100,150).placeholder(R.drawable.ic_action_search).error(R.mipmap.ic_launcher).into(holder.thumbnail);
        holder.onClick(movies.get(position),listener);
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }



    public static class MovieViewHolder extends RecyclerView.ViewHolder{
        CardView cv;
        TextView title;
        TextView desc;
        ImageView thumbnail;
        public MovieViewHolder(View itemView) {
            super(itemView);
            cv = (CardView) itemView.findViewById(R.id.itemCardview);
            title = (TextView) itemView.findViewById(R.id.itemTitle);
            desc = (TextView) itemView.findViewById(R.id.itemDesc);
            thumbnail = (ImageView) itemView.findViewById(R.id.itemImg);
        }

        public void onClick (final Movie movie, final OnItemClickListener listener){
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onItemClick(movie);
                }
            });
        }
    }
    public interface OnItemClickListener {
        void onItemClick(Movie item);
    }

}
