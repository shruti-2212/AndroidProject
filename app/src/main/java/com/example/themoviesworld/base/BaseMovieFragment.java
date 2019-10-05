package com.example.themoviesworld.base;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.themoviesworld.Adapters.RecyclerAdapter;
import com.example.themoviesworld.Models.Result;
import com.example.themoviesworld.MovieApp;
import com.example.themoviesworld.dao.ResultDao;

import java.util.List;

public abstract class BaseMovieFragment extends BaseFragment {

    private ResultDao resultDao;
    private OnFragmentInteractionListener mListener;
    private RecyclerAdapter recyclerAdapter = null;
    private RecyclerView recyclerView = null;

    protected abstract int getLayoutId();

    protected abstract String getType();

    protected abstract int getRecyclerViewId();

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        resultDao = MovieApp.getUserDatabase().getResultDao();
        recyclerAdapter = new RecyclerAdapter(getContext());
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(getLayoutId(), container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = view.findViewById(getRecyclerViewId());

        if (recyclerAdapter == null)
            recyclerAdapter = new RecyclerAdapter(getActivity());

        addNotifyRecyclerView(resultDao.getResult(getType()));
    }


    private void addNotifyRecyclerView(List<Result> iDataList) {
        recyclerAdapter.setMdata(iDataList);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(recyclerAdapter);
    }

}
