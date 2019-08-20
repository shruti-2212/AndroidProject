package com.example.themoviesworld.Adapters;

import android.util.Log;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.themoviesworld.Fragments.LatestMovies;
import com.example.themoviesworld.Fragments.PopularMovies;
import com.example.themoviesworld.Fragments.TopRatedMovies;

public class PageAdapter extends FragmentPagerAdapter {
    private int numberOfTabs;

    public PageAdapter(FragmentManager fm,int numberOfTabs) {
        super(fm);
        this.numberOfTabs=numberOfTabs;
        Log.i("TAG", "Inside PageAdapter: ");
    }


    @Override
    public Fragment getItem(int position) {
        switch (position)
        {
            case 0: {
                Log.i("TAG", "getItem: Item0");
                return new LatestMovies();
            }
            case 1: {
                Log.i("TAG", "getItem: Item1");
                return new PopularMovies();
            }
            case 2: {
                Log.i("TAG", "getItem: Item2");
                return new TopRatedMovies();
            }
        }
        return null;
    }

    @Override
    public int getCount() {
        return numberOfTabs;
    }
}
