package com.xuhuawei.animalsfight.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.xuhuawei.animalsfight.utils.MyConst;

public class GridLineView extends View {
    private Paint paint;  //绘图
    private float cellSize;
    private  float space = 30;   //长宽间隔
    public void setCellSize(float cellSize) {
        this.cellSize = cellSize;
        space=cellSize+ MyConst.VALUES;
        invalidate();
    }

    public GridLineView(Context context) {
        super(context);
        init();
    }

    public GridLineView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public GridLineView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        paint = new Paint();
        paint.setColor(Color.YELLOW);
        paint.setStrokeJoin(Paint.Join.ROUND);
        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setStrokeWidth(3);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
    }

    /**
     * 绘制网格线
     */
    protected void onDraw(Canvas canvas){
        canvas.drawColor(Color.BLUE);

        float widht=getWidth()-cellSize;
        float height=getHeight()-cellSize;

        float vertz =  (cellSize/2);
        float hortz =  (cellSize/2);
        for(int i=0;i<4;i++){
            //画横线
            canvas.drawLine(0,  vertz,  widht, vertz, paint);
            //画竖线
            canvas.drawLine(hortz, 0, hortz, height, paint);

            vertz+=space;
            hortz+=space;
        }
    }
}
