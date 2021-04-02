package com.example.OrderMaps.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.OrderMaps.model.StatisticsModel;
import com.example.OrderMaps.model.apimodel.DatumNotification;
import com.example.OrderMaps.model.apimodel.StatisticsDatum;
import com.example.splashactivity.R;

import java.util.ArrayList;
import java.util.List;

public class StatisticsAdapter extends RecyclerView.Adapter<StatisticsAdapter.ViewHolder> {
    List<StatisticsDatum> data;
    Context context;


    public StatisticsAdapter(List<StatisticsDatum> data,Context context) {
        this.data = data;
        this.context=context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.statistcs_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {

        final StatisticsDatum model=data.get(position);

        holder.companyname.setText(model.getCompany().getName()+" "+"اجمالي ");
        holder.statisticsmoney.setText(model.getAmount()+" "+"ريال" );

    }
    public void setlist(List<StatisticsDatum> data) {


        this.data = data;
        notifyDataSetChanged();


    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView companyname,statisticsmoney;

        public ViewHolder(@NonNull final View itemView) {
            super(itemView);
            companyname=itemView.findViewById(R.id.totalcompany);
            statisticsmoney=itemView.findViewById(R.id.statisticsmoney);



        }
    }

}
