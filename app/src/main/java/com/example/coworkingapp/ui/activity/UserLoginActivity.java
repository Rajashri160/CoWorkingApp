package com.example.coworkingapp.ui.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.coworkingapp.DataModal;
import com.example.coworkingapp.R;
import com.example.coworkingapp.RetrofitAPI;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UserLoginActivity extends AppCompatActivity {
    String email;
    String password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_login);
        getSupportActionBar().hide();
        EditText editTextEmail = findViewById(R.id.editTextMobileOrEmail);
        EditText editTextPassword = findViewById(R.id.editPassword);

        findViewById(R.id.btnLoginAccount).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                email = editTextEmail.getText().toString();
                password = editTextPassword.getText().toString();

                if(!email.equals("") && !password.equals("")) {
                    userAuthentication(email, password);
                }else{
                    Toast.makeText(UserLoginActivity.this, "Please enter login details.", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void userAuthentication(String email, String password) {
        // on below line we are creating a retrofit
        // builder and passing our base url
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://demo0413095.mockable.io/digitalflake/api/")
                // as we are sending data in json format so
                // we have to add Gson converter factory
                .addConverterFactory(GsonConverterFactory.create())
                // at last we are building our retrofit builder.
                .build();
        // below line is to create an instance for our retrofit api class.
        RetrofitAPI retrofitAPI = retrofit.create(RetrofitAPI.class);

        // passing data from our text fields to our modal class.
        DataModal modal = new DataModal(email, password);

        // calling a method to create a post and passing our modal class.
        Call<DataModal> call = retrofitAPI.userAuthentication(modal);

        // on below line we are executing our method.
        call.enqueue(new Callback<DataModal>() {
            @Override
            public void onResponse(Call<DataModal> call, Response<DataModal> response) {
                // this method is called when we get response from our api.

                if(response.code() == 200){
                    Toast.makeText(UserLoginActivity.this, "User logged in Successfully", Toast.LENGTH_SHORT).show();
                    response.body();
                    Intent intent = new Intent(UserLoginActivity.this, DashboardActivity.class);
                    startActivity(intent);
                }else   {
                    Toast.makeText(UserLoginActivity.this, "Error", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<DataModal> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }
}