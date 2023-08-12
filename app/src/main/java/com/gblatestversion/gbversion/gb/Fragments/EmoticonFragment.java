package com.gblatestversion.gbversion.gb.Fragments;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;


import com.gblatestversion.gbversion.gb.adaptor.DecorAdapter;
import com.gblatestversion.gbversion.gb.databinding.FragmentGnlEmoticonBinding;

import java.util.ArrayList;
import java.util.List;


public class EmoticonFragment extends Fragment {

    FragmentGnlEmoticonBinding gbvEmoticonBinding;
    Activity activity;
    List<String> arraylist = new ArrayList<>();
    DecorAdapter decorAdapter;
    int POS = 1;

    public EmoticonFragment(Activity activity, int POS) {
        this.activity = activity;
        this.POS = POS;
    }

    public static EmoticonFragment newInstance(Activity activity, int POS) {
        return new EmoticonFragment(activity, POS);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        gbvEmoticonBinding = FragmentGnlEmoticonBinding.inflate(getLayoutInflater());
        return gbvEmoticonBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        gbvEmoticonBinding.recyclerView.setLayoutManager(new LinearLayoutManager(activity));
        decorAdapter = new DecorAdapter(activity, arraylist,true);
        gbvEmoticonBinding.recyclerView.setAdapter(decorAdapter);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                refreshData();
            }
        }, 500);
    }

    public void refreshData() {
        if (POS == 1)
            insertData1();
        else if (POS == 2)
            insertData2();
        else if (POS == 3)
            insertData3();
        else if (POS == 4)
            insertData4();
        decorAdapter.refresh(arraylist);
        checkData();
    }

    public void insertData1() {
        arraylist.add("⊂◉‿◉つ");
        arraylist.add("✿◕ ‿ ◕✿");
        arraylist.add("◎[▪‿▪]◎");
        arraylist.add("(✿◠‿◠)");
        arraylist.add("☆(❁‿❁)☆");
        arraylist.add("≧◉◡◉≦");
        arraylist.add("≧◠‿◠≦");
        arraylist.add("٩(˘◡˘)۶\"");
        arraylist.add("(^)o(^)");
        arraylist.add("（*^_^*）");
        arraylist.add("( ͡° ͜ʖ ͡°)");
        arraylist.add("┑(￣▽￣)┍");
        arraylist.add("ლ(╹◡╹ლ)");
        arraylist.add("ʘ‿ʘ");
        arraylist.add("o(∩_∩)o");
        arraylist.add("(•ิ‿•ิ)");
        arraylist.add("（⌒▽⌒）");
        arraylist.add("(◠‿◠)");
        arraylist.add("≖‿≖");
        arraylist.add("(◕‿◕)");
        arraylist.add("ಥ‿ಥ");
        arraylist.add("(●⌒∇⌒●)");
        arraylist.add("(❁´◡`❁)");
    }

    public void insertData2() {
        arraylist.add("(︶︹︺)");
        arraylist.add("(◕︿◕✿)");
        arraylist.add("(•̪●)");
        arraylist.add("(◕︵◕)");
        arraylist.add("(◕﹏◕✿)");
        arraylist.add("（︶^︶）");
        arraylist.add("（┬＿┬）");
        arraylist.add("╥﹏╥");
        arraylist.add("(〒︿〒﹀)");
        arraylist.add("⊙︿⊙");
        arraylist.add("╯︿╰");
        arraylist.add("╯﹏╰");
        arraylist.add("ˇ︿ˇ");
        arraylist.add("ˇ﹏ˇ");
        arraylist.add("⊙﹏⊙");
        arraylist.add("●︿●");
        arraylist.add("(´◔‸◔`)");
        arraylist.add("(,Ծ‸Ծ,)");
        arraylist.add("ˇ﹏ˇ");
        arraylist.add("(´°̥̥̥̥̥̥̥̥ω°̥̥̥̥̥̥̥̥｀)ಥ");
        arraylist.add("(╯︵╰,)");
        arraylist.add("(个_个)");
    }

    public void insertData3() {
        arraylist.add("♥‿♥");
        arraylist.add("♥╣[-_-]╠♥");
        arraylist.add("★~(◠‿◕✿)");
        arraylist.add("(✿◠‿◠)");
        arraylist.add("( ･_･)♡");
        arraylist.add("(●&◡&●)ﾉ♥");
        arraylist.add("(。･ω･｡)ﾉ♡");
        arraylist.add("(。♡‿♡。)");
        arraylist.add("♡＾▽＾♡");
        arraylist.add("(ღ˘⌣˘ღ)");
        arraylist.add("-`ღ´-");
        arraylist.add("☜♡☞");
        arraylist.add("{♥‿♥}");
        arraylist.add("[̲̅ə̲̅٨̲̅٥̲̅٦̲̅]");
        arraylist.add("ℓ٥ﻻ ﻉ√٥υ");
        arraylist.add("▂▃▄▅▆▇█▓▒░LoveYou░▒▓█▇▆▅▄▃▂");
        arraylist.add("➳♥");
        arraylist.add("(ღ˘⌣˘)♥ ℒ♡ⓥℯ ㄚ♡ⓤ");
        arraylist.add("( ＾◡＾)っ✂❤");
        arraylist.add("웃❤유");
        arraylist.add("┣┓웃┏♨❤♨┑유┏┥");
        arraylist.add("ღ");
        arraylist.add("❥");
    }

    public void insertData4() {
        arraylist.add("|̲̅̅●̲̅̅|̲̅̅=̲̅̅|̲̅̅●̲̅̅|");
        arraylist.add("♪┏(°.°)┛");
        arraylist.add("ιllιlı.");
        arraylist.add("(ﾉ´▽｀)ﾉ♪");
        arraylist.add("☊");
        arraylist.add("♫♪.ılılıll|̲̅̅●̲̅̅|̲̅̅=̲̅̅|̲̅̅●̲̅̅|llılılı.♫♪");
        arraylist.add("[ↂ■■ↂ]");
        arraylist.add("|[●▪▪●]|");
        arraylist.add("{ o }==(::)");
        arraylist.add("c====(=#O| )");
        arraylist.add("ヾ(´ρ｀)〃");
        arraylist.add("இwஇ");
        arraylist.add("（＾Ｏ＾☆♪");
        arraylist.add("(´▽｀)ノ♪");
        arraylist.add("(´△｀)♪");
    }


    public void checkData() {
        gbvEmoticonBinding.includedError.progress.setVisibility(View.GONE);
        if (decorAdapter.getItemCount() > 0) {
            gbvEmoticonBinding.includedError.tvNodata.setVisibility(View.GONE);
        } else
            gbvEmoticonBinding.includedError.tvNodata.setVisibility(View.VISIBLE);
    }


}