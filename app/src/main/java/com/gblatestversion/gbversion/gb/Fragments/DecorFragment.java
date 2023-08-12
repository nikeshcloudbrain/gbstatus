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


import com.gblatestversion.gbversion.gb.adaptor.DecorAdapter;
import com.gblatestversion.gbversion.gb.databinding.FragmentGnlDecorBinding;

import java.util.ArrayList;
import java.util.List;

public class DecorFragment extends Fragment {

    FragmentGnlDecorBinding gbvDecorBinding;
    Activity activity;

    DecorAdapter decorAdapter;
    List<String> arraylist = new ArrayList<>();

    public DecorFragment(Activity activity) {
        this.activity = activity;
    }

    public static DecorFragment newInstance(Activity activity) {
        return new DecorFragment(activity);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        gbvDecorBinding = FragmentGnlDecorBinding.inflate(getLayoutInflater(), container, false);
        return gbvDecorBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        gbvDecorBinding.recyclerView.setLayoutManager(new LinearLayoutManager(activity));
        decorAdapter = new DecorAdapter(activity, arraylist, true);
        gbvDecorBinding.recyclerView.setAdapter(decorAdapter);

        gbvDecorBinding.ivRemove.setOnClickListener(v -> gbvDecorBinding.editMessage.setText(""));
        gbvDecorBinding.editMessage.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                decorAdapter.refreshText(String.valueOf(charSequence));
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
        insertData("obj$ect");
        decorAdapter.refresh(arraylist);
        checkData();
    }

    public void insertData(String obj) {
        arraylist.add("★·.·´¯`·.·★ " + obj + " ★·.·´¯`·.·★");
        arraylist.add("▁ ▂ ▄ ▅ ▆ ▇ █ " + obj + " █ ▇ ▆ ▅ ▄ ▂ ▁ ");
        arraylist.add("°°°·.°·..·°¯°·._.· " + obj + " °°°·.°·..·°¯°·._.·");
        arraylist.add("ıllıllı " + obj + " ıllıllı");
        arraylist.add("•?((¯°·._.• " + obj + "  •._.·°¯))؟•");
        arraylist.add("▌│█║▌║▌║ " + obj + "║▌║▌║█│▌");
        arraylist.add("×º°”˜`”°º× " + obj + " ×º°”˜`”°º×");
        arraylist.add("*•.¸♡ " + obj + " ♡¸.•*");
        arraylist.add("╰☆☆ " + obj + " ☆☆╮");
        arraylist.add("¯´•._.• " + obj + " •._.•´¯");
        arraylist.add("¸„.-•~¹°”ˆ˜¨ " + obj + "  ¨˜ˆ”°¹~•-.„¸");
        arraylist.add("░▒▓█ " + obj + " █▓▒░");
        arraylist.add("░▒▓█►─═ " + obj + " ═─◄█▓▒░");
        arraylist.add("★彡 " + obj + " 彡★");
        arraylist.add("➶➶➶➶➶ " + obj + " ➷➷➷➷➷");
        arraylist.add("▀▄▀▄▀▄  " + obj + " ▀▄▀▄▀▄ ");
        arraylist.add("(-_-)" + obj + "(-_-) ");
        arraylist.add("-漫~*&¨¯¨&*·舞~" + obj + "-漫~*&¨¯¨&*·舞~");
        arraylist.add("๑۞๑,¸¸,ø¤º°`°๑۩" + obj + "๑۩ ,¸¸,ø¤º°`°๑۞๑");
        arraylist.add("【｡_｡】" + obj + "【｡_｡】");
        arraylist.add("◦•●◉✿ " + obj + "✿◉●•◦");
        arraylist.add("╚»★«╝ " + obj + "╚»★«╝ ");
        arraylist.add("ღ(¯`◕‿◕´¯) ♫ ♪ ♫" + obj + "♫ ♪ ♫ (¯`◕‿◕´¯)ღ");
        arraylist.add("«-(¯`v´¯)-«" + obj + " »-(¯`v´¯)-»");
        arraylist.add("✿.｡.:* ☆:**:. " + obj + ".:**:.☆*.:｡.✿");
        arraylist.add("ღ(¯`◕‿◕´¯) ♫ ♪ ♫ " + obj + "♫ ♪ ♫ (¯`◕‿◕´¯)ღ");
    }

    public void checkData() {
        gbvDecorBinding.includedError.progress.setVisibility(View.GONE);
        if (decorAdapter.getItemCount() > 0) {
            gbvDecorBinding.includedError.tvNodata.setVisibility(View.GONE);
        } else
            gbvDecorBinding.includedError.tvNodata.setVisibility(View.VISIBLE);
    }

}