
package com.example.coworkingapp.ui.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.coworkingapp.MainActivity;
import com.example.coworkingapp.R;
import com.example.coworkingapp.ui.AppUtilty;

public class FlashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flash_screen);
        int userId = AppUtilty.getUserId(getApplicationContext());
        if(userId != 0) {
            Intent intent = new Intent(FlashScreenActivity.this, UserLoginActivity.class);
            startActivity(intent);
        }else{
            Intent intent = new Intent(FlashScreenActivity.this, RegisterActivity.class);
            startActivity(intent);
        }
    }
}