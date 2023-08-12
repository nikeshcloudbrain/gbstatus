package com.gblatestversion.gbversion.gb.module;

import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class EmojiGenrator {
    private List<String> alphabets;
    private ArrayList<String> emojiFormate;

    public EmojiGenrator genrator;

    public EmojiGenrator() {
        addToArray();
    }

    public void addToArray() {
        this.alphabets = new ArrayList();
        this.emojiFormate = new ArrayList<>();
        this.alphabets.addAll(Arrays.asList("A,B,C,D,E,F,G,H,I,J,K,L,M,N,O,P,Q,R,S,T,U,V,W,X,Y,Z".split(",")));
        this.emojiFormate.add("       m\n    m  m\n    m m m\n   m       m\nm         m");
        this.emojiFormate.add("mmmm\nm          m\nmmmm\nm          m\nmmmm");
        this.emojiFormate.add("\n    mmm\n m         m\nm\n m         m\n    mmm\n");
        this.emojiFormate.add("mmmm\nm          m\nm            m\nm          m\nmmmm");
        this.emojiFormate.add("mmmm\nm\nmmm\nm\nmmmm");
        this.emojiFormate.add("mmmm\nm\nmmm\nm\nm");
        this.emojiFormate.add(" mmm\n m\nm       mm\n m        m\n    mmm");
        this.emojiFormate.add("m         m\nm         m\nmmmm\nm         m\nm         m");
        this.emojiFormate.add("mmm\n      m\n      m\n      m\nmmm");
        this.emojiFormate.add("   mmm\n        m\n        m\nm    m\n  mm");
        this.emojiFormate.add("m     m\nm   m\nmm\nm   m\nm     m");
        this.emojiFormate.add("m\nm\nm\nm\nmmmm");
        this.emojiFormate.add("m           m\nm m   m m\nm      m    m\nm           m\nm           m");
        this.emojiFormate.add("m          m\nm m       m\nm    m    m\nm      m m\nm          m");
        this.emojiFormate.add("   mmm\n m        m\nm         m\n m        m\n   mmm");
        this.emojiFormate.add("mmmm\nm         m\nmmmm\nm\nm\n");
        this.emojiFormate.add("   mmm\n m        m\nm    m m\n m       m\n  mmm m");
        this.emojiFormate.add("mmmm\nm         m\nmmmm\nm    m\nm      m\n");
        this.emojiFormate.add("  mmm\n m\n  mmm\n           m\n  mmm");
        this.emojiFormate.add("mmmm\n      m\n      m\n      m\n      m");
        this.emojiFormate.add("m         m\n m        m\n m        m\n  m       m\n   mmm");
        this.emojiFormate.add("\nm        m\n m     m\n   m   m\n     m m\n     m");
        this.emojiFormate.add("m        m        m\n m     m m     m\n  m   m    m   m\n   m m       m m\n     m           m");
        this.emojiFormate.add("m      m\n   m m\n     m\n   m m\nm      m");
        this.emojiFormate.add("m      m\n   m m\n     m\n     m\n     m");
        this.emojiFormate.add("mmmm\n         m\n       m\n   m\nmmmm");
        this.emojiFormate.add("  mm\nm  m\n      m\n  mmm");
        this.emojiFormate.add(" mmm\nm     m\n      m\n   m\n mmm");
        this.emojiFormate.add("mmm\n         m\n    mm\n         m\nmmm");
        this.emojiFormate.add("         m\n    m m\n  m    m\nmmmm\n         m");
        this.emojiFormate.add("mmm\nm\n mmm\n         m\nmmm");
        this.emojiFormate.add(" mmm\nm\nmmm\nm      m\n mmm");
        this.emojiFormate.add("mmm\n        m\n      m\n    m\n   m");
        this.emojiFormate.add(" mmm\nm      m\n mmm\nm      m\n mmm");
        this.emojiFormate.add(" mmm\nm     m\n mmm\n        m\n   mm");
        this.emojiFormate.add("   mmm\n m        m\nm         m\n m        m\n   mmm");
    }

    public String generate(String str, String str2) {
        StringBuilder sb = new StringBuilder(str);
        for (int i = 0; i < str.length(); i++) {
            sb.setCharAt(i, Character.toUpperCase(sb.charAt(i)));
        }
        return geteleleter(sb.toString(), str2);
    }

    private String geteleleter(String str, String str2) {
        StringBuilder sb = new StringBuilder();
        String[] split = str.split("");
        for (int i = 0; i < split.length; i++) {
            if (this.alphabets.contains(split[i])) {
                int indexOf = this.alphabets.indexOf(split[i]);
                sb.append((this.emojiFormate.get(indexOf) + "\n\n").replace("m", str2));
            }
        }
        return sb.toString();
    }

    private String getnew(String str, String str2) {
        String[] split = str.split("");
        StringBuilder sb = new StringBuilder();
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < 26; i++) {
            arrayList.add(this.alphabets.get(i));
        }
        int i2 = 0;
        for (String str3 : split) {
            Log.d("txtlog", str3);
            for (int i3 = 0; i3 < arrayList.size(); i3++) {
                Log.d("emolog", "emo  " + ((String) arrayList.get(i3)));
                if (((String) arrayList.get(i3)).equals(str3)) {
                    i2 = i3;
                }
            }
            sb.append((this.emojiFormate.get(i2) + "\n\n").replace("m", str2));
        }
        return sb.toString();
    }
}
