package com.xuhuawei.animalsfight.animals;

public class AnimalsResultHelper {
    private AnimalCellBean[][] dataArray;
    public void setDataArray(AnimalCellBean[][] dataArray){
        this.dataArray=dataArray;
    }

    public  AnimalsResultBean checkResult(){
        AnimalsResultBean bean=new AnimalsResultBean();

        return bean;
    }

    /**
     * 是否仅剩红蓝两个 那么比较级别高低
     * @return
     */
    private boolean isOnlyOne(){
        return false;
    }

    /**
     * 是否全是一个颜色
     * @return
     */
    private boolean isOnlyRedOrBlue(boolean isRed){
        for (int i=0;i<dataArray.length;i++){
            for (int j = 0; j < dataArray[i].length; j++) {

            }
        }
        return false;
    }
    /**
     * 是否还能移动
     * @param isRed
     * @return
     */
    private boolean isEnableMove(boolean isRed){
        for (int i=0;i<dataArray.length;i++){
            for (int j = 0; j < dataArray[i].length; j++) {

            }
        }
        return false;
    }
}
