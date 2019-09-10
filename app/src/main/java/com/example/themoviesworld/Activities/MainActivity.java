package com.example.themoviesworld.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.themoviesworld.MovieApp;
import com.example.themoviesworld.PreferenceUtils;
import com.example.themoviesworld.dao.UserDao;
import com.example.themoviesworld.R;
import com.example.themoviesworld.Models.User;
import com.example.themoviesworld.UserDatabase;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import static com.example.themoviesworld.DBConstants.TO_HRS;
import static java.lang.Math.abs;

public class MainActivity extends AppCompatActivity {
    SharedPreferences sharedPreferences;


    Button button_login, button_sign_up, button_forgot_password;
    EditText userEmail, password;
    RelativeLayout rr1, rr2, rr3;
    SimpleDateFormat dateFormat = new SimpleDateFormat(
            "yyyy-MM-dd HH:mm:ss", Locale.getDefault());


    //    private UserDatabase database;
    private UserDao userDao;
    private ProgressDialog progressDialog;
    float loginTimeDiff, loginTimeDiffInHrs;

    Handler handler = new Handler();
    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            Log.i("TAG", "run: Current Thread" + Thread.currentThread());
            rr1.setVisibility(View.VISIBLE);
            rr2.setVisibility(View.VISIBLE);
            rr3.setVisibility(View.VISIBLE);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedPreferences = getApplicationContext().getSharedPreferences("Preferences", 0);

        userDao = MovieApp.getsUserDatabase().getUserDao();
/*
        if(userDao.getUserCount()!=0&&sharedPreferences!=null
                &&(getIntent().getBooleanExtra("From_Signup",false))==false
                   &&(getIntent().getBooleanExtra("From logout",false))==false) {*/



        /* String Login = sharedPreferences.getString("login", null);*/
        //Log.i("TAG", "onCreate: shared preference" + id);
        if (userDao.getUserCount() != 0 && PreferenceUtils.getId(getApplicationContext()) != 0) {
            int id = PreferenceUtils.getId(getApplicationContext());
            Log.i("Tag", "USer Id is" + id);
            String lastLoginTime = userDao.getUser(id).getUserLoginTime();
            Date currentTime = new Date();
            try {
                loginTimeDiff = (abs((dateFormat.parse(lastLoginTime)).getTime() - currentTime.getTime()));
                loginTimeDiffInHrs = loginTimeDiff / TO_HRS;
            } catch (Exception e) {
                e.printStackTrace();
            }
            Log.i("Tag", "LastLogin Time: " + lastLoginTime);
            Log.i("Tag", "Current Time:" + currentTime);
            Log.i("Tag", "Login time diff:" + loginTimeDiff);
            Log.i("Tag", "Login time diff in hrs:" + loginTimeDiffInHrs);

            if (loginTimeDiffInHrs < 24.0) {

                Intent i = new Intent(this, LayoutActivity.class);
                i.putExtra("User Name", userDao.getUser(id).getFirst_name());
                startActivity(i);
                finish();
            } else {

            }
        }


        /*else if(userDao.getUserCount()==0||loginTimeDiffInHrs>=24.0
                || sharedPreferences==null
                || (getIntent().getBooleanExtra("From_Signup",false))==true
                ||(getIntent().getBooleanExtra("From logout",false))==true) {}*/
        setContentView(R.layout.activity_main);


        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Check User...");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setProgress(0);


        rr1 = findViewById(R.id.rr_layout);
        rr2 = findViewById(R.id.relative_layout);
        rr3 = findViewById(R.id.rr_layout3);

        userEmail = findViewById(R.id.et_username);
        password = findViewById(R.id.et_password);

        Log.i("TAG", "onCreate: Current Thread" + Thread.currentThread());
        handler.postDelayed(runnable, 2000);

        button_login = findViewById(R.id.login_button);
        button_sign_up = findViewById(R.id.btn_signup);
        button_forgot_password = findViewById(R.id.btn_forgot_password);

        button_sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SignUpActivity.class);
                startActivity(intent);
                finish();
            }
        });
        button_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final User user = userDao.getuser(userEmail.getText().toString(), password.getText().toString());
                if (emptyValidation()) {
                    Toast.makeText(MainActivity.this, "UserName and password fields cannot be empty", Toast.LENGTH_LONG).show();
                } else {
                    progressDialog.show();
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            if (user != null) {

                                user.setUserLoginTime(dateFormat.format(new Date()));
                                userDao.update(user);
                                Log.i("Tag", "Setting user login time" + user.getUserLoginTime());

                                    /*SharedPreferences.Editor editor = sharedPreferences.edit();
                                    editor.putString("login", user.getEmail());
                                    editor.putInt("Id",user.getId());
                                    editor.commit();*/
                                PreferenceUtils.saveId(user.getId(), getApplicationContext());
                                Intent i = new Intent(MainActivity.this, LayoutActivity.class);
                                i.putExtra("User Name", user.getFirst_name());
                                i.putExtra("ID", user.getId());
                                startActivity(i);
                                finish();
                            } else {
                                Toast.makeText(MainActivity.this, "User not registered", Toast.LENGTH_LONG).show();

                            }
                            progressDialog.dismiss();
                        }
                    }, 1000);
                    //Intent intent = new Intent(MainActivity.this, LayoutActivity.class);
                    //startActivity(intent);
                }

            }
        });

        button_forgot_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ChangePasswordActivity.class);
                startActivity(intent);
                finish();
            }
        });


    }

    private boolean emptyValidation() {
        if (TextUtils.isEmpty(userEmail.getText().toString()) || TextUtils.isEmpty(password.getText().toString())) {
            return true;
        } else {
            return false;
        }
    }
}
