package com.example.OrderMaps.ui.adapter;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.OrderMaps.ui.fragement.AllOrdersFragment;
import com.example.OrderMaps.ui.fragement.DailyOrderFragment;

public class TabOrderAdapter extends FragmentPagerAdapter {
    Context context;
    int totalTabs;



    public TabOrderAdapter(Context context,@NonNull FragmentManager fm, int totalTabs) {
        super(fm);
        context= context;
        this.totalTabs=totalTabs;
    }


    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                DailyOrderFragment dailyOrderFragment = new DailyOrderFragment();
                return dailyOrderFragment;
            case 1:
                AllOrdersFragment allOrdersFragment = new AllOrdersFragment();
                return allOrdersFragment;

            default:
                return null;
        }
    }
    @Override
    public int getCount() {
        return totalTabs;
    }

}
