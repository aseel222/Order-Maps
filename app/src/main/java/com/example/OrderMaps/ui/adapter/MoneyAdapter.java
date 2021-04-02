package com.example.OrderMaps.ui.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.OrderMaps.model.MoneyModel;
import com.example.OrderMaps.model.apimodel.BillDatum;
import com.example.OrderMaps.model.apimodel.Datum;
import com.example.OrderMaps.model.apimodel.OrderBill;
import com.example.splashactivity.R;

import java.util.ArrayList;
import java.util.List;

public class MoneyAdapter extends RecyclerView.Adapter<MoneyAdapter.ViewHolder> {
    List<BillDatum> data;
    Context context;




    public MoneyAdapter(List<BillDatum> data, Context context) {
        this.data = data;
        this.context=context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.money_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {

        final BillDatum model=data.get(position);
        OrderBill order = model.getOrder();
        if (order != null){
            holder.ordernum.setText(model.getOrder().getId()+"");
            holder.moneytxt.setText(model.getOrder().getPrice());
            holder.orderdetails.setText(model.getOrder().getAddress());
        }

        }



    public void setlist(List<BillDatum> data) {
        this.data.clear();
        this.data.addAll(data);
        notifyDataSetChanged();


    }
    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView ordernum,orderdetails,moneytxt;

        public ViewHolder(@NonNull final View itemView) {
            super(itemView);
            ordernum=itemView.findViewById(R.id.ordernum_txt);
            orderdetails=itemView.findViewById(R.id.orderdetailsaddress);
            moneytxt=itemView.findViewById(R.id.moneytxt);



        }
    }

}
