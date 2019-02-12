package com.xuhuawei.animalsfight.views.ratios;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;


/**
 * Created by xuhuawei on 2017/12/29.
 */

public class ScaleMarginRelativeLayout extends RelativeLayout {

    private ScaleViewHelper mRatioViewHelper;

    public ScaleMarginRelativeLayout(Context context) {
        this(context,null);
    }

    public ScaleMarginRelativeLayout(Context context, AttributeSet attrs) {
        this(context, attrs,0);

    }

    public ScaleMarginRelativeLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mRatioViewHelper = new ScaleViewHelper(this, attrs);
    }
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int[] value = mRatioViewHelper.onMeasure(widthMeasureSpec, heightMeasureSpec);
        widthMeasureSpec = value[0];
        heightMeasureSpec = value[1];
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}
