package com.example.OrderMaps.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;


import com.example.OrderMaps.model.apimodel.Datum;
import com.example.splashactivity.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.ViewHolder> {
List<Datum> data;

    private Context context;


    public void setOnbuttonclicklistner(OnItemClickListner onbuttonclicklistner) {
        this.onbuttonclicklistner = onbuttonclicklistner;
    }
    OnItemClickListner onbuttonclicklistner;



    public OrderAdapter(List<Datum> data,Context context) {
        this.data = data;
        this.context=context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.order_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {

        final Datum model=data.get(position);
            holder.ordernum.setText("طلب"+" "+model.getId());

            holder.orderdetails.setText(model.getAddress());
            holder.orderdate.setText(model.getCreated());
            Picasso.get().load(model.getCompany().getImageUrl()).into(holder.logo);



        if(onbuttonclicklistner!=null){
            holder.layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onbuttonclicklistner.onitemclick(position,model);
                }
            });
        }


    }

    public void removeDeletedItem(int position){
        this.data.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeRemoved(position , getItemCount() - position);
    }
    public void setlist(List<Datum> data) {
           this.data.clear();
            this.data.addAll(data);
            notifyDataSetChanged();


    }


    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView ordernum,orderdetails,orderdate;
        ImageView logo;
        ConstraintLayout layout;

        public ViewHolder(@NonNull final View itemView) {
            super(itemView);
            ordernum=itemView.findViewById(R.id.order_num);
            orderdetails=itemView.findViewById(R.id.orderdetails);
            orderdate=itemView.findViewById(R.id.orderdate);
            logo=itemView.findViewById(R.id.reslogo);
            layout=itemView.findViewById(R.id.layouut);



        }
    }
    public interface OnItemClickListner{
        void onitemclick(int pos,Datum model);
    }

}
