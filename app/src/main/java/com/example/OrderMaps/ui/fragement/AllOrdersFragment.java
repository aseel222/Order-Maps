package com.example.OrderMaps.ui.fragement;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.OrderMaps.model.apimodel.Datum;
import com.example.OrderMaps.model.apimodel.PendingOrderResponse;
import com.example.OrderMaps.model.local.PreferencesManager;
import com.example.OrderMaps.ui.adapter.Order2Adapter;
import com.example.OrderMaps.viewmodel.ProgressOrderViewModel;
import com.example.OrderMaps.viewmodel.Search2ViewModel;
import com.example.splashactivity.R;
import com.example.splashactivity.databinding.FragmentAllOrdersBinding;
import com.google.gson.Gson;

import java.util.ArrayList;


public class AllOrdersFragment extends Fragment {
    ArrayList<Datum> data;
    Search2ViewModel search2ViewModel;

    Order2Adapter adapter;
    FragmentAllOrdersBinding binding;
    boolean fl=true;
    ProgressOrderViewModel viewModel;
    PreferencesManager preferencesManager;

    public AllOrdersFragment() {
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_all_orders, container, false);
        View view = binding.getRoot();
        preferencesManager = new PreferencesManager(getActivity());
        viewModel = new ViewModelProvider(getActivity()).get(ProgressOrderViewModel.class);
        search2ViewModel = new ViewModelProvider(getActivity()).get(Search2ViewModel.class);
        viewModel.getprogressorders("Bearer " + preferencesManager.fetchtoken());
        observedata();
        setupAdapter();
        observedtat();



//        binding.swiperefershall.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
//            @Override
//            public void onRefresh() {
//                observedata();
//                binding.swiperefershall.setRefreshing(false);
//
//            }
//        });
       /* binding.searchallorders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validatedata();
            }
        });
        */
        binding.searchallorders.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                fl=false;



            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {


                    validatedata(s.toString());


            }

            @Override
            public void afterTextChanged(Editable s) {







            }
        });


        return view;
    }

    public void observedata() {
        viewModel.progressOrderResponseMutableLiveData.observe(getViewLifecycleOwner(), new Observer<PendingOrderResponse>() {
            @Override
            public void onChanged(PendingOrderResponse pendingOrderResponse) {
                adapter.setlist(pendingOrderResponse.getData());
                if(fl){
                     viewModel.getprogressorders("Bearer " + preferencesManager.fetchtoken());
                }

                //adapter.setlist2(pendingOrderResponse.getData());

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
            Toast.makeText(getActivity(),"لايوجد إتصال بالإنترنت",Toast.LENGTH_LONG).show();
        });
    }

    private void setupAdapter() {
        LinearLayoutManager linearLayout = new LinearLayoutManager(getActivity());
        binding.recyclerAllorders.setLayoutManager(linearLayout);
        adapter = new Order2Adapter(new ArrayList<Datum>(), getActivity());
        binding.recyclerAllorders.setAdapter(adapter);

    }

    public void validatedata(String text) {
        //String id = binding.searchallorders.getText().toString().trim();
//        if (binding.searchallorders.isPressed()) {
//            Log.d("fjvnfjv", "validatedata: text is empty " + text);
//            Log.d("fjvnfjv", "validatedata: text is empty " + adapter.data2.size());
//           // adapter.setlist(data2);
//
//
//        } else {
            Log.d("fjvnfjv", "validatedata: text is not empty " + text);
            String status = "progress";
            search2ViewModel.search(text, status, "Bearer " + preferencesManager.fetchtoken());

        }


        public void observedtat () {
            search2ViewModel.searchResponseMutableLiveData.observe(getViewLifecycleOwner(), new Observer<PendingOrderResponse>() {
                @Override
                public void onChanged(PendingOrderResponse pendingOrderResponse) {
                    adapter.setlist(pendingOrderResponse.getData());
                    //Log.wtf("aseel", "observedtat: " + new Gson().toJson(pendingOrderResponse.getData()));

                }
            });
            search2ViewModel.isLoading().observe(getViewLifecycleOwner(),isloading -> {
                if (isloading) {
                    binding.loading.setVisibility(View.VISIBLE);
                } else {
                    binding.loading.setVisibility(View.GONE);
                }
            });

        }

}




