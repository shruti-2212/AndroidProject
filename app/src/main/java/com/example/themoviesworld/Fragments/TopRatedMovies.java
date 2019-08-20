package com.example.themoviesworld.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

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
import com.example.themoviesworld.dao.UserDao;

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


public class TopRatedMovies extends Fragment {
    private RecyclerView recyclerView;

    private List<Result> results, mdata;
    //    private UserDatabase userDatabase;
    private ResultDao resultDao;


    private OnFragmentInteractionListener mListener;

    public TopRatedMovies() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    /*public static TopRatedMovies newInstance(String param1, String param2) {
        TopRatedMovies fragment = new TopRatedMovies();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }*/

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //callApi();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_top_rated_movies, container, false);
        recyclerView = view.findViewById(R.id.recyclerView_top_rated_movies);
        resultDao= MovieApp.getsUserDatabase().getResultDao();
        Log.i("TAG", "onCreateView: TopRated"+resultDao.getResult(TYPE1).size());
        setupRecyclerView(resultDao.getResult(TYPE1));

        return view;
    }


    public void setupRecyclerView(List<Result> mdata) {
        Log.i("TAG", "setupRecyclerView:TopRated ");
        RecyclerAdapter recyclerAdapter = new RecyclerAdapter(getContext(), mdata);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(recyclerAdapter);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
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
}
