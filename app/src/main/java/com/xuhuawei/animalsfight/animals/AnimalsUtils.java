package com.xuhuawei.animalsfight.animals;

/**
 * 动物棋的帮助类
 */
public class AnimalsUtils {
    /**
     * 两个棋子是否是挨着的 只有挨着才能走 才能吃
     * @param selectedPoint
     * @param clickPoint
     * @return 两个位置是否是挨着的
     */
    public static boolean isPositionNearby(Point selectedPoint, Point clickPoint) {
        if (selectedPoint.pointY == clickPoint.pointY && Math.abs(selectedPoint.pointX - clickPoint.pointX) == 1) {
            return true;
        } else if (selectedPoint.pointX == clickPoint.pointX && Math.abs(selectedPoint.pointY - clickPoint.pointY) == 1) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 比较两个大小
     * @param selectedBean
     * @param clickBean
     * @return 1是大 -1是小  0是等于
     */
    public static int isBiggerThan(AnimalCellBean selectedBean, AnimalCellBean clickBean) {
        if (selectedBean.index == AnimalCellBean.MIN_INDEX && clickBean.index == AnimalCellBean.MAX_INDEX) {//老鼠吃大象
            return 1;
        } else {
            if (selectedBean.index == AnimalCellBean.MAX_INDEX && clickBean.index == AnimalCellBean.MIN_INDEX) {//老鼠吃大象
                //大象不能吃老鼠
                return -1;
            } else if (selectedBean.index > clickBean.index) {//大吃小
                return 1;
            } else if (selectedBean.index == clickBean.index) {//同归于尽
                return 0;
            }else{
                return -1;
            }
        }
    }
}
