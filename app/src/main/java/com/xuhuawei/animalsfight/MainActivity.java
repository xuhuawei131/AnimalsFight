package com.xuhuawei.animalsfight;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.xuhuawei.animalsfight.animals.AnimalsResultBean;
import com.xuhuawei.animalsfight.animals.interfaces.OnFinishAnimalsListener;
import com.xuhuawei.animalsfight.animals.interfaces.OnRedTurnChangeListener;
import com.xuhuawei.animalsfight.views.AnimalsLayoutView;

public class MainActivity extends AppCompatActivity {
    private TextView text_turn;
    private TextView btn_reset;
    private AnimalsLayoutView view_animals;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        text_turn=findViewById(R.id.text_turn);
        btn_reset=findViewById(R.id.btn_reset);
        btn_reset.setOnClickListener(onClickListener);

        view_animals=findViewById(R.id.view_animals);
        view_animals.setOnRedTurnChangeListener(onRedTurnChangeListener);
        view_animals.setOnFinishAnimalsListener(onFinishAnimalsListener);
    }

    private View.OnClickListener onClickListener=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            view_animals.resetGame();
        }
    };

    private OnRedTurnChangeListener onRedTurnChangeListener=new OnRedTurnChangeListener(){
        @Override
        public void onRedTurnChangeListener(boolean isRedTurn) {
            text_turn.setText(isRedTurn?"该红方走":"该蓝方走");
        }
    };
    private OnFinishAnimalsListener onFinishAnimalsListener=new OnFinishAnimalsListener() {
        @Override
        public void onFinishAnimals(AnimalsResultBean bean) {
            text_turn.setText(bean.toString());
            Toast.makeText(MainActivity.this,bean.toString(),Toast.LENGTH_LONG).show();
        }
    };
}
