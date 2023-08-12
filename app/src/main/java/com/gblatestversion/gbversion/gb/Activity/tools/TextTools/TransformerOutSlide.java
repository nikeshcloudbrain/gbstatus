package com.gblatestversion.gbversion.gb.Activity.tools.TextTools;

import android.view.View;

import androidx.viewpager.widget.ViewPager;

public class TransformerOutSlide extends BaseTransformer implements ViewPager.PageTransformer {
    private static final float MIN_ALPHA = 0.5f;
    private static final float MIN_SCALE = 0.85f;

    public void onTransform(View view, float f) {
        if (f >= -1.0f || f <= 1.0f) {
            float height = (float) view.getHeight();
            float max = Math.max(MIN_SCALE, 1.0f - Math.abs(f));
            float f2 = 1.0f - max;
            float f3 = (height * f2) / 2.0f;
            float width = (((float) view.getWidth()) * f2) / 2.0f;
            view.setPivotY(height * 0.5f);
            if (f < 0.0f) {
                view.setTranslationX(width - (f3 / 2.0f));
            } else {
                view.setTranslationX((-width) + (f3 / 2.0f));
            }
            view.setScaleX(max);
            view.setScaleY(max);
            view.setAlpha((((max - MIN_SCALE) / 0.14999998f) * 0.5f) + 0.5f);
        }
    }
}
