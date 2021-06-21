package com.otakuphile.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {



    public static TextView status ;
    boolean gameActive = true;

   //0-X
   //1-O
    int activePlayer=0;
    int []gameState={2,2,2,2,2,2,2,2,2};
    //State :
    //0 = X
    //1 = O
    //2 = NULL
    int [][] winPos = {{0,1,2},{3,4,5},{6,7,8},
                       {0,3,6},{1,4,7},{2,5,8},
                       {0,4,8},{2,4,6}};
    public void playerTap(View view)
    {
        if(!gameActive)
        {
            gameReset(view);
        }
        status = findViewById(R.id.status);
        ImageView img = (ImageView)view;
        int tappedImage = Integer.parseInt(img.getTag().toString());
        if(gameState[tappedImage]==2)
        {
            gameState[tappedImage]=activePlayer;
            img.setTranslationY(-1000f);
            if(activePlayer==0) {
                img.setImageResource(R.drawable.x);
                activePlayer = 1;
                status.setText("O's Turn: Tap To Play");
            }
            else
            {
                img.setImageResource(R.drawable.o);
                activePlayer=0;
                status.setText("X's Turn: Tap To Play");
            }
            img.animate().translationYBy(1000f).setDuration(300);

        }
        //checking winning position
        for(int []winPosition : winPos )
        {
            if(gameState[winPosition[0]]!=2 && gameState[winPosition[0]]==gameState[winPosition[1]] && gameState[winPosition[1]]==gameState[winPosition[2]])
            {
                //who won?
                String winnerStr;
                if(gameState[winPosition[0]]==0)
                {
                    winnerStr="X has Won ";
                    // updating status bar acc to winner
                    status.setText(winnerStr);
                    gameActive=false;

                }
                else
                {
                    winnerStr="O has Won ";
                    // updating status bar acc to winner
                    status.setText(winnerStr);
                    gameActive=false;
                }

            }
        }
        boolean gameDraw=false;

        for(int emptySquare : gameState)
        {
            if(emptySquare==2)
            {
                gameDraw=true;
                break;
            }
        }
        if(!gameDraw && gameActive)
        {
            gameActive=false;
            String winnerStr="Game Draw";
            status.setText(winnerStr);

        }






    }
    public void gameReset(View view)
    {
        gameActive=true;
        activePlayer=0;
        for(int i=0;i<gameState.length;i++)
        {
            gameState[i]=2;
        }
        ((ImageView)findViewById(R.id.imageView0)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView1)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView9)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView3)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView4)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView5)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView6)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView7)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView8)).setImageResource(0);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}