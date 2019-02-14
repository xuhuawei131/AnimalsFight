package com.xuhuawei.animalsfight.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.xuhuawei.animalsfight.utils.MyConst;

/**
 * 网格视图
 */
public class GridLineView extends View {
    private Paint paint;  //绘图
    private float halfCellSize;
    private  float space = 30;   //长宽间隔
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
        paint.setColor(Color.BLACK);
        paint.setStrokeJoin(Paint.Join.ROUND);
        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setStrokeWidth(10);
    }
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int height = getMeasuredHeight();
        int width = getMeasuredWidth();
        float cellWidth = (width - (MyConst.SUM_LINE - 1) * MyConst.VALUES) / MyConst.SUM_LINE;
        setCellSize(cellWidth);
    }
    public void setCellSize(float cellSize) {
        this.halfCellSize = cellSize/2;
        space=cellSize+ MyConst.VALUES;
        invalidate();
    }

    /**
     * 绘制网格线
     */
    protected void onDraw(Canvas canvas){
//        canvas.drawColor(Color.BLUE);
        float targetX=getWidth()-halfCellSize;
        float targetY=getHeight()-halfCellSize;

        float vertz =  halfCellSize;
        float hortz =  halfCellSize;
        for(int i=0;i<4;i++){
            //画横线
            canvas.drawLine(halfCellSize,  vertz,  targetX, vertz, paint);
            //画竖线
            canvas.drawLine(hortz, halfCellSize, hortz, targetY, paint);
            vertz+=space;
            hortz+=space;
        }
    }
}
