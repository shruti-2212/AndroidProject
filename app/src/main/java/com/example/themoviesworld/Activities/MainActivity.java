package com.example.themoviesworld.Activities;

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

import androidx.appcompat.app.AppCompatActivity;

import com.example.themoviesworld.Models.User;
import com.example.themoviesworld.MovieApp;
import com.example.themoviesworld.PreferenceUtils;
import com.example.themoviesworld.R;
import com.example.themoviesworld.dao.UserDao;
import com.example.themoviesworld.utils.ActivityUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import static com.example.themoviesworld.DBConstants.TO_HRS;
import static java.lang.Math.abs;

public class MainActivity extends AppCompatActivity {
    private EditText userEmail;
    private EditText password;
    private RelativeLayout rr1;
    private RelativeLayout rr2;
    private RelativeLayout rr3;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());

    private UserDao userDao;
    private ProgressDialog progressDialog;
    private float loginTimeDiff, loginTimeDiffInHrs;

    private Handler handler = new Handler();
    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            Log.i("TAG", "run: Current Thread" + Thread.currentThread());
            // TODO 3 views are okay. for more similar views create arrays of views and then check visibility
            rr1.setVisibility(View.VISIBLE);
            rr2.setVisibility(View.VISIBLE);
            rr3.setVisibility(View.VISIBLE);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
<<<<<<< Updated upstream
        userDao = MovieApp.getUserDatabase().getUserDao();

        // Check if there are any existing users logged in within the defined time limit
        if (userDao.getUserCount() != 0 && PreferenceUtils.getId() != 0) {
            int id = PreferenceUtils.getId();
=======
        sharedPreferences = getApplicationContext().getSharedPreferences("Preferences", 0);

        userDao = MovieApp.getsUserDatabase().getUserDao();

        if (userDao.getUserCount() != 0 && PreferenceUtils.getId(getApplicationContext()) != 0) {
            int id = PreferenceUtils.getId(getApplicationContext());
>>>>>>> Stashed changes
            Log.i("Tag", "USer Id is" + id);

            String lastLoginTime = userDao.getUser(id).getUserLoginTime();

            // TODO Create a DateTimeUtils class that does all the operations related to date and time usage
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
                // TODO Add some logs, Toast or anything that explains this flow
            }
        } else {
            // TODO Add some logs, Toast or anything that explains this flow
            Log.d("TAG", "No logged in user found");
        }


<<<<<<< Updated upstream
=======

>>>>>>> Stashed changes
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

        Button button_login = findViewById(R.id.login_button);
        Button button_sign_up = findViewById(R.id.btn_signup);
        Button button_forgot_password = findViewById(R.id.btn_forgot_password);

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

                // Validates the entered user details
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

                                PreferenceUtils.saveId(user.getId());

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
                }

            }
        });

        button_forgot_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // FIXME Repeated code. Should go in a method
                ActivityUtils.launchActivityAndFinish(MainActivity.this, ChangePasswordActivity.class);
            }
        });

    }

    private boolean emptyValidation() {
        // FIXME Simplify the code wherever required
        return TextUtils.isEmpty(userEmail.getText().toString()) || TextUtils.isEmpty(password.getText().toString());
    }


}
