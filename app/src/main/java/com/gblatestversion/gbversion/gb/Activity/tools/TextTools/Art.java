package com.gblatestversion.gbversion.gb.Activity.tools.TextTools;

import kotlin.jvm.internal.Intrinsics;

public class Art {
    private String end;
    private String start;

    public Art(String str, String str2) {
        Intrinsics.checkNotNullParameter(str, "start");
        Intrinsics.checkNotNullParameter(str2, "end");
        this.start = str;
        this.end = str2;
    }

    public final String getStartArt() {
        return this.start;
    }

    public final String getEndArt() {
        return this.end;
    }
}
