package com.xuhuawei.animalsfight.animals;

public class AnimalsResultBean {
    public boolean isFinish=false;
    public boolean isRedTurn;
    private int result;//1红方胜利  2、蓝方胜利-1 平均-1
    public String reason;//胜利原因

    public AnimalsResultBean(boolean isRedTurn) {
        this.isRedTurn = isRedTurn;
    }



    public int getResult() {
        return result;
    }

    /**
     * 如果是红色局 结果是正常的，如果是蓝色局 那么结果是相反的
     * @param result
     */
    public void setResult(int result) {
        if (isRedTurn){
            this.result = result;
        }else{
            this.result = result*-1;
        }
    }
    @Override
    public String toString() {
        StringBuilder sb=new StringBuilder();
        if (result==1){
            sb.append("红方胜利!");
        }else if (result==-1){
            sb.append("蓝方胜利!");
        }else{
            sb.append("平局!");
        }
        sb.append(reason);
        return sb.toString();
    }
}
