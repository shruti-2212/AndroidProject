package com.example.themoviesworld.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
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
import com.example.themoviesworld.utils.PreferenceUtils;
import com.example.themoviesworld.dao.UserDao;
import com.example.themoviesworld.R;
import com.example.themoviesworld.Models.User;
import com.example.themoviesworld.utils.ActivityUtils;
import com.example.themoviesworld.utils.DateTimeUtils;
import com.example.themoviesworld.utils.ToastUtils;

import static com.example.themoviesworld.DBConstants.TO_HRS;
import static java.lang.Math.abs;

public class MainActivity extends AppCompatActivity {

    private EditText userEmail;
    private EditText password;
    private RelativeLayout rr1;
    private RelativeLayout rr2;
    private RelativeLayout rr3;

    private Button button_login;
    private Button button_sign_up;
    private Button button_forgot_password;

    private UserDao userDao;
    private ProgressDialog progressDialog;


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

        userDao = MovieApp.getUserDatabase().getUserDao();
        int id = PreferenceUtils.getId();

        //Initially check whether user database is empty or has logged in previously
        if (userDao.getUserCount() != 0 && id != 0) {

            Log.i("Tag", "USer Id is" + id);

            String lastLoginTimeString = userDao.getUser(id).getUserLoginTime();
            long lastLoginTime = DateTimeUtils.parseStringToDate(lastLoginTimeString).getTime();
            long currentTime = DateTimeUtils.getCurrentTime();


            long loginTimeDiff = DateTimeUtils.getTimeDifferenceinSeconds(lastLoginTime, currentTime);
            float loginTimeDiffInHrs = loginTimeDiff / TO_HRS;

            Log.i("Tag", "LastLogin Time: " + lastLoginTime);
            Log.i("Tag", "Login time diff:" + loginTimeDiff);
            Log.i("Tag", "Login time diff in hrs:" + loginTimeDiffInHrs);

            // Check if there are any existing users logged in within the defined time limit
            if (loginTimeDiffInHrs < 24.0) {

                ToastUtils.showToast("Logged in UserFound");
                Intent i = new Intent(this, LayoutActivity.class);
                i.putExtra("User Name", userDao.getUser(id).getFirst_name());
                startActivity(i);
                finish();
            } else {
                ToastUtils.showToast("User Login time expired.Needs to be logged in again");
            }
        } else {
            ToastUtils.showToast("No Logged in User found");
        }


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
                ActivityUtils.launchActivityAndFinish(MainActivity.this, SignUpActivity.class);
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

                                user.setUserLoginTime(DateTimeUtils.parseDateToString(DateTimeUtils.currentDateAndTime));
                                userDao.update(user);

                                Log.i("Tag", "Setting user login time" + user.getUserLoginTime());

                                PreferenceUtils.saveId(user.getId());
                                Intent i = new Intent();
                                i.putExtra("User Name", user.getFirst_name());
                                i.putExtra("ID", user.getId());
                                ActivityUtils.launchActivityWithData(i,MainActivity.this, LayoutActivity.class);
                            } else {
                                ToastUtils.showToast("User not registered");

                            }
                            progressDialog.dismiss();
                        }
                    }, 1000);

                }

            }
        });

        button_forgot_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ActivityUtils.launchActivityAndFinish(MainActivity.this, ChangePasswordActivity.class);
            }
        });


    }

    private boolean emptyValidation() {
        return TextUtils.isEmpty(userEmail.getText().toString()) || TextUtils.isEmpty(password.getText().toString());
    }
}
