package com.gblatestversion.gbversion.gb.Activity.tools.TextTools;

import android.view.View;

import androidx.viewpager.widget.ViewPager;

public abstract class BaseTransformer implements ViewPager.PageTransformer {
    private float clampPosition(float f) {
        if (f < -1.0f) {
            return -1.0f;
        }
        if (f > 1.0f) {
            return 1.0f;
        }
        return f;
    }

    public static final float min(float f, float f2) {
        return f < f2 ? f2 : f;
    }

    public boolean hideOffscreenPages() {
        return true;
    }

    public boolean isPagingEnabled() {
        return false;
    }

    public void onPostTransform(View view, float f) {
    }

    public abstract void onTransform(View view, float f);

    public void transformPage(View view, float f) {
        float clampPosition = clampPosition(f);
        onPreTransform(view, clampPosition);
        onTransform(view, clampPosition);
        onPostTransform(view, clampPosition);
    }

    public void onPreTransform(View view, float f) {
        float width = (float) view.getWidth();
        float f2 = 0.0f;
        view.setRotationX(0.0f);
        view.setRotationY(0.0f);
        view.setRotation(0.0f);
        view.setScaleX(1.0f);
        view.setScaleY(1.0f);
        view.setPivotX(0.0f);
        view.setPivotY(0.0f);
        view.setTranslationY(0.0f);
        view.setTranslationX(isPagingEnabled() ? 0.0f : (-width) * f);
        if (hideOffscreenPages()) {
            if (f > -1.0f && f < 1.0f) {
                f2 = 1.0f;
            }
            view.setAlpha(f2);
            view.setEnabled(false);
            return;
        }
        view.setEnabled(true);
        view.setAlpha(1.0f);
    }
}
