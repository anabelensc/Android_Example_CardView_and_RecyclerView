package com.example.belensarabia.myapplication.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.belensarabia.myapplication.R;
import com.example.belensarabia.myapplication.models.Movie;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by belensarabia on 31/3/18.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private List<Movie> movies;
    private int layout;
    private OnItemClickListener listener;

    private Context context;

    public MyAdapter(List<Movie> movies, int layout, OnItemClickListener listener) {
        this.movies = movies;
        this.layout = layout;
        this.listener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(layout, parent, false);
        ViewHolder vh = new ViewHolder(v);
        context = parent.getContext();
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.bind(movies.get(position), listener);

    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView textViewName;
        private ImageView imageViewPoster;

        public ViewHolder(View itemView) {
            super(itemView);
            textViewName = itemView.findViewById(R.id.testViewTitle);
            imageViewPoster = itemView.findViewById(R.id.imageViewPoster);
        }

        public void bind(final Movie movie, final OnItemClickListener listener) {

            textViewName.setText(movie.getName());
            Picasso.with(context).load(movie.getPoster()).fit().into(imageViewPoster);
            //imageViewPoster.setImageResource(movie.getPoster());

            itemView.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    listener.onItemClick(movie, getAdapterPosition());
                }
            });
        }
    }

    public interface OnItemClickListener {
        void onItemClick(Movie movie, int position);
    }
}
