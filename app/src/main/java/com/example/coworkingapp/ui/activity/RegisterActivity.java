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
import com.example.coworkingapp.ui.AppUtilty;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RegisterActivity extends AppCompatActivity {
    String name;
    String email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        getSupportActionBar().hide();

        EditText editTextName = findViewById(R.id.editTextFullName);
        EditText editTextEmail = findViewById(R.id.editTextEmailId);

        findViewById(R.id.btnCreateAccount).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name = editTextName.getText().toString();
                email = editTextEmail.getText().toString();
                if(!name.equals("") && !email.equals("")) {
                    String Expn =
                            "^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]{1}|[\\w-]{2,}))@"
                                    +"((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                                    +"[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\."
                                    +"([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                                    +"[0-9]{1,2}|25[0-5]|2[0-4][0-9])){1}|"
                                    +"([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$";

                    if (email.matches(Expn) && email.length() > 0)
                    {
                        userRegister(name, email);
                    }
                    else
                    {
                        Toast.makeText(RegisterActivity.this, "invalid email", Toast.LENGTH_LONG).show();
                    }
                }else{
                    Toast.makeText(RegisterActivity.this, "Enter email and name", Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    private void userRegister(String name, String email) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://demo0413095.mockable.io/digitalflake/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RetrofitAPI retrofitAPI = retrofit.create(RetrofitAPI.class);
        DataModal modal = new DataModal(name, email);
        Call<DataModal> call = retrofitAPI.userRegister(modal);
        call.enqueue(new Callback<DataModal>() {
            @Override
            public void onResponse(Call<DataModal> call, Response<DataModal> response) {
                if(response.code() == 200){
                    Toast.makeText(RegisterActivity.this, "Registered Successfully", Toast.LENGTH_SHORT).show();
                    response.body();
                    int userId =response.body().getUserId();
                    AppUtilty.setUserId(getApplicationContext(), userId);
                    Intent intent = new Intent(RegisterActivity.this, DashboardActivity.class);
                    startActivity(intent);
                }else   {
                    Toast.makeText(RegisterActivity.this, "Error", Toast.LENGTH_SHORT).show();
                }

            }
            @Override
            public void onFailure(Call<DataModal> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }
}