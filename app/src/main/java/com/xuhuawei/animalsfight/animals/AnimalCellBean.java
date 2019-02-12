package com.xuhuawei.animalsfight.animals;

public class AnimalCellBean {
    public int index;
    public String name;
    public boolean isRed;//是否是红方
    public boolean isOpen=false;//是否已经翻开
    public int id;
    public boolean isSelected=false;//是否点击选中

    public AnimalCellBean(int index, String name) {
        this.index = index;
        this.name = name;
    }
}
