package com.example.themoviesworld.Fragments;

import com.example.themoviesworld.R;
import com.example.themoviesworld.base.BaseMovieFragment;

import static com.example.themoviesworld.DBConstants.TYPE2;


public class PopularMovies extends BaseMovieFragment {

    @Override
    protected String getType() {
        return TYPE2;
    }

    @Override
    protected int getRecyclerViewId() {
        return R.id.recyclerView_movies;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_movies;
    }

}
