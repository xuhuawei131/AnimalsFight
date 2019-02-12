package com.xuhuawei.animalsfight.animals;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xuhuawei.animalsfight.R;

public class AnimalsCellView extends TextView {
    public AnimalsCellView(Context context) {
        super(context);
    }

    public AnimalsCellView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public AnimalsCellView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setData(AnimalCellBean bean) {
        if (bean == null) {
            setVisibility(View.GONE);
        } else {
            if (!bean.isOpen) {
                setText("关闭");
            } else {
                setText(bean.name);

                if (bean.isRed) {
                    setBackgroundResource(R.color.red_fail);
                } else {
                    setBackgroundResource(R.color.blue_default);
                }

                if (bean.isSelected) {
                    setTextColor(getResources().getColor(R.color.black));
                } else {
                    setTextColor(getResources().getColor(R.color.white));
                }
            }
        }
    }
}
