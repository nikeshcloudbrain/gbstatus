package com.gblatestversion.gbversion.gb.Activity.tools.TextTools;


import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;


import com.gblatestversion.gbversion.gb.R;
import com.gblatestversion.gbversion.gb.adsw.WFSAppLoadAds;
import com.gblatestversion.gbversion.gb.databinding.ActivityNameStyleDetailBinding;

public class NameStyleDetailActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener {
    String[] list;
    int position = 0;
    int size;
    String text;
    NameDetailPagerAdapter nameDetailPagerAdapter;
ActivityNameStyleDetailBinding binding;
   
  @Override
    public void onBackPressed() {
        WFSAppLoadAds.getInstance().showInterstitialBack(this, this::finish);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
binding=ActivityNameStyleDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.tool.icBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        binding.tool.title.setText("Name Text Effect");

WFSAppLoadAds.getInstance().showNativeBottomDynamic(this,binding.frameViewAds);
        Intent intent = getIntent();
        position = intent.getIntExtra("position", 0);
        text = intent.getStringExtra("text");
        size = intent.getIntExtra("size", 0);
        new AsyncTaskBase().execute(new Void[0]);

    }

    public void onPageScrollStateChanged(int i) {
    }

    public void onPageScrolled(int i, float f, int i2) {
    }

    public void onPageSelected(int i) {
    }

    private class AsyncTaskBase extends AsyncTask<Void, Void, Void> {
        private AsyncTaskBase() {
        }

        public void onPreExecute() {
            super.onPreExecute();
        }

        public Void doInBackground(Void... voidArr) {
            list = getResources().getStringArray(R.array.name_style);
            return null;
        }

        public void onPostExecute(Void voidR) {
            super.onPostExecute(voidR);
            ViewPager viewPager = findViewById(R.id.pgrNameDetailId);
            nameDetailPagerAdapter = new NameDetailPagerAdapter(NameStyleDetailActivity.this.getSupportFragmentManager());
            viewPager.setOnPageChangeListener(NameStyleDetailActivity.this);
            viewPager.setAdapter(nameDetailPagerAdapter);
            viewPager.setPageMargin(20);
            viewPager.setPageTransformer(true, new TransformerOutSlide());
        }
    }

    private class NameDetailPagerAdapter extends FragmentPagerAdapter {
        public NameDetailPagerAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
        }

        public Fragment getItem(int i) {
            return NameStyleFragment.content(text, list[position + i]);
        }

        public int getCount() {
            return size - position;
        }
    }

}
