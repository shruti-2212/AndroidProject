package com.example.themoviesworld.Api;

import com.example.themoviesworld.Models.Example;

import retrofit2.Call;
import retrofit2.http.GET;


public interface Api {
    String baseUrl = "https://api.themoviedb.org/3/movie/";
    String api_key = "380865196e40beffdb3712e87b310dcf";
    String imageUrl = "https://image.tmdb.org/t/p/w600_and_h900_bestv2/";

    @GET("popular?api_key=380865196e40beffdb3712e87b310dcf&language=en-US&page=1")
    Call<Example> getPopularMovies();

    @GET("top_rated?api_key=380865196e40beffdb3712e87b310dcf&language=en-US&page=1")
    Call<Example> getTopRatedMovies();

    @GET("upcoming?api_key=380865196e40beffdb3712e87b310dcf&language=en-US&page=1")
    Call<Example> getUpcomingMovies();

    @GET("latest?api_key=380865196e40beffdb3712e87b310dcf&language=en-US&page=1")
    Call<Example> getLatestMovies();

    @GET("/3/movie/{movieId}")
    Call<Example> getMovieDetails();

    @GET("3/genre/movie/list")
    Call<Example> getGenres();
}

