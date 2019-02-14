package com.xuhuawei.animalsfight.animals;

import static com.xuhuawei.animalsfight.animals.AnimalCellBean.MAX_INDEX;
import static com.xuhuawei.animalsfight.animals.AnimalCellBean.MIN_INDEX;

public class Point {
    public int pointX, pointY;

    public Point(int pointX, int pointY) {
        this.pointX = pointX;
        this.pointY = pointY;
    }

    public boolean isValidateCell() {
        if (pointX < 0 || pointY < 0) {
            return false;
        }
        if (pointX >= 4 || pointY >=4) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Point{" +
                "pointX=" + pointX +
                ", pointY=" + pointY +
                '}';
    }
}
