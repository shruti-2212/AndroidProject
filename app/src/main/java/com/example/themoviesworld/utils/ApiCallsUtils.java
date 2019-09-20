package com.example.themoviesworld.utils;

import android.util.Log;

import com.example.themoviesworld.Api.Api;
import com.example.themoviesworld.BlockExecutor;
import com.example.themoviesworld.Models.Example;
import com.example.themoviesworld.Models.Result;
import com.example.themoviesworld.MovieApp;
import com.example.themoviesworld.dao.ResultDao;

import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.themoviesworld.DBConstants.TYPE1;
import static com.example.themoviesworld.DBConstants.TYPE2;
import static com.example.themoviesworld.DBConstants.TYPE3;
import static com.example.themoviesworld.DBConstants.imageUrl;

public class ApiCallsUtils {
    public static void callApi(ResultDao resultDao,String TYPE, Api api, BlockExecutor iBlockExecutor){
        Call iCallback;
        switch (TYPE)
        {
            case TYPE1:
                iCallback=api.getTopRatedMovies();
                break;
            case TYPE2:
                iCallback=api.getPopularMovies();
                break;
            case TYPE3:
                iCallback=api.getLatestMovies();
                break;
                default:
                    iCallback=null;
                    ToastUtils.showToast("Callback null");
        }

        implementApiResponse(TYPE,iCallback,iBlockExecutor,resultDao);

    }
    public static void implementApiResponse(String TYPE, Call iCallback, BlockExecutor iBlockExecutor,ResultDao resultDao) {


        iCallback.enqueue(new Callback<Example>() {
            @Override
            public void onResponse(Call<Example> call, Response<Example> response) {
                Example example = response.body();
                Log.i("Tag","Check"+example.getTotalResults());
                List<Result> results = example.getResults();
                Log.i("TAG", "onResponse: " + example.getTotalResults() + example + " " + results.size());

                resultDao.deleteByType(TYPE);

                Log.i("TAG", "onCreate: Inserting TopRateddata in database QUERY");

                for (Result i : results) {
                    i.setType(TYPE);
                    i.setLastTimeStamp(DateTimeUtils.getCurrentTime());
                    i.setPosterPath(imageUrl + i.getPosterPath());
                    resultDao.insert(i);

                }

                if (iBlockExecutor != null)
                    iBlockExecutor.executeThis();

            }

            @Override
            public void onFailure(Call call, Throwable t) {
                Log.i("TAG", "onFailure: " + t.getMessage());

            }
        });

    }
    public static Api createApiRequest() {
        Log.i("TAG", "Inside create Api Request function");
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        Retrofit retrofit = new Retrofit.Builder()
                .client(new OkHttpClient.Builder()
                        .addInterceptor(logging).build())
                .baseUrl(Api.baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Api api = retrofit.create(Api.class);
        return api;
    }
}
