package com.xuhuawei.animalsfight.animals;

public class AnimalsResultBean {
    public boolean isFinish=false;
    public int result;//1红方胜利  2、蓝方胜利-1 平均-1
    public String reason;//胜利原因

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
