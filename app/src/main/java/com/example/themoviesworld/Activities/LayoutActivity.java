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
import com.example.themoviesworld.Fragments.LatestMovies;
import com.example.themoviesworld.Fragments.PopularMovies;
import com.example.themoviesworld.Fragments.TopRatedMovies;
import com.example.themoviesworld.Models.Example;
import com.example.themoviesworld.Models.Result;
import com.example.themoviesworld.MovieApp;
import com.example.themoviesworld.PreferenceUtils;
import com.example.themoviesworld.R;
import com.example.themoviesworld.dao.ResultDao;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

import java.text.SimpleDateFormat;
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

import static java.lang.Math.abs;

public class LayoutActivity extends AppCompatActivity implements PopularMovies.OnFragmentInteractionListener, LatestMovies.OnFragmentInteractionListener,
        TopRatedMovies.OnFragmentInteractionListener, NavigationView.OnNavigationItemSelectedListener, DBConstants {

    Toolbar toolbar;
    ViewPager viewPager;
    TabLayout tabLayout;
    TabItem popularMovies;
    TabItem latestMovies;
    TabItem topRatedMovies;
    PagerAdapter pagerAdapter;
    DrawerLayout drawer;
    ProgressBar progressBar;
    String imageUrl = "https://image.tmdb.org/t/p/w600_and_h900_bestv2/";

    TextView nav_heading;

    private ResultDao resultDao;

    SimpleDateFormat dateFormat;

    float hrsTRM, hrsPM, hrsUM;
    long timeDiffrerenceTRM, timeDiffrerencePM, timeDiffrerenceUM;

    Date lastTimeUM, lastTimePM, lastTimeTRM, currentTime;


    private void callApiTopRated(Api api, BlockExecutor iBlockExecutor) {
        Log.i("TAG", "Inside callAPITopRated");

        Call iCallback = api.getTopRatedMovies();
        implementApiResponse(TYPE1, iCallback, iBlockExecutor);
    }


    private void callApiPopularMovies(Api api, BlockExecutor iBlockExecutor) {
        Log.i("TAG", "Inside callAPIPopularMovies");
        Log.i("TAG", "onCreate: Popular movies");


        Call iCallback = api.getPopularMovies();
        implementApiResponse(TYPE2, iCallback, iBlockExecutor);
    }

    public void callApiUpcoming(Api api, BlockExecutor iBlockExecutor) {
        Log.i("TAG", "Inside callAPIUpcoming");


        Log.i("TAG", "In callApiUpcoming calling api");
        Call iCallback = api.getUpcomingMovies();
        implementApiResponse(TYPE3, iCallback, iBlockExecutor);
    }


    public Api createApiRequest() {
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

    private void implementApiResponse(String TYPE, Call iCallback, BlockExecutor iBlockExecutor) {

        iCallback.enqueue(new Callback<Example>() {
            @Override
            public void onResponse(Call<Example> call, Response<Example> response) {
                Example example = response.body();
                List<Result> results = example.getResults();
                Log.i("TAG", "onResponse: " + example.getTotalResults() + example + " " + results.size());

//
                resultDao.deleteByType(TYPE);
                Log.i("TAG", "onCreate: Inserting TopRateddata in database QUERY");
                Log.i("TAG", "onCreate: CurrentTimeStamp" + getDateTime());
                for (Result i : results) {
                    i.setType(TYPE);
                    i.setLastTimeStamp(getDateTime());
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

    private String getDateTime() {

        Date date = new Date();
        return dateFormat.format(date);
    }

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

        dateFormat = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss", Locale.getDefault());
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

        Api api = createApiRequest();

        Log.i("TAG", "Check Database Empty" + resultList);

        if (resultList == null || resultList.size() == 0) {
            viewPager.setVisibility(View.GONE);
            progressBar.setVisibility(View.VISIBLE);

            Log.i("TAG", "On Database Empty");

            // FIXME Start using some architectural pattern to model such implementation in a modular and flexible
            callApiUpcoming(api, new BlockExecutor() {
                @Override
                public void executeThis() {
                    callApiTopRated(api, new BlockExecutor() {
                        @Override
                        public void executeThis() {
                            callApiPopularMovies(api, new BlockExecutor() {
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
            currentTime = new Date();
            Log.i("TAG", "Calculating time" + currentTime);
            try {
                Log.i("TAG", "Calculating time" + currentTime);
                lastTimeTRM = dateFormat.parse(resultDao.getMaxTimeStamp(TYPE1));
                lastTimePM = dateFormat.parse(resultDao.getMaxTimeStamp(TYPE2));
                lastTimeUM = dateFormat.parse(resultDao.getMaxTimeStamp(TYPE3));
                Log.i("TAG", "Time TRM : " + lastTimeTRM.getTime() + "-" + currentTime.getTime());

                timeDiffrerenceTRM = abs(lastTimeTRM.getTime() - currentTime.getTime());
                timeDiffrerencePM = abs(lastTimeTRM.getTime() - currentTime.getTime());
                timeDiffrerenceUM = abs(lastTimeTRM.getTime() - currentTime.getTime());
                Log.i("TAG", "Time TRM : " + timeDiffrerenceTRM);

                hrsTRM = (timeDiffrerenceTRM / TO_HRS);
                hrsPM = (timeDiffrerencePM / TO_HRS);
                hrsUM = (timeDiffrerenceUM / TO_HRS);
                Log.i("TAG", "onCreate: " + " " + hrsTRM + " " + hrsPM + " " + hrsUM);
            } catch (Exception e) {

                Log.i("TAG", "onCreate: Inside Catch Exception Occured");
                e.printStackTrace();
            }

            if (hrsTRM >= 4.0) {
                callApiTopRated(api, new BlockExecutor() {
                    @Override
                    public void executeThis() {

                        if (hrsUM >= 4.0) {
                            callApiUpcoming(api, new BlockExecutor() {
                                @Override
                                public void executeThis() {
                                    if (hrsPM >= 4.0) {
                                        callApiPopularMovies(api, new BlockExecutor() {
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
                Intent intent = new Intent(this, MainActivity.class);
                intent.putExtra("From logout", true);
                startActivity(intent);
                finish();
                break;
        }

        drawer.closeDrawer(GravityCompat.START);

        return true;
    }
}

