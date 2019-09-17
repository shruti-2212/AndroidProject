package com.example.themoviesworld.utils;

import android.app.Activity;
import android.content.Intent;

public class ActivityUtils {

    public static void launchActivityAndFinish(Activity iContext, Class iClass) {
        Intent intent = new Intent(iContext, iClass);
        iContext.startActivity(intent);
        iContext.finish();
    }


}
