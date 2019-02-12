package com.xuhuawei.animalsfight.views.ratios;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;


/**
 * Created by xuhuawei on 2017/12/29.
 */

public class ScaleMarginLinearLayout extends LinearLayout {

    private ScaleViewHelper mRatioViewHelper;

    public ScaleMarginLinearLayout(Context context) {
        this(context,null);
    }

    public ScaleMarginLinearLayout(Context context, AttributeSet attrs) {
        this(context, attrs,0);

    }

    public ScaleMarginLinearLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mRatioViewHelper = new ScaleViewHelper(this, attrs);
    }
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        setMeasuredDimension(getDefaultSize(0, widthMeasureSpec),
//                getDefaultSize(0, heightMeasureSpec));

        int[] value = mRatioViewHelper.onMeasure(widthMeasureSpec, heightMeasureSpec);
        widthMeasureSpec = value[0];
        heightMeasureSpec = value[1];
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}
