package com.example.quest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{


    Button[] choices = new Button[3];
    TextView console;
    Story story;
    Player dovakin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        choices[0] = findViewById(R.id.ch1);
        choices[1] = findViewById(R.id.ch2);
        choices[2] = findViewById(R.id.ch3);
        for(Button b: choices){
            b.setOnClickListener(this);
        }
        console = findViewById(R.id.Console);
        dovakin = new Player("Вася", "Пупкин");
        story = new Story(this);
        console.setText("============" + story.current_situation.subject + "============\n" + story.current_situation.text);
    }

    @Override
    public void onClick(View v) {
        int choice = 0;
        if(v.getId() == R.id.ch1)
            choice = 1;
        if(v.getId() == R.id.ch2)
            choice = 2;
        if(v.getId() == R.id.ch3)
            choice = 3;
        dovakin.healthBonus = story.current_situation.healthBonus;
        dovakin.damageBonus = story.current_situation.damageBonus;
        dovakin.manaBonus = story.current_situation.manaBonus;
        dovakin.health += dovakin.healthBonus;
        dovakin.damage += dovakin.damageBonus;
        dovakin.mana += dovakin.manaBonus;
        dovakin.cheese += story.current_situation.dCheese;
        if (story.isEnd()) {
            console.setText("==========Конец игры==========");
            return;
        }
        if (dovakin.isDead()) {
            console.setText("==========Тебя убили==========");
            return;
        }
        story.go(choice);
        console.setText("============" + story.current_situation.subject + "============\n" + story.current_situation.text);
    }
}