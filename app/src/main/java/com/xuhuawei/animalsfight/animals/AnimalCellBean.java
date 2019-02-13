package com.xuhuawei.animalsfight.animals;

public class AnimalCellBean {
    public static final int MIN_INDEX=0;
    public static final int MAX_INDEX=7;
    public int index;
    public String name;
    public boolean isRed;//是否是红方
    public int id;
    public AnimalsCellStatus status=AnimalsCellStatus.COVER;

    public Point currentPoint;

    public AnimalCellBean(int index, String name) {
        this.index = index;
        this.name = name;
    }
    public void setCurrentPoint(int x,int y){
        setCurrentPoint(new Point(x,y));
    }
    public void setCurrentPoint(Point point){
        currentPoint=point;
    }
}
