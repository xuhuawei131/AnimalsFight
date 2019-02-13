package com.xuhuawei.animalsfight.animals;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.xuhuawei.animalsfight.utils.MyConst;

public class AnimalsLayoutLayer extends LinearLayout {
    private AnimalDataMain dataMain;
    private AnimalsCellView[][] viewArray = new AnimalsCellView[MyConst.SUM_LINE][MyConst.SUM_LINE];
    private AnimalCellBean[][] dataArray;
    private AnimalsResultHelper resultHelper=new AnimalsResultHelper();
    private boolean isRedTurn;
    public AnimalsLayoutLayer(Context context) {
        super(context);
        init();
    }

    public AnimalsLayoutLayer(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public AnimalsLayoutLayer(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }


    public void setRedTurn(boolean isRedTurn){
        this.isRedTurn=isRedTurn;
    }

    private void init() {
        setOrientation(VERTICAL);

        dataMain = new AnimalDataMain();
        dataMain.resetData();
        invilidateView();
        setData();
    }

    /**
     * 初始化view
     */
    private void invilidateView() {
        for (int y = 0; y < MyConst.SUM_LINE; y++) {
            LinearLayout line = new LinearLayout(getContext());
            LinearLayout.LayoutParams lineLp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            int topMargin = 0;
            if (y != 0) {
                topMargin = (int) MyConst.VALUES;
            }
            lineLp.topMargin = topMargin;
            addView(line, lineLp);

            for (int x = 0; x < MyConst.SUM_LINE; x++) {
                AnimalsCellView cellView = new AnimalsCellView(getContext());
                cellView.setOnCellItemClickListener(onCellItemClickListener);
                line.addView(cellView);
                cellView.x=x;
                cellView.y=y;
                viewArray[y][x] = cellView;
            }
        }
    }

    public void setCellSize(float cellSize) {
        for (int y = 0; y < MyConst.SUM_LINE; y++) {
            for (int x = 0; x < MyConst.SUM_LINE; x++) {
                AnimalsCellView view = viewArray[y][x];
                LinearLayout.LayoutParams cellLp = (LayoutParams) view.getLayoutParams();
                cellLp.width = (int) cellSize;
                cellLp.height = (int) cellSize;

                float marginLeft = 0;
                if (x != 0) {
                    marginLeft = (MyConst.VALUES);
                }
                cellLp.leftMargin = (int) marginLeft;
                view.setLayoutParams(cellLp);
            }
        }
    }

    private void setData() {
        this.post(new Runnable() {
            @Override
            public void run() {
                dataArray= dataMain.getNewReleseArray();
                resultHelper.setDataArray(dataArray);
                for (int y = 0; y < MyConst.SUM_LINE; y++) {
                    for (int x = 0; x < MyConst.SUM_LINE; x++) {
                        viewArray[y][x].setAnimalCellBean(dataArray[y][x]);
                    }
                }
            }
        });
    }

    private void checkResult(){
        AnimalsResultBean bean= resultHelper.checkResult();
        if (bean.isFinish){
            Toast.makeText(getContext(),bean.toString(),Toast.LENGTH_LONG).show();
        }else{

        }
    }

    /**
     * 两个棋子是否是挨着的
     * @param selectedPoint
     * @param clickPoint
     * @return
     */
    public boolean isPositionNearby(Point selectedPoint,Point clickPoint){
        if (selectedPoint.pointY==clickPoint.pointY&&Math.abs(selectedPoint.pointX-clickPoint.pointX)==1){
            return true;
        }else if (selectedPoint.pointX==clickPoint.pointX&&Math.abs(selectedPoint.pointY-clickPoint.pointY)==1){
            return true;
        }else{
            return false;
        }
    }

    /**
     * 走路
     * @param selectedPoint
     * @param clickPoint
     */
    private void walkRoad(Point selectedPoint,Point clickPoint){
        if (isPositionNearby(selectedPoint,clickPoint)){
            isRedTurn=!isRedTurn;
            AnimalCellBean bean=dataArray[selectedPoint.pointY][selectedPoint.pointX];

            dataArray[selectedPoint.pointY][selectedPoint.pointX]=null;
            viewArray[selectedPoint.pointY][selectedPoint.pointX].setAnimalCellBean(null);

            bean.status=AnimalsCellStatus.UNSELECTED;
            dataArray[clickPoint.pointY][clickPoint.pointX]=bean;
            bean.setCurrentPoint(clickPoint);

            viewArray[clickPoint.pointY][clickPoint.pointX].setAnimalCellBean(bean);
            checkResult();
        }
    }


    /**
     * 吃动物
     * @param selectedPoint
     * @param clickPoint
     */
    private void eatAnimals(Point selectedPoint,Point clickPoint){
            walkRoad(selectedPoint,clickPoint);
    }

    /**
     * 同归于尽
     * @param selectedPoint
     * @param clickPoint
     */
    private void killTogether(Point selectedPoint,Point clickPoint){
        if (isPositionNearby(selectedPoint,clickPoint)){
            isRedTurn=!isRedTurn;
            dataArray[selectedPoint.pointY][selectedPoint.pointX]=null;
            viewArray[selectedPoint.pointY][selectedPoint.pointX].setAnimalCellBean(null);

            dataArray[clickPoint.pointY][clickPoint.pointX]=null;
            viewArray[clickPoint.pointY][clickPoint.pointX].setAnimalCellBean(null);
        }
    }

    /**
     * 获取选中动物站点
     * @return
     */
    private Point getSelectedPoint(){
        int selectPointX=-1;
        int selectPointY=-1;
        for (int y = 0; y < MyConst.SUM_LINE; y++) {
            for (int x = 0; x < MyConst.SUM_LINE; x++) {
                if (dataArray[y][x]!=null){//站点 有动物
                    if (dataArray[y][x].status==AnimalsCellStatus.SELECTED){
                        selectPointX=x;
                        selectPointY=y;
                        break;
                    }
                }
            }
        }
        if (selectPointX==-1){
            return null;
        }else{
            return new Point(selectPointX,selectPointY);
        }
    }

    /**
     * 点击的
     * @param point
     * @return
     */
    private boolean clickPointInColcor(Point point){
        AnimalCellBean bean=dataArray[point.pointY][point.pointX];
        if (bean.isRed==isRedTurn){
            return true;
        }else{
            return false;
        }

    }

    private AnimalsCellView.OnCellItemClickListener onCellItemClickListener =new AnimalsCellView.OnCellItemClickListener() {
        @Override
        public void onCellEmptyItemClickListener(Point point) {
            Point selectedPoint =getSelectedPoint();
            //有被选中的动物
            if (selectedPoint!=null){
                walkRoad(selectedPoint,point);
            }
        }
        @Override
        public void onCellAttackItemClickListener(Point clickPoint) {
            Point selectedPoint =getSelectedPoint();
            //没有被选中的动物
            if (selectedPoint==null){
                if (clickPointInColcor(clickPoint)){
                    viewArray[clickPoint.pointY][clickPoint.pointX].setCellViewSelected();
                }
            }else{//有被选中的动物
                int selectPointX=selectedPoint.pointX;
                int selectPointY=selectedPoint.pointY;

                AnimalCellBean selectedBean=dataArray[selectPointY][selectPointX];
                AnimalCellBean clickBean=dataArray[clickPoint.pointY][clickPoint.pointX];

                if (clickBean!=null){//如果有动物


                    if (selectedBean.isRed!=clickBean.isRed){ //如果是不同颜色的
                        if (selectedBean.index==AnimalCellBean.MIN_INDEX&&clickBean.index==AnimalCellBean.MAX_INDEX){//老鼠吃大象
                            eatAnimals(new Point(selectPointX,selectPointY),clickPoint);
                        }else{

                            if (selectedBean.index==AnimalCellBean.MAX_INDEX&&clickBean.index==AnimalCellBean.MIN_INDEX){//老鼠吃大象
                                //大象不能吃老鼠
                            }else if (selectedBean.index>clickBean.index){//大吃小
                                eatAnimals(new Point(selectPointX,selectPointY),clickPoint);
                            }else if (selectedBean.index==clickBean.index){//同归于尽
                                killTogether(new Point(selectPointX,selectPointY),clickPoint);
                            }
                        }
                    }else{//如果选中的和 点击的是相同颜色 那么转移焦点
                        viewArray[selectedPoint.pointY][selectedPoint.pointX].setCellViewUnSelected();
                        viewArray[clickPoint.pointY][clickPoint.pointX].setCellViewSelected();
                    }
                }
            }

        }
        @Override
        public void onCellOpenItemClickListener(Point point) {
            Point selectedPoint =getSelectedPoint();
            if (selectedPoint!=null){
                viewArray[selectedPoint.pointY][selectedPoint.pointX].setCellViewUnSelected();
            }
            viewArray[point.pointY][point.pointX].setCellViewUnSelected();
            checkResult();
        }
    };
}
