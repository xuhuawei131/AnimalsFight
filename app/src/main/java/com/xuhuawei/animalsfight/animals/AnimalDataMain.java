package com.xuhuawei.animalsfight.animals;


import com.xuhuawei.animalsfight.utils.MyConst;

import java.util.*;

public class AnimalDataMain {
    private static final int SUM=8;
    private static final int TOTAL_SUM=SUM+SUM;

    private AnimalCellBean[] totalAnimals=new AnimalCellBean[TOTAL_SUM];
    private AnimalCellBean[] sortTotalAnimals=new AnimalCellBean[TOTAL_SUM];
    private AnimalCellBean[][] releaseArray =new AnimalCellBean[MyConst.SUM_LINE][MyConst.SUM_LINE];

    private Map<Integer,AnimalCellBean> leftAnimalsCellMap=new HashMap<>();
    private Random random =new Random();

    public AnimalDataMain() {
        init();
    }

    private void init(){
        totalAnimals[0]=new AnimalCellBean(0,"老鼠");
        totalAnimals[1]=new AnimalCellBean(1,"小猫");
        totalAnimals[2]=new AnimalCellBean(2,"猎狗");
        totalAnimals[3]=new AnimalCellBean(3,"豺狼");
        totalAnimals[4]=new AnimalCellBean(4,"猎豹");
        totalAnimals[5]=new AnimalCellBean(5,"老虎");
        totalAnimals[6]=new AnimalCellBean(6,"狮子");
        totalAnimals[7]=new AnimalCellBean(7,"大象");

        totalAnimals[SUM+0]=new AnimalCellBean(0,"老鼠");
        totalAnimals[SUM+1]=new AnimalCellBean(1,"小猫");
        totalAnimals[SUM+2]=new AnimalCellBean(2,"猎狗");
        totalAnimals[SUM+3]=new AnimalCellBean(3,"豺狼");
        totalAnimals[SUM+4]=new AnimalCellBean(4,"猎豹");
        totalAnimals[SUM+5]=new AnimalCellBean(5,"老虎");
        totalAnimals[SUM+6]=new AnimalCellBean(6,"狮子");
        totalAnimals[SUM+7]=new AnimalCellBean(7,"大象");

        for (int i = 0; i <TOTAL_SUM; i++) {
            if (i<SUM){
                totalAnimals[i].isRed=true;
            }else{
                totalAnimals[i].isRed=false;
            }
            totalAnimals[i].id=i;
        }
    }

    /**
     * 重置状态
     */
    public void resetData(){
        //重置
        for (int i = 0; i <TOTAL_SUM ; i++) {
            sortTotalAnimals[i]=null;
            totalAnimals[i].status=AnimalsCellStatus.COVER;
            leftAnimalsCellMap.put(i,totalAnimals[i]);
        }
        //从新布局
        for (int i = 0; i < TOTAL_SUM; i++) {
            int id=getRandomId();
            AnimalCellBean bean=leftAnimalsCellMap.remove(id);
            if (bean!=null){
                sortTotalAnimals[i]=bean;
            }
        }

        for (int i = 0; i < TOTAL_SUM; i++) {
            int x=i%4;
            int y=i/4;
            releaseArray[y][x]=sortTotalAnimals[i];
        }
    }

    /**
     * 获取随机的位置
     * @return
     */
    private int getRandomId(){
        int id=random.nextInt(TOTAL_SUM);
        if (leftAnimalsCellMap.containsKey(id)){
            return id;
        }else{
            return getRandomId();
        }
    }

    /**
     * 打印所有的卡牌
     */
    public void printAllCell(){
        for (int i = 0; i < TOTAL_SUM; i++) {
            if (i%4==0){
                System.out.println();
            }
            String corlor=sortTotalAnimals[i].isRed?"红":"蓝";
            System.out.print(corlor+"-"+sortTotalAnimals[i].name+"    ");
        }
    }

    public AnimalCellBean[][] getReleseArray() {
        return releaseArray;
    }
    public AnimalCellBean[][] getNewReleseArray() {
        resetData();
        return releaseArray;
    }
}
