package com.xuhuawei.animalsfight.animals;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.xuhuawei.animalsfight.utils.MyConst;
import com.xuhuawei.animalsfight.views.GridLineView;

import static com.xuhuawei.animalsfight.utils.MyConst.SUM;

public class AnimalsLayoutLayer extends FrameLayout {
    private AnimalDataMain dataMain;
    private AnimalsCellView []cellViews=new AnimalsCellView[SUM];
    private AnimalsCellView[][] cardsMap = new AnimalsCellView[MyConst.SUM_LINE][MyConst.SUM_LINE];

    private int cellSize;

    public void setCellSize(float cellSize) {
        this.cellSize = (int) cellSize;
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

    private void addCellView(int position,AnimalCellBean bean){

        AnimalsCellView c;
        LinearLayout line;
        LinearLayout.LayoutParams lineLp;

        for (int y = 0; y < MyConst.SUM_LINE; y++) {
            line = new LinearLayout(getContext());
            lineLp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, cellSize);
            addView(line, lineLp);

            for (int x = 0; x < MyConst.SUM_LINE; x++) {
                c = new AnimalsCellView(getContext());
                line.addView(c, cellSize, cellSize);
            }
        }

    }

}
