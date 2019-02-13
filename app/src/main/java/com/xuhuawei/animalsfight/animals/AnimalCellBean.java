package com.xuhuawei.animalsfight.animals;

public class AnimalCellBean {
    public static final int MIN_INDEX=0;
    public static final int MAX_INDEX=7;
    public int index;
    public String name;
    public boolean isRed;//是否是红方
    public int id;
    public AnimalsCellStatus status=AnimalsCellStatus.COVER;

    public AnimalCellBean(int index, String name) {
        this.index = index;
        this.name = name;
    }
}
