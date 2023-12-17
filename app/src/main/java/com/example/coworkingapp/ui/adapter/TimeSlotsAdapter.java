package com.example.coworkingapp.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.coworkingapp.R;
import com.example.coworkingapp.ui.activity.CalenderActivity;

import java.util.ArrayList;

public class TimeSlotsAdapter extends RecyclerView.Adapter<TimeSlotsAdapter.ViewHolder> {
    ArrayList<CalenderActivity.Slots> listSlots = new ArrayList<>();

    // RecyclerView recyclerView;
    public TimeSlotsAdapter(ArrayList<CalenderActivity.Slots> listSlots) {
        this.listSlots = listSlots;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.row_time_slots, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final CalenderActivity.Slots myListData = listSlots.get(position);
        holder.tv_time_slots.setText(listSlots.get(position).getSlotName());
        holder.tv_time_slots.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(view.getContext(),"click on item: "+myListData.getDescription(),Toast.LENGTH_LONG).show();
            }
        });
    }


    @Override
    public int getItemCount() {
        return listSlots.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tv_time_slots;
        public ViewHolder(View itemView) {
            super(itemView);
            this.tv_time_slots = (TextView) itemView.findViewById(R.id.tv_time_slots);
        }
    }
}


