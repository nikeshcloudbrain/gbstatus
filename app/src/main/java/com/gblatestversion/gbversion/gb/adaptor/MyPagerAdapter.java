package com.gblatestversion.gbversion.gb.adaptor;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.gblatestversion.gbversion.gb.Fragments.DbGenratorFragment;

public class MyPagerAdapter extends FragmentPagerAdapter {
    private String[] tabTitles = new String[]{"Popular", "Text", "Basic","Badge", "Cute", "Floral","Gold", "Nature", "Pattern","Texture", "AnimalPrint"};

    public MyPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new DbGenratorFragment(position);
            case 1:
                return new DbGenratorFragment(position);
            case 2:
                return new DbGenratorFragment(position);
            case 3:
                return new DbGenratorFragment(position);
            case 4:
                return new DbGenratorFragment(position);
            case 5:
                return new DbGenratorFragment(position);
            case 6:
                return new DbGenratorFragment(position);
            case 7:
                return new DbGenratorFragment(position);
            case 8:
                return new DbGenratorFragment(position);
            case 9:
                return new DbGenratorFragment(position);
            case 10:
                return new DbGenratorFragment(position);
            default:
                return new DbGenratorFragment(0);
        }
    }

    @Override
    public int getCount() {
        return tabTitles.length;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitles[position];
    }
}
