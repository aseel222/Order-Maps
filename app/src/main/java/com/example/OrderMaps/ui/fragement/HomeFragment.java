package com.example.OrderMaps.ui.fragement;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.OrderMaps.model.apimodel.HomeBillResponse;
import com.example.OrderMaps.model.apimodel.ProviderStatusResponse;
import com.example.OrderMaps.model.local.PreferencesManager;
import com.example.OrderMaps.ui.adapter.TabOrderAdapter;
import com.example.OrderMaps.viewmodel.HomeBillViewModel;
import com.example.OrderMaps.viewmodel.ProviderStatusViewModel;
import com.example.splashactivity.R;
import com.example.splashactivity.databinding.FragmentHomeBinding;
import com.google.android.material.tabs.TabLayout;


public class HomeFragment extends Fragment {
    FragmentHomeBinding binding;
    TabOrderAdapter adapter;
    ProviderStatusViewModel viewModel;
    PreferencesManager preferencesManager;
    HomeBillViewModel homeBillViewModel;
    boolean status;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false);
        View view = binding.getRoot();
        homeBillViewModel = new ViewModelProvider(getActivity()).get(HomeBillViewModel.class);
        viewModel = new ViewModelProvider(getActivity()).get(ProviderStatusViewModel.class);
        preferencesManager = new PreferencesManager(getActivity());
        homeBillViewModel.gethomebill("Bearer " + preferencesManager.fetchtoken());
        observehomebill();
        getActivity().setTitle("الصفحه الرئيسيه");
        observedata();
        getstatus();
        binding.togleBtn.setChecked(preferencesManager.getstatus());




       /* binding.notificationIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), NotificationActivity.class);
                startActivity(intent);
            }
        });*/
        binding.tablayout.addTab(binding.tablayout.newTab().setText("الطلبات المستلمه"));
        binding.tablayout.addTab(binding.tablayout.newTab().setText("تحت التحضير"));
        adapter = new TabOrderAdapter(getActivity(), getChildFragmentManager(), binding.tablayout.getTabCount());
        binding.pager.setAdapter(adapter);
        binding.pager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(binding.tablayout));
        binding.tablayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                binding.pager.setCurrentItem(tab.getPosition());

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        return view;


    }

    public void observedata() {
        viewModel.providerStatusResponseMutableLiveData.observe(getActivity(), new Observer<ProviderStatusResponse>() {
            @Override
            public void onChanged(ProviderStatusResponse providerStatusResponse) {
                //Toast.makeText(getActivity(),providerStatusResponse.getData().getStatus(),Toast.LENGTH_LONG).show();


            }
        });
    }

    public void getstatus() {
        binding.togleBtn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {


                    binding.closedoropen.setText("مفتوح");
                    preferencesManager.savestatus(isChecked);
                    viewModel.getstatus(isChecked, "Bearer " + preferencesManager.fetchtoken());


                } else {
                    binding.closedoropen.setText("مغلق");
                    preferencesManager.savestatus(isChecked);
                    viewModel.getstatus(isChecked, "Bearer " + preferencesManager.fetchtoken());

                }

            }
        });


    }

    public void observehomebill() {
        homeBillViewModel.homeBillResponseMutableLiveData.observe(getViewLifecycleOwner(), new Observer<HomeBillResponse>() {
            @Override
            public void onChanged(HomeBillResponse homeBillResponse) {
                binding.totalmoney.setText(homeBillResponse.getData().getTotal() + "الكل");
                binding.dayMoney.setText(homeBillResponse.getData().getToday() + "اليوم");
                homeBillViewModel.gethomebill("Bearer " + preferencesManager.fetchtoken());
            }
        });
    }
}