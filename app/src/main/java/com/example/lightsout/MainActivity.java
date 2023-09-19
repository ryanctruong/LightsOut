package com.example.lightsout;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    public static final int GRID_SIZE = 3;
    private GridLayout grid;
    private boolean[][] cellState;

    TextView countLabel;

    Button resetBut, rand;

    View.OnClickListener buttonClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            int buttonIndex = grid.indexOfChild(view);
            int row = buttonIndex / GRID_SIZE;
            int col = buttonIndex % GRID_SIZE;

            cellState[row][col] = !cellState[row][col];

            if(cellState[row][col]){
                view.setBackgroundColor(getColor(R.color.blue_500));
            } else{
                view.setBackgroundColor(getColor(R.color.black));
            }
            updateCounterLabel();
        }
    };

    View.OnClickListener resetButton = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            for(int i = 0; i < GRID_SIZE; i++){
                for(int j = 0; j < GRID_SIZE; j++){
                    if(cellState[i][j]){
                        cellState[i][j] = false;
                        recolor();
                    }
                }
            }
            updateCounterLabel();
        }
    };

    View.OnClickListener random = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            randomize();
            recolor();
            updateCounterLabel();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        cellState = new boolean[][]{{true, true, true}, {true, true, true}, {true, true, true}};

        setContentView(R.layout.activity_main);
        grid = findViewById(R.id.light_grid);
        countLabel = findViewById(R.id.scoreCounter);




        for(int i = 0; i < grid.getChildCount(); i++){
            grid.getChildAt(i).setOnClickListener(buttonClick);
        }

        resetBut = findViewById(R.id.button);
        resetBut.setOnClickListener(resetButton);

        rand = findViewById(R.id.random);
        rand.setOnClickListener(random);


        randomize();
        recolor();
        updateCounterLabel();

    }

    public void recolor(){
        for (int i = 0; i < grid.getChildCount(); i++) {
            Button gridButton = (Button) grid.getChildAt(i);

            // Find the button's row and col
            int row = i / GRID_SIZE;
            int col = i % GRID_SIZE;

            if (cellState[row][col]) {
                gridButton.setBackgroundColor(getColor(R.color.blue_500));
            } else {
                gridButton.setBackgroundColor(getColor(R.color.black));
            }
        }
    }

    public void randomize(){
        Random random = new Random();
        for(int i =0; i< GRID_SIZE; i++){
            for(int j =0; j< GRID_SIZE; j++){
                cellState[i][j] = random.nextBoolean();
            }
        }
    }

    public void updateCounterLabel(){
        String text = "Source: " + count();
        countLabel.setText(text);
    }

    public int count(){
        int count = 0;
        for(int i = 0; i < GRID_SIZE; i++){
            for(int j = 0; j < GRID_SIZE; j++){
                if(cellState[i][j]){
                    count++;
                }
            }
        }

        return count;
    }

    public void lightsOff(){

    }
}