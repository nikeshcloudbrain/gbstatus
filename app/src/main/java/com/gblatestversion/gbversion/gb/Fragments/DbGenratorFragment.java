package com.gblatestversion.gbversion.gb.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;

import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gblatestversion.gbversion.gb.adaptor.MyFrameAdapter;
import com.gblatestversion.gbversion.gb.api.RenClient;
import com.gblatestversion.gbversion.gb.databinding.FragmentDbGenratorBinding;
import com.gblatestversion.gbversion.gb.model.DpGen.Emage;
import com.gblatestversion.gbversion.gb.model.DpGen.Example;

import java.util.ArrayList;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class DbGenratorFragment extends Fragment {

    FragmentDbGenratorBinding binding;
    MyFrameAdapter myFrameAdapter;
    GridLayoutManager mLayout;
    ArrayList<Emage> mList = new ArrayList<>();
    ArrayList<Example> mL = new ArrayList<>();

    static Parcelable recyclerViewState;

    int page;

    public DbGenratorFragment(int p) {
        // Required empty public constructor
        page = p;
        Log.e("appF", "DbGenratorFragment: "+page );
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getFrameDp(page);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentDbGenratorBinding.inflate(getLayoutInflater());

        binding.progreees.setVisibility(View.VISIBLE);
        mLayout = new GridLayoutManager(getActivity(), 4);
        mLayout.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                switch (myFrameAdapter.getItemViewType(position)) {
                    case 100:
                        return 4;
                    case 101:
                        return 1;
                    default:
                        return -1;
                }
            }
        });
        binding.rvFrame.setLayoutManager(mLayout);
        getFrameDp(page);


        if (mList.size() == 0) {
            binding.progreees.setVisibility(View.VISIBLE);
                getFrameDp(page);



        } else {
            binding.progreees.setVisibility(View.GONE);

        }

        binding.rvFrame.setAdapter(myFrameAdapter);




        return binding.getRoot();
    }


    public void getFrameDp(int Fpage) {
        RenClient.getInstance().getApi().getDpGen()
                .enqueue(new Callback<Example>() {
                    @Override
                    public void onResponse(Call<Example> call, Response<Example> response) {
                        try {
                            mList.clear();

                            mList.addAll(response.body().getData().get(Fpage).getImage());
                            myFrameAdapter = new MyFrameAdapter(getActivity(), mList);

                            Log.e("Appdd", "onResponse: " + mList);
                        } catch (Exception e) {
                            Log.e("TAG", e.toString());
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(Call<Example> call, Throwable t) {
                        Log.e("TAGR", t.getMessage());
                        binding.progreees.setVisibility(View.VISIBLE);

                    }
                });
    }

    @Override
    public void onPause() {
        super.onPause();
        recyclerViewState = Objects.requireNonNull(binding.rvFrame.getLayoutManager()).onSaveInstanceState();
    }

    @Override
    public void onResume() {
        super.onResume();
        getFrameDp(page);

        Objects.requireNonNull(binding.rvFrame.getLayoutManager()).onRestoreInstanceState(recyclerViewState);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        recyclerViewState = null;
    }




}