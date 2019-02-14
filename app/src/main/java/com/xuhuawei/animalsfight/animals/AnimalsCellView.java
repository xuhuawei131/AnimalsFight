package com.xuhuawei.animalsfight.animals;

import android.content.Context;
import android.graphics.Typeface;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.xuhuawei.animalsfight.R;

public class AnimalsCellView extends TextView {
    public int x,y;

    private AnimalCellBean bean;

    private OnCellItemClickListener onCellItemClickListener;
    public AnimalsCellView(Context context) {
        super(context);
        init();
    }

    public AnimalsCellView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }
    public AnimalsCellView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }
    private void init() {
        setTextSize(20);
        setTypeface(Typeface.DEFAULT_BOLD);
        setGravity(Gravity.CENTER);
        setOnClickListener(listener);
    }

    public void setAnimalCellBean(AnimalCellBean bean) {
        this.bean=bean;
        invilidateData();
    }

    public void invilidateData(){
        if (bean == null) {
            setText("");
            setBackgroundResource(R.color.touming);
        } else {

            if (bean.status==AnimalsCellStatus.COVER){
                setText("关闭");
                setTextColor(getResources().getColor(R.color.white));
                setBackgroundResource(R.color.black);
            }else{
                setText(bean.name);
                setTextColor(getResources().getColor(R.color.white));

                if (bean.status==AnimalsCellStatus.UNSELECTED){
                    if (bean.isRed) {
                        setBackgroundResource(R.color.red_fail);
                    } else {
                        setBackgroundResource(R.color.blue_default);
                    }
                }else {
                    if (bean.isRed) {
                        setBackgroundResource(R.drawable.bg_red_cell_select);
                    } else {
                        setBackgroundResource(R.drawable.bg_blue_cell_select);
                    }
                }
            }
        }
    }
    public void setCellViewUnSelected(){
        bean.status=AnimalsCellStatus.UNSELECTED;
        invilidateData();
    }
    public void setCellViewSelected(){
        bean.status=AnimalsCellStatus.SELECTED;
        invilidateData();
    }

    private OnClickListener listener=new OnClickListener() {
        @Override
        public void onClick(View v) {
            if (bean==null) {
                if (onCellItemClickListener !=null){
                    onCellItemClickListener.onCellEmptyItemClickListener(new Point(x,y));
                }
            }else if (bean.status==AnimalsCellStatus.UNSELECTED){
                if (onCellItemClickListener !=null){
                    onCellItemClickListener.onCellAttackItemClickListener(new Point(x,y));
                }
            }else if (bean.status==AnimalsCellStatus.COVER) {
                if (onCellItemClickListener !=null){
                    onCellItemClickListener.onCellOpenItemClickListener(new Point(x,y));
                }
            }else if(bean.status==AnimalsCellStatus.SELECTED){
                bean.status=AnimalsCellStatus.UNSELECTED;
                invilidateData();
            }
        }
    };
    public interface OnCellItemClickListener {
        public void onCellEmptyItemClickListener(Point point);
        public void onCellAttackItemClickListener(Point point);
        public void onCellOpenItemClickListener(Point point);
    }
    public void setOnCellItemClickListener(OnCellItemClickListener listener){
        onCellItemClickListener =listener;
    }
}
