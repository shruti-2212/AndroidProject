package com.example.themoviesworld.Activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.themoviesworld.Adapters.PageAdapter;
import com.example.themoviesworld.Api.Api;
import com.example.themoviesworld.BlockExecutor;
import com.example.themoviesworld.DBConstants;
import com.example.themoviesworld.utils.DBUtils;
import com.example.themoviesworld.Models.Result;
import com.example.themoviesworld.MovieApp;
import com.example.themoviesworld.utils.ActivityUtils;
import com.example.themoviesworld.utils.ApiCallsUtils;
import com.example.themoviesworld.utils.DateTimeUtils;
import com.example.themoviesworld.utils.PreferenceUtils;
import com.example.themoviesworld.R;
import com.example.themoviesworld.base.OnFragmentInteractionListener;
import com.example.themoviesworld.dao.ResultDao;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

import java.util.List;

import retrofit2.Call;

import static java.lang.Math.abs;

//<<<<<<< HEAD
public class LayoutActivity extends AppCompatActivity implements OnFragmentInteractionListener,
//        PopularMovies.OnFragmentInteractionListener, LatestMovies.OnFragmentInteractionListener, TopRatedMovies.OnFragmentInteractionListener,
        NavigationView.OnNavigationItemSelectedListener, DBConstants {
//=======
//public class LayoutActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, DBConstants {
//>>>>>>> 4a3405fb718de4e3a42cfe333ef2bf47434d7763

    private Toolbar toolbar;
    private ViewPager viewPager;
    private TabLayout tabLayout;

    private TabItem popularMovies;
    private TabItem latestMovies;
    private TabItem topRatedMovies;

    private PagerAdapter pagerAdapter;
    private DrawerLayout drawer;
    private ProgressBar progressBar;

    private String imageUrl = "https://image.tmdb.org/t/p/w600_and_h900_bestv2/";

    private TextView nav_heading;

    private ResultDao resultDao;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout);

        resultDao = MovieApp.getUserDatabase().getResultDao();

        toolbar = findViewById(R.id.toolbar);
        viewPager = findViewById(R.id.view_pager);
        tabLayout = findViewById(R.id.tab_layout);
        popularMovies = findViewById(R.id.popular_movies);
        topRatedMovies = findViewById(R.id.top_rated_movies);
        latestMovies = findViewById(R.id.latest_movies);
        drawer = findViewById(R.id.drawer_layout);
        progressBar = findViewById(R.id.progress_bar);


        NavigationView nview = findViewById(R.id.nav_drawer);
        setSupportActionBar(toolbar);

        Intent i = getIntent();
        String username = i.getStringExtra("User Name");


        View headerView = nview.getHeaderView(0);
        nav_heading = headerView.findViewById(R.id.nav_heading);
        nav_heading.setText("Hello" + " " + username);

        nview.setNavigationItemSelectedListener(this);


        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

        List<Result> resultList = resultDao.getResult();

        Api api = ApiCallsUtils.createApiRequest();

        Log.i("TAG", "Check Database Empty" + resultList);

        if (resultList == null || resultList.size() == 0) {
            viewPager.setVisibility(View.GONE);
            progressBar.setVisibility(View.VISIBLE);

            Log.i("TAG", "On Database Empty");

            // FIXME Start using some architectural pattern to model such implementation in a modular and flexible
            callApi(TYPE3, api, new BlockExecutor() {
                @Override
                public void executeThis() {
                    callApi(TYPE1, api, new BlockExecutor() {
                        @Override
                        public void executeThis() {
                            callApi(TYPE2, api, new BlockExecutor() {
                                @Override
                                public void executeThis() {
                                    updateUI();
                                }
                            });
                        }
                    });
                }
            });
        } else {
            updateUI();


            // FIXME Move this code to a new DateTime Utils class

            Log.i("TAG", "Calculating time" + " " + DateTimeUtils.currentDateAndTime + " " + DateTimeUtils.getCurrentTime());




            if (DBUtils.shouldRefresh(TYPE1)) {
                callApi(TYPE1, api, new BlockExecutor() {
                    @Override
                    public void executeThis() {
                        if (DBUtils.shouldRefresh(TYPE3)) {
                            callApi(TYPE3, api, new BlockExecutor() {
                                @Override
                                public void executeThis() {
                                    if (DBUtils.shouldRefresh(TYPE2)) {
                                        callApi(TYPE2, api, new BlockExecutor() {
                                            @Override
                                            public void executeThis() {
                                                // Update UI if required
                                                updateUI();
                                            }
                                        });
                                    }
                                }
                            });
                        }

                    }
                });
            }
        }

    }

    private void callApi(String TYPE, Api api, BlockExecutor iBlockExecutor) {
        Call iCallback;
        switch (TYPE) {
            case TYPE1:
                iCallback = api.getTopRatedMovies();
                break;
            case TYPE2:
                iCallback = api.getPopularMovies();
                break;
            case TYPE3:
                iCallback = api.getUpcomingMovies();
                break;

            default:
                iCallback = null;
        }
        ApiCallsUtils.implementApiResponse(TYPE, iCallback, iBlockExecutor, resultDao);
    }


    private void updateUI() {

        Log.i("TAG", "onCreate:Layout Activity setting view pager ");

        pagerAdapter = new PageAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setOffscreenPageLimit(1);
        viewPager.setAdapter(pagerAdapter);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {

            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                viewPager.setCurrentItem(tab.getPosition(), false);
                Log.i("TAG", "" + tab.getPosition());

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {


            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                Log.i("TAG", "onTabReselected: ");

            }
        });

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        // Update the visibility
        viewPager.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.nav_movies:
                startActivity(getIntent());
                break;
            case R.id.Logout:
                PreferenceUtils.saveId(0);

                // TODO Add one more method in com.example.themoviesworld.utils.ActivityUtils to support this type of intent creation
                Intent intent = new Intent();
                intent.putExtra("From logout", true);
                ActivityUtils.launchActivityWithData(intent, this, MainActivity.class);
                break;
        }

        drawer.closeDrawer(GravityCompat.START);

        return true;
    }
}

