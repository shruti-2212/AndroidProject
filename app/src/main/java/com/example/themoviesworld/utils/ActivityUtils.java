package com.example.themoviesworld.utils;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;

import androidx.core.view.KeyEventDispatcher;

public class ActivityUtils {

    public static void launchActivityAndFinish(Activity iContext, Class iClass) {
        Intent intent = new Intent(iContext, iClass);

        iContext.startActivity(intent);
        iContext.finish();
    }
    public static void launchActivityWithData(Intent i, Activity iContext, Class iClass){
        ComponentName componentName= new ComponentName(iContext,iClass);
        i.setComponent(componentName);
        iContext.startActivity(i);
        iContext.finish();
    }


}
