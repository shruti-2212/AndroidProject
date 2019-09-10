package com.example.themoviesworld.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.themoviesworld.R;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;

public class MovieDetailActivity extends AppCompatActivity {
    CollapsingToolbarLayout collapsingToolbarLayout;
    String title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_movie_detail);
        TextView tv_name = findViewById(R.id.aa_movie_name);
        TextView tv_studio = findViewById(R.id.aa_studio);
        TextView tv_categorie = findViewById(R.id.aa_category);
        TextView tv_description = findViewById(R.id.aa_description);
        TextView tv_rating = findViewById(R.id.aa_rating);
        TextView display_releaseDate = findViewById(R.id.display_release_date);
        ImageView img = findViewById(R.id.aa_thumbnail);
        collapsingToolbarLayout = findViewById(R.id.collapsingtoolbar_id);


        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("Bundle");
        title = bundle.getString("Title");
        String img_url = bundle.getString("ImagePath");
        String description = bundle.getString("Description");
        String release_date = bundle.getString("Release Date");
        Log.i("TAG", "onCreate: " + title);
        tv_name.setText(title);
        tv_description.setText(description);
        tv_categorie.setText(release_date);
        display_releaseDate.setText(release_date);
        initCollapsingToolbar();

        //collapsingToolbarLayout.setTitle(title);
        RequestOptions requestOptions = new RequestOptions().fitCenter().placeholder(R.drawable.drawable_loading_shape).error(R.drawable.drawable_loading_shape);
        Glide.with(this)
                .load(img_url).apply(requestOptions).into(img);
    }

    private void initCollapsingToolbar() {

        collapsingToolbarLayout.setTitle(" ");
        AppBarLayout appBarLayout = findViewById(R.id.appbar_layout);
        appBarLayout.setExpanded(true);

        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            boolean isShow = false;
            int scrollRange = -1;

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int i) {
                if (scrollRange == -1)
                    scrollRange = appBarLayout.getTotalScrollRange();
                if (scrollRange + i == 0) {
                    collapsingToolbarLayout.setTitle(title);
                    isShow = true;
                } else if (isShow) {
                    collapsingToolbarLayout.setTitle(" ");
                    isShow = false;
                }
            }
        });


    }
}
