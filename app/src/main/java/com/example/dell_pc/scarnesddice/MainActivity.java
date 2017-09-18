package com.example.dell_pc.scarnesddice;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;


public class MainActivity extends AppCompatActivity {
    TextView yourScore;
    TextView computerScore;
    TextView turnScore;
    ImageView Image;
    Button roll;
    Button hold;
    Button reset;
    boolean isPlayerTurn;
    int img;
    int YourScore;
    int ComputerScore;
    int TurnScore;
    int image[]={R.drawable.dice1,R.drawable.dice2,R.drawable.dice3,R.drawable.dice4,R.drawable.dice5,R.drawable.dice6};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        yourScore = (TextView) findViewById(R.id.textView);
        computerScore = (TextView) findViewById(R.id.textView2);
        turnScore = (TextView) findViewById(R.id.textView3);
        Image = (ImageView) findViewById(R.id.imageView);
        roll = (Button) findViewById(R.id.roll);
        hold = (Button) findViewById(R.id.hold);
        reset = (Button) findViewById(R.id.reset);
    }
        public void Roll(View v){
         img= new Random().nextInt(6);
      /*  switch(img){
            case 0:
                Image.setImageResource(R.drawable.dice1);
            case 1:                                                       this switch can be replased by that array and line 1
                Image.setImageResource(R.drawable.dice2);
            case 2:
                Image.setImageResource(R.drawable.dice2);



        }
*/
               Image.setImageResource(image[img]);                     //line 1
                if(img==0){
                    TurnScore=0;
                    Hold(null);
                }
                else{
                    TurnScore+=img+1;
                    updateUi();
                }
        }
        public void Hold(View v){
            if(isPlayerTurn)
                YourScore += TurnScore;
            else
                ComputerScore+=TurnScore;
            img=0;
            TurnScore=0;
            updateUi();
            isPlayerTurn=!isPlayerTurn;

            if(ComputerScore>100||YourScore>100){
                Toast.makeText(MainActivity.this,(ComputerScore>100 ? "COMPUTER":"PLAYER")+" WON", Toast.LENGTH_SHORT).show();
                Reset(null);
            }
            if(!isPlayerTurn){
                new android.os.Handler().postDelayed(new Runnable(){
                    @Override
                    public void run() {
                        CComputerTurn();
                    }
                },1000);
            }


        }
    public void updateUi(){
        yourScore.setText("YOUR SCORE:" + YourScore);
        computerScore.setText("COMPUTER SCORE:" + ComputerScore);
        turnScore.setText("TURN SCORE:" + TurnScore);
        Image.setImageResource(image[img]);                      //line 1

    }
    public void Reset(View v){
        YourScore=ComputerScore=TurnScore=0;
        isPlayerTurn=true;
        img=0;
    }

    public void CComputerTurn(){
        if(!isPlayerTurn){
           // int chance=ComputerScore-YourScore;
            if(TurnScore<(ComputerScore+YourScore)/2){
            //if(chance>0)
                Roll(null);
                new android.os.Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        CComputerTurn();
                    }
                },1000);
            }
            else {
                Hold(null);
            }
        }
    }

    }


