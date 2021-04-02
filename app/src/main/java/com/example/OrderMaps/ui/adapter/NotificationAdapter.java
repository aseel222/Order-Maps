package com.example.OrderMaps.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.OrderMaps.model.apimodel.DatumNotification;
import com.example.splashactivity.R;

import java.util.List;

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.ViewHolder> {
    List<DatumNotification> data;
    Context context;




    public NotificationAdapter(List<DatumNotification> data,Context context) {
        this.data=data;
        this.context=context;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.notification_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {

        final DatumNotification model=data.get(position);

        holder.ordernum.setText(model.getTitle());
        holder.orderdetails.setText(model.getBody());

    }
    public void setlist(List<DatumNotification> data) {


        this.data = data;
        notifyDataSetChanged();


    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView ordernum,orderdetails;
        public ViewHolder(@NonNull final View itemView) {
            super(itemView);
            ordernum=itemView.findViewById(R.id.newordertitle);
            orderdetails=itemView.findViewById(R.id.neworderdetailstxt);



        }
    }

}
