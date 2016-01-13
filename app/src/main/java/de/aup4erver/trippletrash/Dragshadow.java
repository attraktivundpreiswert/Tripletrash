package de.aup4erver.trippletrash;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;

/**
 * Created by sebastian on 28.12.15.
 */
public class Dragshadow extends View.DragShadowBuilder {

    Drawable greyBox;


    public Dragshadow(View view) {
        super(view);
        greyBox = ((ImageView) view).getDrawable();

        if (greyBox == null) {
            greyBox = new ColorDrawable(Color.GREEN);
        }
    }

    @Override
    public void onDrawShadow(Canvas canvas) {
        greyBox.draw(canvas);
    }

    @Override
    public void onProvideShadowMetrics(Point shadowSize, Point shadowTouchPoint) {
        View v = getView();

        int height = v.getHeight();
        int weith = v.getWidth();

        greyBox.setBounds(0, 0, weith, height);

        shadowSize.set(weith, height);

        shadowTouchPoint.set(weith / 2, height / 2);

    }
}
