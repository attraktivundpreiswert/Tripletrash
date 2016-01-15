package de.aup4erver.trippletrash;

import android.content.ClipData;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.DragEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.GridLayout;
import android.widget.RelativeLayout;

import java.util.Random;

public class MainActivity extends AppCompatActivity implements View.OnLongClickListener, View.OnDragListener {


    final int cardplayer = 6;
    final int[] imageViewcardID = {R.drawable.n1, R.drawable.n2, R.drawable.n3, R.drawable.n4, R.drawable.n5, R.drawable.n6, R.drawable.n7, R.drawable.n8, R.drawable.n9, R.drawable.n10,
            R.drawable.n11, R.drawable.n12, R.drawable.n13, R.drawable.n14, R.drawable.n15, R.drawable.n16, R.drawable.n17, R.drawable.n18, R.drawable.n19, R.drawable.n20, R.drawable.n21, R.drawable.n22,
            R.drawable.n23, R.drawable.n24};

    TagImageView[] imageViewcards;
    Cards cards;
    Wurfel wurfel;
    int currentpos;
    int tempcounter = 0;
    int with;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        cards = new Cards(imageViewcardID);
        cards.randomize();

        imageViewcards = new TagImageView[24];
        GridLayout[] gridLayout = {(GridLayout) findViewById(R.id.gridlayout_player1), (GridLayout) findViewById(R.id.gridlayout_player2),
                (GridLayout) findViewById(R.id.gridlayout_player3), (GridLayout) findViewById(R.id.gridlayout_player4)};


        int p1[] = cards.getPlayer1();
        int p2[] = cards.getPlayer2();
        int p3[] = cards.getPlayer3();
        int p4[] = cards.getPlayer4();

        Support supp = new Support(this);
        int with = (supp.screenwithinPx() - supp.dpstopx(32)) / 6;

        for (int i = 0; i < 6; i++) {
            imageViewcards[i] = new TagImageView(this);
            imageViewcards[i].setImageBitmap(ImageDecoder.decodeSampledBitmapFromResource(getResources(), p1[i], with, with));
            gridLayout[0].addView(imageViewcards[i]);
            imageViewcards[i].getLayoutParams().width = with;
            imageViewcards[i].getLayoutParams().height = with;
            imageViewcards[i].requestLayout();
            imageViewcards[i].setOnLongClickListener(this);
            imageViewcards[i].setTag(String.valueOf(p1[i]));
        }

        for (int i = 6; i < 12; i++) {
            imageViewcards[i] = new TagImageView(this);
            imageViewcards[i].setImageBitmap(ImageDecoder.decodeSampledBitmapFromResource(getResources(), p2[i - 6], with, with));
            gridLayout[1].addView(imageViewcards[i]);
            imageViewcards[i].getLayoutParams().width = with;
            imageViewcards[i].getLayoutParams().height = with;
            imageViewcards[i].requestLayout();
            imageViewcards[i].setOnLongClickListener(this);
            imageViewcards[i].setTag(String.valueOf(p2[i - 6]));
        }

        for (int i = 12; i < 18; i++) {
            imageViewcards[i] = new TagImageView(this);
            imageViewcards[i].setImageBitmap(ImageDecoder.decodeSampledBitmapFromResource(getResources(), p3[i - 12], with, with));
            gridLayout[2].addView(imageViewcards[i]);
            imageViewcards[i].getLayoutParams().width = with;
            imageViewcards[i].getLayoutParams().height = with;
            imageViewcards[i].requestLayout();
            imageViewcards[i].setOnLongClickListener(this);
            imageViewcards[i].setTag(String.valueOf(p3[i - 12]));
        }

        for (int i = 18; i < 24; i++) {
            imageViewcards[i] = new TagImageView(this);
            imageViewcards[i].setImageBitmap(ImageDecoder.decodeSampledBitmapFromResource(getResources(), p4[i - 18], with, with));
            gridLayout[3].addView(imageViewcards[i]);
            imageViewcards[i].getLayoutParams().width = with;
            imageViewcards[i].getLayoutParams().height = with;
            imageViewcards[i].requestLayout();
            imageViewcards[i].setOnLongClickListener(this);
            imageViewcards[i].setTag(String.valueOf(p4[i - 18]));
        }

        this.with = with;

        for (GridLayout gridLayouts : gridLayout) {
            gridLayouts.setOnDragListener(this);
        }

        findViewById(R.id.scrollView_player1).setBackgroundColor(getResources().getColor(R.color.colorPlay));

        FrameLayout[] frameLayouts = {(FrameLayout) findViewById(R.id.framelayout_bio), (FrameLayout) findViewById(R.id.framelayout_recyle), (FrameLayout) findViewById(R.id.frameLayout_rest)};

        for (FrameLayout frameLayout : frameLayouts) {
            frameLayout.setOnDragListener(new View.OnDragListener() {
                @Override
                public boolean onDrag(View v, DragEvent event) {
                    if (event.getAction() == DragEvent.ACTION_DROP) {

                        int zug = wurfel.asint();

                        if (zug == 1 || zug == 0) {

                            tempcounter++;

                            int state = currentpos;

                            String[] check;

                            switch (state) {
                                case 0:
                                    check = getResources().getStringArray(R.array.rest);
                                    break;
                                case 1:
                                    check = getResources().getStringArray(R.array.bio);
                                    break;
                                case 2:
                                    check = getResources().getStringArray(R.array.reclye);
                                    break;
                                default:
                                    check = null;
                            }

                            String tag = ((TagImageView) event.getLocalState()).getStringTag();

                            tag = getResources().getResourceEntryName(Integer.parseInt(tag));

                            assert check != null;
                            for (String s : check) {
                                if (s.equals(tag)) {
                                    View view = (View) event.getLocalState();
                                    ViewGroup owner = (ViewGroup) view.getParent();
                                    owner.removeView(view);
                                }
                            }

                            //Zug 0 special
                            if (zug == 0 && tempcounter == 3) {
                                nextPlayer();
                            }
                            else if (zug == 1 && tempcounter == 4) {
                                nextPlayer();
                            }
                        }
                    }

                    return true;
                }
            });
        }

    }


    @Override
    protected void onPostResume() {
        super.onPostResume();
        wurfel = new Wurfel();

        currentpos = randInt(0, 2);
        rotate();
        nextPlayer();
    }


    @Override
    public boolean onLongClick(View v) {

        int state = wurfel.asint();

        if (state == 0) {

            GridLayout gridLayout = (GridLayout) v.getParent();

            if (gridLayout.getId() == R.id.gridlayout_player1 && wurfel.getPlayer() == 0) {
                makeitfly(v);
            }
            if (gridLayout.getId() == R.id.gridlayout_player2 && wurfel.getPlayer() == 1) {
                makeitfly(v);
            }
            if (gridLayout.getId() == R.id.gridlayout_player3 && wurfel.getPlayer() == 2) {
                makeitfly(v);
            }
            if (gridLayout.getId() == R.id.gridlayout_player4 && wurfel.getPlayer() == 3) {
                makeitfly(v);
            }

        }
        else if (state == 1) {
            makeitfly(v);
        }

        else if (state == 4) {
            GridLayout gridLayout = (GridLayout) v.getParent();

            if (gridLayout.getId() == R.id.gridlayout_player1 && wurfel.getPlayer() == 0) {
                makeitfly(v);
            }
            if (gridLayout.getId() == R.id.gridlayout_player2 && wurfel.getPlayer() == 1) {
                makeitfly(v);
            }
            if (gridLayout.getId() == R.id.gridlayout_player3 && wurfel.getPlayer() == 2) {
                makeitfly(v);
            }
            if (gridLayout.getId() == R.id.gridlayout_player4 && wurfel.getPlayer() == 3) {
                makeitfly(v);
            }
        }
        else if (state == 5) {
            GridLayout gridLayout = (GridLayout) v.getParent();

            if (gridLayout.getId() != R.id.gridlayout_player1 && wurfel.getPlayer() == 0) {
                makeitfly(v);
            }
            if (gridLayout.getId() != R.id.gridlayout_player2 && wurfel.getPlayer() == 1) {
                makeitfly(v);
            }
            if (gridLayout.getId() != R.id.gridlayout_player3 && wurfel.getPlayer() == 2) {
                makeitfly(v);
            }
            if (gridLayout.getId() != R.id.gridlayout_player4 && wurfel.getPlayer() == 3) {
                makeitfly(v);
            }
        }
        return false;
    }

    public void makeitfly(View v) {
        Dragshadow dragshadow = new Dragshadow(v);
        ClipData clipData = ClipData.newPlainText("", "");
        v.startDrag(clipData, dragshadow, v, 0);
    }

    @Override
    public boolean onDrag(View v, DragEvent event) {
        if (event.getAction() == DragEvent.ACTION_DROP) {
            int state = wurfel.asint();

            if (state == 4 || state == 5) {
                tempcounter++;

                View view = (View) event.getLocalState();
                ViewGroup owner = (ViewGroup) view.getParent();
                owner.removeView(view);

                GridLayout container = (GridLayout) v;
                container.addView(view);
                view.setVisibility(View.VISIBLE);


                if (tempcounter == 3) {
                    nextPlayer();
                }
            }
        }
        return true;
    }

    public void rotate() {

        RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.RelativeLayoutPicutre);
        if (currentpos == 0) {
            relativeLayout.setBackgroundDrawable(new BitmapDrawable(getResources(), ImageDecoder.decodeSampledBitmapFromResource(getResources(), R.drawable.m4, with, with)));
        } else if (currentpos == 1) {
            relativeLayout.setBackgroundDrawable(new BitmapDrawable(getResources(), ImageDecoder.decodeSampledBitmapFromResource(getResources(), R.drawable.m2, with, with)));
        } else if (currentpos == 2) {
            relativeLayout.setBackgroundDrawable(new BitmapDrawable(getResources(), ImageDecoder.decodeSampledBitmapFromResource(getResources(), R.drawable.m1, with, with)));
        }
    }

    public void nextPlayer() {
        View players[] = {findViewById(R.id.scrollView_player1), findViewById(R.id.scrollView_player2), findViewById(R.id.scrollView_player3), findViewById(R.id.scrollView_player4)};
        wurfel.nextPlayer();
        wurfel.roll();
        findViewById(R.id.imageView).setBackgroundDrawable(new BitmapDrawable(ImageDecoder.decodeSampledBitmapFromResource(getResources(), wurfel.asResDrawble(), with, with)));

        for (int i = 0; i <= 3; i++) {
            if (i == wurfel.getPlayer()) {
                players[i].setBackgroundResource(R.color.colorPlay);
            }
            else {
                players[i].setBackgroundResource(R.color.colorFeld);
            }
        }
        tempcounter = 0;

        if (wurfel.asint() == 2 || wurfel.asint() == 3)  {
            rotate();
            nextPlayer();
        }
    }

    private int randInt(int min, int max) {
        Random rand = new Random();
        return rand.nextInt(max - min + 1) + min;
    }

}
