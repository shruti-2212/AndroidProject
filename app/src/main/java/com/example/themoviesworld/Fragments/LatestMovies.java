package com.example.themoviesworld.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.themoviesworld.Adapters.RecyclerAdapter;
import com.example.themoviesworld.Models.Result;
import com.example.themoviesworld.MovieApp;
import com.example.themoviesworld.R;
import com.example.themoviesworld.dao.ResultDao;

import java.util.List;

import static com.example.themoviesworld.DBConstants.TYPE3;


public class LatestMovies extends Fragment {
    private RecyclerView recyclerView;
    //    private UserDatabase userDatabase;
    private ResultDao resultDao;

    // private OnFragmentInteractionListener mListener;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("TAG", "onCreate:LatestMovies ");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.i("TAG", "onCreateView:Upcoming ");

        View view = inflater.inflate(R.layout.fragment_latest_movies, container, false);
        recyclerView = view.findViewById(R.id.recyclerView_latest_movies);
        resultDao = MovieApp.getUserDatabase().getResultDao();
        Log.i("TAG", "onCreateView: Upcoming" + resultDao.getResult(TYPE3).size());
        setupRecyclerView(resultDao.getResult(TYPE3));
        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
  /*  public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }*/

    /*@Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }*/
    /*@Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }*/


    public void setupRecyclerView(List<Result> mdata) {
        Log.i("TAG", "setupRecyclerView:Upcoming ");
        RecyclerAdapter recyclerAdapter = new RecyclerAdapter(getContext(), mdata);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(recyclerAdapter);
    }


   /* public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);

    }*/
}
