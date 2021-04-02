package com.example.OrderMaps.ui.fragement;

import android.app.AlertDialog;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.OrderMaps.model.apimodel.CancelOrderResponse;
import com.example.OrderMaps.model.apimodel.Datum;
import com.example.OrderMaps.model.apimodel.PendingOrderResponse;
import com.example.OrderMaps.model.apimodel.orderdetailsmodel.OrderDetailsResponse;
import com.example.OrderMaps.model.apimodel.orderdetailsmodel.Products;
import com.example.OrderMaps.model.local.PreferencesManager;
import com.example.OrderMaps.ui.adapter.OrderAdapter;
import com.example.OrderMaps.ui.adapter.OrderReseatAdapter;
import com.example.OrderMaps.viewmodel.CancelOrderViewModel;
import com.example.OrderMaps.viewmodel.OrderDetailsViewModel;
import com.example.OrderMaps.viewmodel.PendingOrderViewModel;
import com.example.OrderMaps.viewmodel.SearchViewModel;
import com.example.splashactivity.R;
import com.example.splashactivity.databinding.FragmentDailyOrderBinding;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;


public class DailyOrderFragment extends Fragment {

    //int deletedPosition = -1
    ArrayList<Datum> data;
    ArrayList<Products> data2;
    AlertDialog orderdialog;
    OrderAdapter adapter;
    OrderReseatAdapter adapter2;
    FragmentDailyOrderBinding binding;
    String id;
    RecyclerView orderdetailsrecycler;
    PendingOrderViewModel viewModel;
    OrderDetailsViewModel orderDetailsViewModel;
    PreferencesManager pref;
    int orderidint;
    SearchViewModel searchViewModel;
    boolean fl=true;
    Double sum;
    CancelOrderViewModel cancelOrderViewModel;


    public DailyOrderFragment() {
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_daily_order, container, false);
        View view = binding.getRoot();


        pref = new PreferencesManager(getActivity());
        viewModel = new ViewModelProvider(getActivity()).get(PendingOrderViewModel.class);
        cancelOrderViewModel = new ViewModelProvider(getActivity()).get(CancelOrderViewModel.class);
        orderDetailsViewModel = new ViewModelProvider(getActivity()).get(OrderDetailsViewModel.class);
        viewModel.getpendingoredrs("Bearer " + pref.fetchtoken());
        searchViewModel = new ViewModelProvider(getActivity()).get(SearchViewModel.class);
        observedata();


//        binding.swiperefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
//            @Override
//            public void onRefresh() {
//                observedata();
//                binding.swiperefresh.setRefreshing(false);
//
//
//            }
//        });

        setupAdapter();

        observedata2();
binding.searchdailyorder.addTextChangedListener(new TextWatcher() {
    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        fl=false;
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        validatesearch(s.toString());


    }
});




        adapter.setOnbuttonclicklistner(new OrderAdapter.OnItemClickListner() {
            @Override
            public void onitemclick(int pos, Datum model) {
                 orderdialog = new AlertDialog.Builder(getActivity()).create();
                orderidint = model.getId();
                orderDetailsViewModel.getorderdetails(orderidint, "Bearer " + pref.fetchtoken());

                View v = getActivity().getLayoutInflater().inflate(R.layout.order_dialog, null);
                orderdialog.setView(v);

                Button showorderdetails = v.findViewById(R.id.visibleorder);
                Button cancelorder = v.findViewById(R.id.cancelorder);
                Button confirmorder = v.findViewById(R.id.confirmbtn);
                TextView ordernum = v.findViewById(R.id.ordernum);
                ImageView logoorder = v.findViewById(R.id.logores);

                ordernum.setText("طلب" + " " + model.getId());
                Picasso.get().load(model.getCompany().getImageUrl()).into(logoorder);


                orderdialog.show();
                confirmorder.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int id2 = model.getId();
                        String status = "progress";

                        cancelOrderViewModel.postorderstatus(id2, status, "Bearer " + pref.fetchtoken());
                        /*cancelOrderViewModel.cancelOrderResponseMutableLiveData.observe(getViewLifecycleOwner(), new Observer<CancelOrderResponse>() {
                            @Override
                            public void onChanged(CancelOrderResponse cancelOrderResponse) {



                            }
                        });*/

                    }
                });
                cancelorder.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AlertDialog cancelorderdialog = new AlertDialog.Builder(getActivity()).create();


                        View v2 = getActivity().getLayoutInflater().inflate(R.layout.cancelorder_dialog, null);
                        cancelorderdialog.setView(v2);
                        Button cancel = v2.findViewById(R.id.cancelorder);
                        ImageView exit = v2.findViewById(R.id.cancelbtn);
                        TextView cancelorderdetails = v2.findViewById(R.id.cancelorderdetails);
                        cancelorderdetails.setText("اذا اكدت الالغاء سوف يتم الغاء طلب رقم " + " " + model.getId());


                        exit.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                cancelorderdialog.dismiss();
                            }
                        });
                        cancel.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                int idorder = model.getId();
                                String statusorder = "cancel";
                                cancelOrderViewModel.postorderstatus(idorder, statusorder, "Bearer " + pref.fetchtoken());



                            }
                        });

                        orderdialog.dismiss();
                        cancelorderdialog.show();



                    }
                });
                showorderdetails.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AlertDialog orderdetails = new AlertDialog.Builder(getActivity()).create();


                        View v1 = getActivity().getLayoutInflater().inflate(R.layout.orderdetails_dialog, null);
                        orderdetails.setView(v1);
                        TextView orderid = v1.findViewById(R.id.ordertxt);
                        TextView datetxt = v1.findViewById(R.id.datetxt);

                        TextView storename = v1.findViewById(R.id.storenametxt);
                        TextView ordertotal = v1.findViewById(R.id.totlamoney);
                        TextView orderprice = v1.findViewById(R.id.costdetailstxt);
                        TextView ordertx = v1.findViewById(R.id.ordertax);
                        TextView address = v1.findViewById(R.id.orderdetails_txt);
                        orderdetailsrecycler = v1.findViewById(R.id.orderreseat_recyclerview);
                        setupAdapter2();

                        ImageView reslogo = v1.findViewById(R.id.logo);
                        orderDetailsViewModel.orderDetailsResponseMutableLiveData.observe(getViewLifecycleOwner(), new Observer<OrderDetailsResponse>() {
                            @Override
                            public void onChanged(OrderDetailsResponse orderDetailsResponse) {
                                orderid.setText("طلب" + " " + orderDetailsResponse.getData().get(0).getId());
                                datetxt.setText(orderDetailsResponse.getData().get(0).getCreated());
                                ordertotal.setText("اجمالي التكلفه" + " " + orderDetailsResponse.getData().get(0).getPrice() + "ريال");
                                ordertx.setText("الضريبه" + " " + orderDetailsResponse.getData().get(0).getTax() + "ريال");
                                address.setText(orderDetailsResponse.getData().get(0).getAddress());


                                sum = Double.parseDouble(orderDetailsResponse.getData().get(0).getPrice()) - orderDetailsResponse.getData().get(0).getTax();
                                orderprice.setText("التكلفه" + " " + sum + "ريال" + " ");

                                storename.setText("متجر:" + " " + orderDetailsResponse.getData().get(0).getProvider().getName());
                                Picasso.get().load(orderDetailsResponse.getData().get(0).getCompany().getImageUrl()).into(reslogo);
                                adapter2.setlist(orderDetailsResponse.getData().get(0).getProducts());

                            }
                        });

                        orderdetails.show();
                        orderdialog.dismiss();

                    }
                });


            }
        });

        return view;
    }


    private void setupAdapter() {

        LinearLayoutManager linearLayout = new LinearLayoutManager(getActivity());
        binding.dailyorderRecyclerview.setLayoutManager(linearLayout);
        adapter = new OrderAdapter(new ArrayList<Datum>(), getActivity());
        binding.dailyorderRecyclerview.setAdapter(adapter);

    }

    private void setupAdapter2() {
        LinearLayoutManager linearLayout = new LinearLayoutManager(getActivity());
        orderdetailsrecycler.setLayoutManager(linearLayout);
        adapter2 = new OrderReseatAdapter(new ArrayList<Products>(), getActivity());
        orderdetailsrecycler.setAdapter(adapter2);

    }


    private void observedata() {
        viewModel.pendingOrderResponseMutableLiveData.observe(getViewLifecycleOwner(), new Observer<PendingOrderResponse>() {
            @Override
            public void onChanged(PendingOrderResponse pendingOrderResponse) {

               adapter.setlist(pendingOrderResponse.getData());
                if(fl){
                    viewModel.getpendingoredrs("Bearer " + pref.fetchtoken());
                }


            }
        });
        cancelOrderViewModel.cancelOrderResponseMutableLiveData.observe(getViewLifecycleOwner(), new Observer<CancelOrderResponse>() {
            @Override
            public void onChanged(CancelOrderResponse cancelOrderResponse) {

                String state = cancelOrderResponse.getData().getStatus();
                if (!state.equals("cancel")){
                    AlertDialog confirmorderdialog = new AlertDialog.Builder(getActivity()).create();


                    View v3 = getActivity().getLayoutInflater().inflate(R.layout.confirmorder_dialog, null);
                    confirmorderdialog.setView(v3);
                    TextView confirmordertxt = v3.findViewById(R.id.confirmedordertxt);
                    ImageView exit=v3.findViewById(R.id.backbtn);
                    confirmordertxt.setText("تم تاكيد طلب رقم " + " " + cancelOrderResponse.getData().getId());
                    viewModel.getpendingoredrs("Bearer " + pref.fetchtoken());
                    exit.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            confirmorderdialog.dismiss();
                        }
                    });


                    confirmorderdialog.show();
                    orderdialog.dismiss();
                }else {
                    Toast.makeText(getActivity(), "تم الغاء الطلب بنجاح", Toast.LENGTH_LONG).show();

                    // adapter.removeDeletedItem(pos);
                    //complete
                    viewModel.getpendingoredrs("Bearer " + pref.fetchtoken());
                }



            }
        });
        viewModel.isLoading().observe(getViewLifecycleOwner(),isloading -> {
            if (isloading) {
                binding.loading.setVisibility(View.VISIBLE);
            } else {
                binding.loading.setVisibility(View.GONE);
            }
        });
        viewModel.getMsgRes().observe(getViewLifecycleOwner(), res -> {
            Toast.makeText(getActivity(),"لا يوجد أتصال بالإنترنت",Toast.LENGTH_LONG).show();
        });
    }

    public void validatesearch(String s) {
        String status="pending";

        searchViewModel.search(s,status, "Bearer " + pref.fetchtoken());

    }

    public void observedata2() {
        searchViewModel.searchResponseMutableLiveData.observe(getViewLifecycleOwner(), new Observer<PendingOrderResponse>() {
            @Override
            public void onChanged(PendingOrderResponse pendingOrderResponse) {
                adapter.setlist(pendingOrderResponse.getData());

            }
        });
        searchViewModel.isLoading().observe(getViewLifecycleOwner(),isloading -> {
            if (isloading) {
                binding.loading.setVisibility(View.VISIBLE);
            } else {
                binding.loading.setVisibility(View.GONE);
            }
        });

    }


}

