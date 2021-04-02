package com.example.OrderMaps.ui.fragement;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.OrderMaps.model.apimodel.BillDatum;
import com.example.OrderMaps.model.apimodel.BillOrderResponse;
import com.example.OrderMaps.model.local.PreferencesManager;
import com.example.OrderMaps.ui.activities.StatisticsActivity;
import com.example.OrderMaps.ui.adapter.MoneyAdapter;
import com.example.OrderMaps.viewmodel.BillViewModel;
import com.example.OrderMaps.viewmodel.SearchBillViewModel;
import com.example.splashactivity.R;
import com.example.splashactivity.databinding.FragmentGalleryBinding;

import java.util.ArrayList;
import java.util.Calendar;


public class GalleryFragment extends Fragment {
    FragmentGalleryBinding binding;
    ArrayList<BillDatum> data;

    BillViewModel billViewModel;
    SearchBillViewModel searchBillViewModel;
    PreferencesManager preferencesManager;
      MoneyAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        OnBackPressedCallback callback = new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                Fragment fragment = new HomeFragment();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.content_frame, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();


            }
        };
        requireActivity().getOnBackPressedDispatcher().addCallback(this, callback);

    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_gallery, container, false);
        View view = binding.getRoot();
        billViewModel = new ViewModelProvider(getActivity()).get(BillViewModel.class);
        preferencesManager = new PreferencesManager(getActivity());
        billViewModel.getbilldata("Bearer " + preferencesManager.fetchtoken());
        observedata();
        setupAdapter();



        binding.statsicsbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), StatisticsActivity.class);
                getActivity().startActivityForResult(intent, 1);
            }
        });




        return view;


    }

    private void setupAdapter() {
        LinearLayoutManager linearLayout = new LinearLayoutManager(getActivity());
        binding.moneyRecyclerview.setLayoutManager(linearLayout);
        adapter = new MoneyAdapter(new ArrayList<BillDatum>(), getActivity());
        binding.moneyRecyclerview.setAdapter(adapter);

    }

    public void observedata() {
        billViewModel.billOrderResponseMutableLiveData.observe(getViewLifecycleOwner(), new Observer<BillOrderResponse>() {
            @Override
            public void onChanged(BillOrderResponse billOrderResponse) {
                adapter.setlist(billOrderResponse.getData());
                billViewModel.getbilldata("Bearer " + preferencesManager.fetchtoken());

            }
        });
    }






}