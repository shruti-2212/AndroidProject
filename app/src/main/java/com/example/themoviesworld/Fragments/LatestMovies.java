package com.example.themoviesworld.Fragments;

import com.example.themoviesworld.R;
import com.example.themoviesworld.base.BaseMovieFragment;

import static com.example.themoviesworld.DBConstants.TYPE3;


public class LatestMovies extends BaseMovieFragment {

    @Override
    protected String getType() {
        return TYPE3;
    }

}
