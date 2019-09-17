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
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.themoviesworld.Models.User;
import com.example.themoviesworld.MovieApp;
import com.example.themoviesworld.R;
import com.example.themoviesworld.dao.UserDao;

public class SignUpActivity extends AppCompatActivity {

    EditText firstName, LastName, Email, password, confirmPassword;
    Button signup;
    //    private UserDatabase userDatabase;
    private UserDao userDao;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Registering...");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setProgress(0);

//        userDatabase = Room.databaseBuilder(this, UserDatabase.class, "mi-database.db")
//                .allowMainThreadQueries()
//                .build();

        userDao = MovieApp.getUserDatabase().getUserDao();

        firstName = findViewById(R.id.et_firstname_signup);
        LastName = findViewById(R.id.et_lastname_signup);
        password = findViewById(R.id.et_password_signup);
        confirmPassword = findViewById(R.id.et_confirm_password_signup);
        Email = findViewById(R.id.et_usermail_signup);

        signup = findViewById(R.id.signup_button_signup);

        signup.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Log.i("TAG", "onClick: ");
                if (!(password.getText().toString()).equals(confirmPassword.getText().toString())) {
                    Toast.makeText(SignUpActivity.this, "Password and confrim Password field shold match", Toast.LENGTH_LONG).show();
                } else if (isEmpty()) {
                    Toast.makeText(SignUpActivity.this, "Fields cannot be left empty", Toast.LENGTH_LONG).show();
                } else {
                    progressDialog.show();
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            User user = new User(firstName.getText().toString(), LastName.getText().toString(),
                                    Email.getText().toString(), password.getText().toString());
                            userDao.insert(user);
                            progressDialog.dismiss();
                            Intent i = new Intent(SignUpActivity.this, MainActivity.class);
                            i.putExtra("From_Signup", true);
                            startActivity(i);
                            finish();
                        }
                    }, 1000);

                }

            }
        });
    }

    private boolean isEmpty() {
        if (TextUtils.isEmpty(Email.getText().toString()) ||
                TextUtils.isEmpty(password.getText().toString()) ||
                TextUtils.isEmpty(firstName.getText().toString()) ||
                TextUtils.isEmpty(LastName.getText().toString())
        ) {
            return true;
        } else {
            return false;
        }
    }

}
