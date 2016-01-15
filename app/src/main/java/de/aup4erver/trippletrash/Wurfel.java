package de.aup4erver.trippletrash;

import java.util.Random;

/**
 * Created by sebastian on 09.01.16.
 */
public class Wurfel {
    private int state = 0;
    private int openstate = 0;

    private int[] wurfelID = {R.drawable.w1, R.drawable.w2, R.drawable.w3, R.drawable.w4, R.drawable.w5, R.drawable.w6};


    private int player = -1;

    public Wurfel() {
        roll();
    }


    public void roll() {
        state = randInt(0, 5);
    }

    private int randInt(int min, int max) {
        Random rand = new Random();
        return rand.nextInt(max - min + 1) + min;
    }

    public String astext() {
        return String.valueOf(state);
    }

    public int asint() {
        return state;//state;
    }

    public int asResDrawble (){
        return wurfelID[state];//state;
    }

    public int getPlayer() {
        return player;
    }

    public void schiben() {
        if (openstate == 2)
            openstate = 0;
        else
            openstate += 1;
    }

    public int getDeckel() {
        return openstate;
    }

    public void nextPlayer() {
        if (player == 3) {
            player = 0;
        }
        else {
            player++;
        }
    }
}
