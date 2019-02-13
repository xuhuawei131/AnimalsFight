package com.xuhuawei.animalsfight.views;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.xuhuawei.animalsfight.animals.AnimalCellBean;
import com.xuhuawei.animalsfight.animals.AnimalDataMain;
import com.xuhuawei.animalsfight.animals.AnimalsCellView;
import com.xuhuawei.animalsfight.animals.AnimalsLayoutLayer;
import com.xuhuawei.animalsfight.utils.MyConst;

public class AnimalsLayoutView extends FrameLayout {
    private GridLineView mGridLineView;
    private AnimalsLayoutLayer mAnimalsLayoutLayer;

    private FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

    public AnimalsLayoutView(Context context) {
        super(context);
        init();
    }

    public AnimalsLayoutView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public AnimalsLayoutView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        addLinesLayer();
        addAnimalsLayer();
    }

    private void addLinesLayer() {
        mGridLineView = new GridLineView(getContext());
        addView(mGridLineView, layoutParams);
    }

    public AnimalsLayoutLayer getmAnimalsLayoutLayer() {
        return mAnimalsLayoutLayer;
    }

    public void setmAnimalsLayoutLayer(AnimalsLayoutLayer mAnimalsLayoutLayer) {
        this.mAnimalsLayoutLayer = mAnimalsLayoutLayer;
    }

    public void addAnimalsLayer() {
        mAnimalsLayoutLayer = new AnimalsLayoutLayer(getContext());
        addView(mAnimalsLayoutLayer, layoutParams);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int height = getMeasuredHeight();
        int width = getMeasuredWidth();
        int cellSize;
        if (width < height) {
            cellSize=width;
        }else{
            cellSize=height;
        }
        int measureSpec[] = new int[2];
        measureSpec[0] = View.MeasureSpec.makeMeasureSpec(
                cellSize, View.MeasureSpec.EXACTLY);
        measureSpec[1] = View.MeasureSpec.makeMeasureSpec(
                cellSize, View.MeasureSpec.EXACTLY);
        super.onMeasure(measureSpec[0], measureSpec[0]);

        float cellWidth = (width - (MyConst.SUM_LINE - 1) * MyConst.VALUES) / MyConst.SUM_LINE;
        mGridLineView.setCellSize(cellWidth);
        mAnimalsLayoutLayer.setCellSize(cellWidth);
    }
}
