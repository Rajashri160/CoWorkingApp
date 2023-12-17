package com.example.coworkingapp.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.coworkingapp.R;
import com.example.coworkingapp.com.example.coworkingapp.CalendarDateModel;

import java.util.ArrayList;

public class CalendarAdapter extends RecyclerView.Adapter<CalendarAdapter.MyViewHolder> {
    private ArrayList<CalendarDateModel> list = new ArrayList<>();
    private final OnItemClickListener listener;
    ArrayList<String> listSlots = new ArrayList<>();
    public interface OnItemClickListener {
        void onItemClick(CalendarDateModel calendarDateModel, int position);
    }

    public CalendarAdapter(ArrayList<String> listSlots) {
        this.listSlots = listSlots;
        listener = null;
    }

    public CalendarAdapter(OnItemClickListener listener) {
        this.listener = listener;
    }
    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView calendarDay;
        private TextView calendarDate;
        private CardView cardView;

        public MyViewHolder(View view) {
            super(view);
            calendarDay = view.findViewById(R.id.tv_calendar_day);
            calendarDate = view.findViewById(R.id.tv_calendar_date);
            cardView = view.findViewById(R.id.card_calendar);
        }



        public void bind(final CalendarDateModel calendarDateModel, final int position) {
            if (calendarDateModel.isSelected) {
                calendarDay.setTextColor(ContextCompat.getColor(itemView.getContext(), R.color.black));
                calendarDate.setTextColor(ContextCompat.getColor(itemView.getContext(), R.color.black));
                cardView.setCardBackgroundColor(ContextCompat.getColor(itemView.getContext(), R.color.white));
            } else {
                calendarDay.setTextColor(ContextCompat.getColor(itemView.getContext(), R.color.black));
                calendarDate.setTextColor(ContextCompat.getColor(itemView.getContext(), R.color.black));
                cardView.setCardBackgroundColor(ContextCompat.getColor(itemView.getContext(), R.color.white));
            }
            calendarDay.setText(calendarDateModel.getCalendarDay());
            calendarDate.setText(calendarDateModel.getCalendarDate());

            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(calendarDateModel, position);
                }
            });
        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_calendar_date, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        CalendarDateModel calendarDateModel = list.get(position);
        holder.bind(calendarDateModel, position);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void setList(ArrayList<CalendarDateModel> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public void setSlotList(ArrayList<String> list) {
        this.listSlots = list;
        notifyDataSetChanged();
    }
}

