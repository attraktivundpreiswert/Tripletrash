package de.aup4erver.trippletrash;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;

import java.util.Random;

/**
 * Created by sebastian on 09.01.16.
 */
public class Wurfel {
    private int state = 0;
    private boolean locked;
    private int openstate = 0;


    private int player = 0;

    public Wurfel() {
        roll();
    }


    public void roll() {
        state = randInt(0, 5);

        if (player != 3)
            player += player;
        else
            player = 0;

    }

    private int randInt(int min, int max) {
        Random rand = new Random();
        return rand.nextInt(max - min + 1) + min;
    }

    public String astext() {
        return String.valueOf(state);
    }

    public int asint() {
        return 0;//state;
    }

    public ColorDrawable asbitmap() {

        ColorDrawable colorDrawable;
        switch (state) {
            case 0:
                colorDrawable = new ColorDrawable(Color.RED);
                break;
            case 1:
                colorDrawable = new ColorDrawable(Color.GREEN);
                break;
            case 2:
                colorDrawable = new ColorDrawable(Color.YELLOW);
                break;
            case 3:
                colorDrawable = new ColorDrawable(Color.BLACK);
                break;
            case 4:
                colorDrawable = new ColorDrawable(Color.WHITE);
                break;
            case 5:
                colorDrawable = new ColorDrawable(Color.MAGENTA);
                break;
            default:
                colorDrawable = null;
                break;
        }
        return colorDrawable;
    }

    public boolean getLocked() {
        return locked;
    }

    public void setLocked(boolean locked) {
        this.locked = locked;
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
}
