package com.gblatestversion.gbversion.gb.Activity.tools.TextTools;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;

import androidx.appcompat.widget.AppCompatTextView;

public class TabView extends AppCompatTextView {
    public float defaultSize;
    private ScaleGestureDetector mScaleDetector;
    public float mScaleFactor = 1.0f;
    public float zoomLimit = 2.0f;

    private class ScaleListener extends ScaleGestureDetector.SimpleOnScaleGestureListener {
        private ScaleListener() {
        }

        public boolean onScale(ScaleGestureDetector scaleGestureDetector) {
            TabView.this.mScaleFactor *= scaleGestureDetector.getScaleFactor();
            TabView tabView = TabView.this;
            tabView.mScaleFactor = Math.max(1.0f, Math.min(tabView.mScaleFactor, TabView.this.zoomLimit));
            TabView tabView2 = TabView.this;
            tabView2.setTextSize(0, tabView2.defaultSize * TabView.this.mScaleFactor);
            TabView.this.setGravity(80);
            return true;
        }
    }

    public TabView(Context context) {
        super(context);
        initialize();
    }

    public TabView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        initialize();
    }

    public TabView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        initialize();
    }

    private void initialize() {
        defaultSize = getTextSize();
        mScaleDetector = new ScaleGestureDetector(getContext(), new ScaleListener());
    }

    public void setZoomLimit(float f) {
        this.zoomLimit = f;
    }

    public void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        super.onTouchEvent(motionEvent);
        mScaleDetector.onTouchEvent(motionEvent);
        return true;
    }
}
