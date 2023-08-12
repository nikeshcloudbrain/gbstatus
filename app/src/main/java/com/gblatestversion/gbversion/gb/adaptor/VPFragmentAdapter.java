package com.gblatestversion.gbversion.gb.adaptor;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.ArrayList;

public class VPFragmentAdapter extends FragmentStateAdapter {

    public ArrayList<Fragment> arrayList = new ArrayList<>();
    public ArrayList<String> StringArrayList = new ArrayList<>();


    public VPFragmentAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return arrayList.get(position);
    }

    public void addFragment(Fragment fragment, String title) {
        arrayList.add(fragment);
        StringArrayList.add(title);
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }
}