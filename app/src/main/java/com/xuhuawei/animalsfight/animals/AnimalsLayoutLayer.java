package com.xuhuawei.animalsfight.animals;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.FrameLayout;

import com.xuhuawei.animalsfight.views.GridLineView;

import static com.xuhuawei.animalsfight.utils.MyConst.SUM;

public class AnimalsLayoutLayer extends FrameLayout {
    private AnimalDataMain dataMain;
    private AnimalsCellView []cellViews=new AnimalsCellView[SUM];
    private float cellSize;
    public void setCellSize(float cellSize) {
        this.cellSize = cellSize;
    }

    public AnimalsLayoutLayer(Context context) {
        super(context);
        init();
    }
    public AnimalsLayoutLayer(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }
    public AnimalsLayoutLayer(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }
    private void init() {
        dataMain=new AnimalDataMain();
        dataMain.resetData();
        this.post(new Runnable() {
            @Override
            public void run() {
                invilidateView();
            }
        });
    }
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    private void invilidateView(){
        AnimalCellBean[] dataArray=dataMain.getSortTotalAnimals();
    }
}
