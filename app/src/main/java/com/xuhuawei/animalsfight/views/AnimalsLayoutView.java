package com.xuhuawei.animalsfight.views;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.xuhuawei.animalsfight.animals.AnimalsLayoutLayer;
import com.xuhuawei.animalsfight.animals.AnimalsResultBean;
import com.xuhuawei.animalsfight.animals.GameType;
import com.xuhuawei.animalsfight.animals.interfaces.OnFinishAnimalsListener;
import com.xuhuawei.animalsfight.animals.interfaces.OnRedTurnChangeListener;

public class AnimalsLayoutView extends FrameLayout {
    private GridLineView mGridLineView;
    private AnimalsLayoutLayer mAnimalsLayoutLayer;
    private FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
    private OnRedTurnChangeListener onRedTurnChangeListener;
    private OnFinishAnimalsListener onMyFinishAnimalsListener;
    private GameType gameType=GameType.PC;

    private boolean isSelfOperate=false;//当前自己是否能操作
    public void setSelfOperate(boolean selfOperate) {
        isSelfOperate = selfOperate;
    }
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

    public void setOnRedTurnChangeListener(OnRedTurnChangeListener listener){
        this.onRedTurnChangeListener =listener;
    }
    public void setOnFinishAnimalsListener(OnFinishAnimalsListener listener){
        this.onMyFinishAnimalsListener=listener;
    }

    private void addLinesLayer() {
        mGridLineView = new GridLineView(getContext());
        addView(mGridLineView, layoutParams);
    }
    private void addAnimalsLayer() {
        mAnimalsLayoutLayer = new AnimalsLayoutLayer(getContext());
        mAnimalsLayoutLayer.setOnRedTurnChangeListener(listener);
        mAnimalsLayoutLayer.setOnFinishAnimalsListener(onFinishAnimalsListener);
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
    }


    public void resetGame(){
        mAnimalsLayoutLayer.resetGame();
    }

    private OnRedTurnChangeListener listener=new OnRedTurnChangeListener(){
        @Override
        public void onRedTurnChangeListener(boolean isRedTurn) {
            if (onRedTurnChangeListener !=null){
                onRedTurnChangeListener.onRedTurnChangeListener(isRedTurn);
            }
        }
    };

    private OnFinishAnimalsListener onFinishAnimalsListener=new OnFinishAnimalsListener(){
        @Override
        public void onFinishAnimals(AnimalsResultBean bean) {
            if (onMyFinishAnimalsListener!=null){
                onMyFinishAnimalsListener.onFinishAnimals(bean);
            }
        }
    };

}
