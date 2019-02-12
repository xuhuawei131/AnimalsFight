package com.xuhuawei.animalsfight.views.ratios;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.xuhuawei.animalsfight.R;


public class ScaleViewHelper {

    private float widthRatio = -1;
    private float heightRatio = -1;

    private float dreamWdithLength;
    private float dreamHeightLength;

    private float leftMargin = -1;
    private float topMargin = -1;
    private float rightMargin = -1;
    private float bottomMargin = -1;

    private float scaleWidth;
    private float scaleHeight;

    private int childWidthSize;
    private int childHeightSize;

    private View view;
    public ScaleViewHelper(View view, AttributeSet attrs){
        this.view=view;
        Context context=view.getContext();
;        Resources resources = context.getResources();
        DisplayMetrics dm = resources.getDisplayMetrics();
        int width = dm.widthPixels;
        int height = dm.heightPixels;

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.RatioMarginLayout);//TypedArray是一个数组容器
        widthRatio = typedArray.getFloat(R.styleable.RatioMarginLayout_widthDesign, 1);
        heightRatio = typedArray.getFloat(R.styleable.RatioMarginLayout_heightDesign, 1);

        dreamWdithLength = typedArray.getFloat(R.styleable.RatioMarginLayout_DesignScreenWidth, 1);
        dreamHeightLength = typedArray.getFloat(R.styleable.RatioMarginLayout_DesignScreenHeight, 1);

        leftMargin = typedArray.getFloat(R.styleable.RatioMarginLayout_leftMarginDesign, 0);
        topMargin = typedArray.getFloat(R.styleable.RatioMarginLayout_topMarginDesign, 0);
        rightMargin = typedArray.getFloat(R.styleable.RatioMarginLayout_rightMarginDesign, 0);
        bottomMargin = typedArray.getFloat(R.styleable.RatioMarginLayout_bottomMarginDesign, 0);
        typedArray.recycle();

        scaleWidth = width / dreamWdithLength;
        scaleHeight = height / dreamHeightLength;

        leftMargin = scaleWidth * leftMargin;
        rightMargin = scaleWidth * rightMargin;

        topMargin = scaleHeight * topMargin;
        bottomMargin = scaleHeight * bottomMargin;

        childWidthSize= (int) (scaleWidth * widthRatio);
        childHeightSize = (int) (scaleHeight * heightRatio);
        Log.e("xhw","height="+height+" heightRatio ="+heightRatio+" dreamHeightLength="+dreamHeightLength+" scaleHeight="+scaleHeight+" childHeightSize="+childHeightSize+" topMargin="+topMargin);

    }

    public int[] onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int measureSpec[]=new int[]{widthMeasureSpec,heightMeasureSpec};
        ViewGroup.MarginLayoutParams lp = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        if (lp != null) {
            if (leftMargin > 0) {
                lp.leftMargin = (int) leftMargin;
            }
            if (topMargin > 0) {
                lp.topMargin = (int) topMargin;
            }
            if (rightMargin > 0) {
                lp.rightMargin = (int) rightMargin;
            }
            if (bottomMargin > 0) {
                lp.bottomMargin = (int) bottomMargin;
            }
            view.setLayoutParams(lp);
        }

        if (widthRatio >= 1) {
            measureSpec[0] = View.MeasureSpec.makeMeasureSpec(
                    childWidthSize, View.MeasureSpec.EXACTLY);
        }
        if (heightRatio >= 1) {
            measureSpec[1] = View.MeasureSpec.makeMeasureSpec(
                    childHeightSize, View.MeasureSpec.EXACTLY);
        }
        return measureSpec;
    }
}
