package com.example.themoviesworld.utils;

import android.widget.Toast;

import com.example.themoviesworld.MovieApp;

public class ToastUtils {

    public static void showToast(String iMessage) {
        Toast.makeText(MovieApp.getContext(), iMessage, Toast.LENGTH_SHORT).show();
    }

    public static void showToastLong(String iMessage) {
        if (iMessage != null)
            Toast.makeText(MovieApp.getContext(), iMessage, Toast.LENGTH_LONG).show();
    }

    // TODO Add method if custom toast is required

}
