package com.example.OrderMaps.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.OrderMaps.model.apimodel.Datum;
import com.example.splashactivity.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class Order2Adapter extends RecyclerView.Adapter<Order2Adapter.ViewHolder> {
    List<Datum> data = new ArrayList<>();



    private Context context;


    public Order2Adapter(List<Datum> data ,Context context) {

        this.data = data;

        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.orderall_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {

        final Datum model = data.get(position);
        holder.ordernum.setText("طلب" + " " + model.getId());

        holder.orderdetails.setText(model.getAddress());
        holder.orderdateall.setText(model.getCreated());

        Picasso.get().load(model.getCompany().getImageUrl()).into(holder.logo);


    }

    public void setlist(List<Datum> data) {
        this.data.clear();
        this.data.addAll(data);

        notifyDataSetChanged();

    }

//    public void setlist2(List<Datum> data2) {
//        this.data.clear();
//       this.data.addAll(data2);
//       notifyDataSetChanged();
////        this.data2 = new ArrayList<>();
////        data2.forEach(item -> this.data2.add(item));
//
//
////        ArrayList<Datum> temp = new ArrayList<>();
////        temp.addAll(data2);
////        setlist(temp);
//
//    }


    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView ordernum, orderdetails,orderdateall;
        ImageView logo;


        public ViewHolder(@NonNull final View itemView) {
            super(itemView);
            orderdateall=itemView.findViewById(R.id.orderalldate);
            ordernum = itemView.findViewById(R.id.orderall_num);
            orderdetails = itemView.findViewById(R.id.orderalldetails);
            logo = itemView.findViewById(R.id.resalllogo);


        }
    }


}
