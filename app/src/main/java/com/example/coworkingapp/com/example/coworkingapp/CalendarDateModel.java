package com.example.coworkingapp.com.example.coworkingapp;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class CalendarDateModel{
    private Date data;
    public boolean isSelected;

    public CalendarDateModel(Date data, boolean isSelected) {
        this.data = data;
        this.isSelected = isSelected;
    }

    public CalendarDateModel(Date data) {
        this.data = data;
        this.isSelected = false;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public String getCalendarDay() {
        return new SimpleDateFormat("EE", Locale.getDefault()).format(data);
    }

    public String getCalendarDate() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(data);
        return String.valueOf(cal.get(Calendar.DAY_OF_MONTH));
    }
}




