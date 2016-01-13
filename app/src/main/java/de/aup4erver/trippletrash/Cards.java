package de.aup4erver.trippletrash;

import java.util.ArrayList;
import java.util.Random;


/**
 * Created by sebastian on 02.01.16.
 */
public class Cards {

    private ArrayList<Integer> arrayListIDs;

    private int[] Player1;
    private int[] Player2;
    private int[] Player3;
    private int[] Player4;

    public Cards(final int[] ids) {

        arrayListIDs = new ArrayList<>();

        for (int con : ids) {
            arrayListIDs.add(con);
        }


        final int size = arrayListIDs.size() / 4;
        Player1 = new int[size];
        Player2 = new int[size];
        Player3 = new int[size];
        Player4 = new int[size];
    }

    public void randomize() {
        for (int c = 0; c < Player1.length; c++) {
            int z = randInt(0, arrayListIDs.size() - 1);
            Player1[c] = arrayListIDs.get(z);
            arrayListIDs.remove(z);
            arrayListIDs.trimToSize();
        }

        for (int c = 0; c < Player2.length; c++) {
            int z = randInt(0, arrayListIDs.size() - 1);
            Player2[c] = arrayListIDs.get(z);
            arrayListIDs.remove(z);
            arrayListIDs.trimToSize();
        }

        for (int c = 0; c < Player3.length; c++) {
            int z = randInt(0, arrayListIDs.size() - 1);
            Player3[c] = arrayListIDs.get(z);
            arrayListIDs.remove(z);
            arrayListIDs.trimToSize();
        }

        for (int c = 0; c < Player4.length; c++) {
            int z = randInt(0, arrayListIDs.size() - 1);
            Player4[c] = arrayListIDs.get(z);
            arrayListIDs.remove(z);
            arrayListIDs.trimToSize();
        }
    }

    private int randInt(int min, int max) {
        Random rand = new Random();
        return rand.nextInt(max - min + 1) + min;
    }

    public int[] getPlayer1() {
        return Player1;
    }

    public int[] getPlayer2() {
        return Player2;
    }

    public int[] getPlayer3() {
        return Player3;
    }

    public int[] getPlayer4() {
        return Player4;
    }


}
