package com.example.OrderMaps.ui.fragement;

import android.content.Intent;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.OrderMaps.model.apimodel.SupportResponse;
import com.example.OrderMaps.model.local.PreferencesManager;
import com.example.OrderMaps.viewmodel.SupportViewModel;
import com.example.splashactivity.R;
import com.example.splashactivity.databinding.FragmentAllOrdersBinding;
import com.example.splashactivity.databinding.FragmentSupportBinding;

public class SupportFragment extends Fragment {
   FragmentSupportBinding fragmentSupportBinding;
   PreferencesManager preferencesManager;
   SupportViewModel supportViewModel;

    public SupportFragment() {

    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragmentSupportBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_support, container, false);
        View view = fragmentSupportBinding.getRoot();
        preferencesManager=new PreferencesManager(getActivity());
        supportViewModel=new ViewModelProvider(getActivity()).get(SupportViewModel.class);
        observedata();
        fragmentSupportBinding.faqconfirmbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validdata();
            }
        });





        return view;

    }
    public void validdata(){
        String messege=fragmentSupportBinding.faqetxt.getText().toString().trim();
        if(messege.isEmpty()){
           fragmentSupportBinding.faqetxt.setError("ادخل رقم الجوال");
            fragmentSupportBinding.faqetxt.requestFocus();
        }
        supportViewModel.postsupport(messege,"Bearer " + preferencesManager.fetchtoken());
    }
    public void observedata(){
        supportViewModel.supportResponseMutableLiveData.observe(getViewLifecycleOwner(), new Observer<SupportResponse>() {
            @Override
            public void onChanged(SupportResponse supportResponse) {
                Toast.makeText(getActivity(),"تم استلام سؤالك ",Toast.LENGTH_LONG).show();

            }
        });
    }

}