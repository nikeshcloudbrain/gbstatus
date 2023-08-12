package com.gblatestversion.gbversion.gb.Fragments;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.gblatestversion.gbversion.gb.adaptor.FontAdapter;
import com.gblatestversion.gbversion.gb.databinding.FragmentGnlFontBinding;


public class FontFragment extends Fragment {

    Activity activity;
    FragmentGnlFontBinding gbvFontBinding;
    FontAdapter fontAdapter;

    public FontFragment(Activity activity) {
        this.activity=activity;
    }

    public static FontFragment newInstance(Activity activity) {
        return new FontFragment(activity);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        gbvFontBinding = FragmentGnlFontBinding.inflate(getLayoutInflater(), container, false);
        return gbvFontBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        gbvFontBinding.recyclerView.setLayoutManager(new LinearLayoutManager(activity));

        fontAdapter = new FontAdapter(activity);

        gbvFontBinding.ivRemove.setOnClickListener(v -> gbvFontBinding.editMessage.setText(""));
        gbvFontBinding.editMessage.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                fontAdapter.refreshText(String.valueOf(charSequence));
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                refreshData();
            }
        }, 500);

    }
    public void refreshData() {
        gbvFontBinding.recyclerView.setAdapter(fontAdapter);
        checkData();
    }

    public void checkData() {
        gbvFontBinding.includedError.progress.setVisibility(View.GONE);
        if (fontAdapter.getItemCount() > 0) {
            gbvFontBinding.includedError.tvNodata.setVisibility(View.GONE);
        } else
            gbvFontBinding.includedError.tvNodata.setVisibility(View.VISIBLE);
    }
}