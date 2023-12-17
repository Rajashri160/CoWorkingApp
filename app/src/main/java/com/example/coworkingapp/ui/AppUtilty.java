package com.example.coworkingapp.ui;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.coworkingapp.R;

import java.lang.reflect.Constructor;

public class AppUtilty {

    public static void setUserId(Context context, int userId){
        SharedPreferences sharedPref = context.getSharedPreferences("CoWorking", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt("UserId", userId);
        editor.apply();
    }

    public static int getUserId(Context context){
        SharedPreferences sharedPref = context.getSharedPreferences("CoWorking",Context.MODE_PRIVATE);
        int userId = sharedPref.getInt("UserId", 0);
        return userId;
    }
}
