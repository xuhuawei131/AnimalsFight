package com.xuhuawei.animalsfight;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.xuhuawei.animalsfight.views.AnimalsLayoutView;

public class MainActivity extends AppCompatActivity {
    private TextView text_turn;
    private AnimalsLayoutView view_animals;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        text_turn=findViewById(R.id.text_turn);
        view_animals=findViewById(R.id.view_animals);

        view_animals.getmAnimalsLayoutLayer();

    }
}
