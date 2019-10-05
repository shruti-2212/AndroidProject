package com.example.themoviesworld.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.themoviesworld.Models.User;
import com.example.themoviesworld.MovieApp;
import com.example.themoviesworld.R;
import com.example.themoviesworld.dao.UserDao;
import com.example.themoviesworld.utils.ToastUtils;

public class ChangePasswordActivity extends AppCompatActivity {
    private TextView password;
    private TextView confirmPassword;
    private TextView userMail;
    private UserDao userDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_forgot_password);

        password = findViewById(R.id.et_password_change_password);
        confirmPassword = findViewById(R.id.et_confirm_password_change_password);
        userMail = findViewById(R.id.et_usermail_change_password);
        Button btn_change_password = findViewById(R.id.button_change_password);
        userDao = MovieApp.getUserDatabase().getUserDao();

        btn_change_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                User user = userDao.getUserChangepassword(userMail.getText().toString());

                // Validate before updating password
                if (user == null) {
                    ToastUtils.showToastLong("User with this mail does not exist");
                } else if (emptyValidation()) {
                    ToastUtils.showToastLong("UserName and password fields cannot be empty");
                } else if (!(password.getText().toString()).equals(confirmPassword.getText().toString())) {
                    ToastUtils.showToastLong("Password and confirm Password field should match");
                } else {
                    user.setPassword(password.getText().toString());
                    userDao.update(user);
                    ToastUtils.showToast("Password changed successfully");
                    startActivity(new Intent(ChangePasswordActivity.this, MainActivity.class));
                }

            }
        });

    }

    private boolean emptyValidation() {
        // Simplify it
        return TextUtils.isEmpty(userMail.getText().toString()) || TextUtils.isEmpty(password.getText().toString());
    }
}
