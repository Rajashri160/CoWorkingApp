package com.example.coworkingapp.ui.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.coworkingapp.R;
import com.example.coworkingapp.RetrofitAPI;
import com.example.coworkingapp.com.example.coworkingapp.CalendarDateModel;
import com.example.coworkingapp.ui.adapter.CalendarAdapter;
import com.example.coworkingapp.ui.adapter.TimeSlotsAdapter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CalenderActivity extends AppCompatActivity {
    private SimpleDateFormat sdf = new SimpleDateFormat("MMMM yyyy", Locale.ENGLISH);
    private Calendar cal = Calendar.getInstance(Locale.ENGLISH);
    private Calendar currentDate = Calendar.getInstance(Locale.ENGLISH);
    private ArrayList<Date> dates = new ArrayList<>();
    private CalendarAdapter adapter4Dates;
    private TimeSlotsAdapter adapter4TimeSlots;
    private ArrayList<CalendarDateModel> calendarList2 = new ArrayList<>();
    private ArrayList<Slots> listSlots;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calender);
        getSupportActionBar().hide();
        getTimeSlots();

        setUpAdapter();
        setUpClickListener();
        setUpCalendar();
    }
    private void setUpClickListener() {
        findViewById(R.id.iv_calendar_next).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cal.add(Calendar.MONTH, 1);
                setUpCalendar();
            }
        });
        findViewById(R.id.iv_calendar_previous).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cal.add(Calendar.MONTH, -1);
                if (cal.equals(currentDate))
                    setUpCalendar();
                else
                    setUpCalendar();
            }
        });
    }

    private void setUpAdapter() {
        int spacingInPixels = getResources().getDimensionPixelSize(R.dimen.single_calendar_margin);
        //((RecyclerView)findViewById(R.id.recyclerView)).addItemDecoration(new HorizontalItemDecoration(spacingInPixels));
        SnapHelper snapHelper = new LinearSnapHelper();
        snapHelper.attachToRecyclerView(findViewById(R.id.recyclerView));
        adapter4Dates = new CalendarAdapter(new CalendarAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(CalendarDateModel calendarDateModel, int position) {
                for (int index = 0; index < calendarList2.size(); index++) {
                    CalendarDateModel calendarModel = calendarList2.get(index);
                    calendarModel.setSelected(index == position);
                }
                adapter4Dates.setList(calendarList2);
            }
        });

        ((RecyclerView)findViewById(R.id.recyclerView)).setAdapter(adapter4Dates);
    }

    private void setUpAdapter4TimeSlots() {
        adapter4TimeSlots = new TimeSlotsAdapter(listSlots);
        //((RecyclerView)findViewById(R.id.rvTimeSlots)).setLayoutManager(new LinearLayoutManager(this));
        ((RecyclerView)findViewById(R.id.rvTimeSlots)).setLayoutManager(new GridLayoutManager(this, 2));

        ((RecyclerView)findViewById(R.id.rvTimeSlots)).setAdapter(adapter4TimeSlots);
    }

    private void setUpCalendar() {
        ArrayList<CalendarDateModel> calendarList = new ArrayList<>();
        ((TextView)findViewById(R.id.tv_date_month)).setText(sdf.format(cal.getTime()));
        Calendar monthCalendar = (Calendar) cal.clone();
        int maxDaysInMonth = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        dates.clear();
        monthCalendar.set(Calendar.DAY_OF_MONTH, 1);
        while (dates.size() < maxDaysInMonth) {
            dates.add(monthCalendar.getTime());
            calendarList.add(new CalendarDateModel(monthCalendar.getTime()));
            monthCalendar.add(Calendar.DAY_OF_MONTH, 1);
        }
        calendarList2.clear();
        calendarList2.addAll(calendarList);
        adapter4Dates.setList(calendarList);
    }

    private void getTimeSlots() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://demo0413095.mockable.io/digitalflake/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RetrofitAPI retrofitAPI = retrofit.create(RetrofitAPI.class);
        Call<JSONResponse> call = retrofitAPI.getSlots();
        call.enqueue(new Callback<JSONResponse>() {
            @Override
            public void onResponse(Call<JSONResponse> call, Response<JSONResponse> response) {
                if(response.code() == 200){
                    if (response != null) {
                        JSONResponse jsonResponse = response.body();
                        listSlots = new ArrayList<>(Arrays.asList(jsonResponse.getSlots()));
                        setUpAdapter4TimeSlots();
                    }
                }
            }

            @Override
            public void onFailure(Call<JSONResponse> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }


    public class Slots {

        private String slot_name;
        private String slot_id;
        private String slot_active;

        public Slots(String slot_name, String slot_id, String slot_active) {
            this.slot_name = slot_name;
            this.slot_id = slot_id;
            this.slot_active = slot_active;
        }

        public String getSlotName() {
            return slot_name;
        }

        public void setSlotName(String slot_name) {
            this.slot_name = slot_name;
        }

        public String getSlotId() {
            return slot_id;
        }

        public void setSlotId(String slot_id) {
            this.slot_id = slot_id;
        }

        public String getActive() {
            return slot_active;
        }

        public void setActive(String slot_active) {
            this.slot_active = slot_active;
        }
    }
    public class JSONResponse {
        private Slots[] slots;

        public Slots[] getSlots() {
            return slots;
        }
    }
}