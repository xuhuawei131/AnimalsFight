package com.xuhuawei.animalsfight.animals;

import java.util.ArrayList;
import java.util.List;

public class AnimalsResultHelper {
    private AnimalCellBean[][] dataArray;

    public void setDataArray(AnimalCellBean[][] dataArray) {
        this.dataArray = dataArray;
    }

    /**
     * 检查结果
     *
     * @param isRedTurn 下一步 是不是红方走
     * @return
     */
    public AnimalsResultBean checkResult(boolean isRedTurn) {
        AnimalsResultBean bean = new AnimalsResultBean(isRedTurn);

        ScanResult redresult = checkResultByTurn(isRedTurn);
        ScanResult blueresult = checkResultByTurn(!isRedTurn);

        if (redresult.turnList.size() == 0) {
            bean.setResult(-1);
            bean.reason = "被吃光了";
            bean.isFinish = true;
        } else if (redresult.turnList.size() == 1) {//剩余一个棋子
            Point redPoint = redresult.turnList.get(0);
            //如果蓝方也剩余一个 那么就直接比大小
            if (blueresult.turnList.size() == 1) {
                Point bluePoint = blueresult.turnList.get(0);
                AnimalCellBean redCell = dataArray[redPoint.pointY][redPoint.pointX];
                AnimalCellBean blueCell = dataArray[bluePoint.pointY][bluePoint.pointX];

                bean.setResult(AnimalsUtils.isBiggerThan(redCell, blueCell));
                bean.reason = "剩余棋子比较大";
                bean.isFinish = true;
            }else{//如果蓝方剩余好几个 那么看看能不能给他憋死了
                if (!isHasNotCover()){
                    boolean redCanMove = false;
                    if (pointCanMove(redPoint)) {
                        redCanMove = true;
                    }
                    if (!redCanMove) {
                        bean.setResult(-1);
                        bean.reason = "棋子无法动弹";
                        bean.isFinish = true;
                    }
                }
            }

        } else {//红方有大于1个棋子了 如果都被憋死了
            if (!isHasNotCover()){
                boolean redCanMove = false;
                for (Point point : redresult.turnList) {
                    if (pointCanMove(point)) {
                        redCanMove = true;
                        break;
                    }
                }
                if (!redCanMove) {
                    bean.setResult(-1);
                    bean.reason = "棋子无法动弹";
                    bean.isFinish = true;
                }
            }
        }
        return bean;
    }


    /**
     * 检查某个点是否还能走
     *
     * @param point
     * @return
     */
    private boolean pointCanMove(Point point) {
        Point pointTop = new Point(point.pointX, point.pointY - 1);
        Point pointLeft = new Point(point.pointX - 1, point.pointY);
        Point pointRight = new Point(point.pointX + 1, point.pointY);
        Point pointBottom = new Point(point.pointX, point.pointY + 1);

        if (isCanEatOrWalk(point, pointTop)) {
            return true;
        } else if (isCanEatOrWalk(point, pointLeft)) {
            return true;
        } else if (isCanEatOrWalk(point, pointRight)) {
            return true;
        } else if (isCanEatOrWalk(point, pointBottom)) {
            return true;
        }
        return false;

    }

    /**
     * 是否还有没有翻开的卡牌
     * @return
     */
    private boolean isHasNotCover() {
        for (int i = 0; i < dataArray.length; i++) {
            for (int j = 0; j < dataArray[i].length; j++) {
                if (dataArray[i][j] != null&&dataArray[i][j].status==AnimalsCellStatus.COVER) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 是否是能走或者能吃
     * @param fromPoint
     * @param toPoint
     * @return
     */
    private boolean isCanEatOrWalk(Point fromPoint, Point toPoint) {
        if (fromPoint.isValidateCell() && toPoint.isValidateCell()) {
            AnimalCellBean fromBean = dataArray[fromPoint.pointY][fromPoint.pointX];
            AnimalCellBean toBean = dataArray[toPoint.pointY][toPoint.pointX];
            if (fromBean == null) {
                return false;
            } else if (toBean == null) {
                return true;
            } else {
                if (toBean.status != AnimalsCellStatus.COVER) {
                    if (fromBean.isRed == toBean.isRed) {//相同颜色也不能走
                        return false;
                    } else {
                        //比较两个大小
                        if (AnimalsUtils.isBiggerThan(fromBean, toBean) > -1) {
                            return true;
                        } else {
                            return false;
                        }
                    }
                } else {//如果是覆盖的也不能走
                    return false;
                }
            }
        } else {
            return false;
        }
    }

    /**
     * 检查某种颜色的结果
     * @param isRedTurn
     * @return
     */
    private ScanResult checkResultByTurn(boolean isRedTurn) {
        List<Point> turnList = new ArrayList<>(0);
        Point maxPoint = null;
        int maxIndex = -1;
        for (int i = 0; i < dataArray.length; i++) {
            for (int j = 0; j < dataArray[i].length; j++) {
                if (dataArray[i][j] != null) {
                    if (isRedTurn == dataArray[i][j].isRed) {
                        Point point = new Point(j, i);
                        turnList.add(point);
                        if (dataArray[i][j].index > maxIndex) {
                            maxIndex = dataArray[i][j].index;
                            maxPoint = point;
                        }
                    }
                }
            }
        }
        ScanResult result = new ScanResult();
        result.turnList = turnList;
        result.maxPoint = maxPoint;
        result.isRedTurn = isRedTurn;
        return result;
    }

    static class ScanResult {
        List<Point> turnList = new ArrayList<>();
        Point maxPoint = null;
        boolean isRedTurn;
    }
}
