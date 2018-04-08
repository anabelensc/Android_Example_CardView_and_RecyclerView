package com.example.belensarabia.myapplication.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import com.example.belensarabia.myapplication.adapters.MyAdapter;
import com.example.belensarabia.myapplication.R;
import com.example.belensarabia.myapplication.models.Movie;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Movie> movies;

    private RecyclerView mRecycleView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private int counter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        movies = this.getAllMovies();
        mRecycleView = (RecyclerView) findViewById(R.id.recyclerView);
        mLayoutManager = new LinearLayoutManager(this);

        mAdapter = new MyAdapter(movies, R.layout.recycler_view_item, new MyAdapter.OnItemClickListener() {

            @Override
            public void onItemClick(Movie movie, int position) {
                deleteMovie(position);
            }

        });

        mRecycleView.setItemAnimator(new DefaultItemAnimator());

        mRecycleView.setLayoutManager(mLayoutManager);
        mRecycleView.setAdapter(mAdapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add_name:
                this.addMovie(0);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private List<Movie> getAllMovies() {
        return new ArrayList<Movie>() {{
            add(new Movie("Harry Potter", R.drawable.harrypotter));
            add(new Movie("Inception", R.drawable.inception));
        }};
    }

    private void addMovie(int position) {
        movies.add(position, new Movie("New image " + (++counter), R.drawable.newmovie));
        mAdapter.notifyItemInserted(position);
        mLayoutManager.scrollToPosition(position);
    }

    private void deleteMovie(int position) {
        movies.remove(position);
        mAdapter.notifyItemRemoved(position);
    }
}
