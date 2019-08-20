package com.example.themoviesworld.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.themoviesworld.Adapters.RecyclerAdapter;
import com.example.themoviesworld.Api.Api;
import com.example.themoviesworld.Models.Example;
import com.example.themoviesworld.Models.Result;
import com.example.themoviesworld.MovieApp;
import com.example.themoviesworld.R;
import com.example.themoviesworld.UserDatabase;
import com.example.themoviesworld.dao.ResultDao;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.themoviesworld.DBConstants.TYPE1;
import static com.example.themoviesworld.DBConstants.TYPE2;


public class PopularMovies extends Fragment {
    private RecyclerView recyclerView;

    private List<Result> results,mdata;

//    private UserDatabase userDatabase;
    private ResultDao resultDao;

    private OnFragmentInteractionListener mListener;

    public PopularMovies() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //callApi();
    }
    // TODO: Rename and change types and number of parameters
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_popular_movies, container, false);
        recyclerView = view.findViewById(R.id.recyclerView_popular_movies);

        resultDao= MovieApp.getsUserDatabase().getResultDao();
        Log.i("TAG", "onCreateView: Popular"+resultDao.getResult(TYPE2).size());
        setupRecyclerView(resultDao.getResult(TYPE2));
        return view;
    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        resultDao= MovieApp.getsUserDatabase().getResultDao();
        Log.i("TAG", "onCreateView: Popular"+resultDao.getResult(TYPE2).size());
        setupRecyclerView(resultDao.getResult(TYPE2));
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
    public void setupRecyclerView(List<Result> mdata) {
        Log.i("TAG", "setupRecyclerView:Popular ");
        RecyclerAdapter recyclerAdapter = new RecyclerAdapter(getContext(),mdata);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(recyclerAdapter);
    }
}
