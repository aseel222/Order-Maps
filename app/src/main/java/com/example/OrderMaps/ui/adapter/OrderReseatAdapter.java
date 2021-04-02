package com.example.OrderMaps.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.OrderMaps.model.apimodel.orderdetailsmodel.Products;
import com.example.splashactivity.R;

import java.util.List;

public class OrderReseatAdapter extends RecyclerView.Adapter<OrderReseatAdapter.ViewHolder> {
    List<Products> products;
    Context context;


    public OrderReseatAdapter(List<Products> products,Context context) {
        this.products = products;
        this.context=context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.orderreset_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {

        final Products model=products.get(position);

        holder.ordername.setText(model.getName());
        holder.orderdprice.setText(model.getPrice());



    }
    public void setlist(List<Products> products)
    {

        this.products=products;
    notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView ordername,orderdprice;

        public ViewHolder(@NonNull final View itemView) {
            super(itemView);
            ordername=itemView.findViewById(R.id.ordertypetxt);
            orderdprice=itemView.findViewById(R.id.orderpricetxt);



        }
    }


}
