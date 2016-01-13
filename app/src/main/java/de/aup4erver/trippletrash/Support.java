package de.aup4erver.trippletrash;

import android.content.Context;
import android.util.DisplayMetrics;

/**
 * Created by sebastian on 03.01.16.
 */
public class Support {

    private Context context;
    private float screenWithDp = 0;
    private float screenHighDp = 0;

    public Support(Context contex) {
        this.context = contex;
    }

    //get the Screen With in DP
    public float screenWithinDp() {

        if (screenWithDp == 0) {
            DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
            this.screenWithDp = displayMetrics.widthPixels / displayMetrics.density;
        }
        return screenWithDp;
    }

    //get the Screen High in Dp
    public float screenHighinDp() {
        if (screenHighDp == 0) {
            DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
            screenHighDp = displayMetrics.heightPixels / displayMetrics.density;
        }
        return screenHighDp;
    }

    public int screenwithinPx() {
        return context.getResources().getDisplayMetrics().widthPixels;
    }

    public int screenhighinPx() {
        return context.getResources().getDisplayMetrics().heightPixels;
    }

    public int dpstopx(float dps) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dps * scale + 0.5f);
    }
}
