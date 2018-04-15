package com.example.belensarabia.myapplication.adapters;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
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
    private Activity activity;

    private OnItemClickListener listener;


    public MyAdapter(List<Movie> movies, int layout, Activity activity, OnItemClickListener listener) {

        this.movies = movies;
        this.layout = layout;
        this.activity = activity;
        this.listener = listener;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(layout, parent, false);
        ViewHolder vh = new ViewHolder(v);

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

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener, MenuItem.OnMenuItemClickListener {

        private TextView textViewName;
        private ImageView imageViewPoster;
        private TextView textViewPunctuation;


        public ViewHolder(View itemView) {
            super(itemView);
            textViewName = itemView.findViewById(R.id.testViewTitle);
            imageViewPoster = itemView.findViewById(R.id.imageViewPoster);
            textViewPunctuation = itemView.findViewById(R.id.textViewPunctuation);

            // Register oncreate menu listener
            itemView.setOnCreateContextMenuListener(this);
        }

        public void bind(final Movie movie, final OnItemClickListener listener) {

            this.textViewName.setText(movie.getName());

            this.textViewPunctuation.setText(String.valueOf(movie.getPunctuation()));
            Picasso.with(activity).load(movie.getPoster()).fit().into(imageViewPoster);


            this.imageViewPoster.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onItemClick(movie, getAdapterPosition());
                }
            });

        }

        @Override
        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {

            Movie movieSelected = movies.get(this.getAdapterPosition());

            menu.setHeaderTitle(movieSelected.getName());


            MenuInflater inflater = activity.getMenuInflater();
            inflater.inflate(R.menu.context_menu_movie, menu);

            for (int i = 0; i < menu.size(); i++)
                menu.getItem(i).setOnMenuItemClickListener(this);

        }


        public boolean onMenuItemClick(MenuItem item) {

            switch (item.getItemId()) {
                case R.id.delete_movie:
                    movies.remove(getAdapterPosition());
                    notifyItemRemoved(getAdapterPosition());
                    return true;
                default:
                    return false;
            }
        }
    }

    public interface OnItemClickListener {
        void onItemClick(Movie movie, int position);
    }
}
