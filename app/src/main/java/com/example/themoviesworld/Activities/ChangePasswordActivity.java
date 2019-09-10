package com.example.themoviesworld.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.themoviesworld.MovieApp;
import com.example.themoviesworld.dao.UserDao;
import com.example.themoviesworld.Models.User;
import com.example.themoviesworld.R;

public class ChangePasswordActivity extends AppCompatActivity {
    TextView password,confirmPassword,usermail;
    Button btn_change_password;
//    private UserDatabase userDatabase;
    private UserDao userDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_forgot_password);
        password=findViewById(R.id.et_password_change_password);
        confirmPassword=findViewById(R.id.et_confirm_password_change_password);
        usermail=findViewById(R.id.et_usermail_change_password);
        btn_change_password=findViewById(R.id.button_change_password);

//        userDatabase = Room.databaseBuilder(this, UserDatabase.class, "mi-database.db")
//                .allowMainThreadQueries()
//                .build();

        userDao= MovieApp.getsUserDatabase().getUserDao();

        btn_change_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

        User user =userDao.getUserChangepassword(usermail.getText().toString());
        if(user == null)
        {
            Toast.makeText(ChangePasswordActivity.this,"User with this mail does not exist",Toast.LENGTH_LONG).show();
        }
        else if(emptyValidation()) {
            Toast.makeText(ChangePasswordActivity.this, "UserName and password fields cannot be empty", Toast.LENGTH_LONG).show();
        }
        else if(!(password.getText().toString()).equals(confirmPassword.getText().toString()))
        {
            Toast.makeText(ChangePasswordActivity.this,"Password and confirm Password field should match",Toast.LENGTH_LONG).show();
        }
        else
        {
            user.setPassword(password.getText().toString());
            userDao.update(user);
            Toast.makeText(ChangePasswordActivity.this,"Password changed successfully",Toast.LENGTH_SHORT).show();
            startActivity(new Intent(ChangePasswordActivity.this, MainActivity.class));
        }

        }
    });



    }
    private boolean emptyValidation() {
        if (TextUtils.isEmpty(usermail.getText().toString()) || TextUtils.isEmpty(password.getText().toString())) {
            return true;
        }else {
            return false;
        }
    }
}
