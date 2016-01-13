package de.aup4erver.trippletrash;

import android.content.ClipData;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.DragEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.GridLayout;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity implements View.OnLongClickListener, View.OnDragListener {


    final int cardplayer = 6;
    final int[] imageViewcardID = {R.drawable.n1, R.drawable.n2, R.drawable.n3, R.drawable.n4, R.drawable.n5, R.drawable.n6, R.drawable.n7, R.drawable.n8, R.drawable.n9, R.drawable.n10,
            R.drawable.n11, R.drawable.n12, R.drawable.n13, R.drawable.n14, R.drawable.n15, R.drawable.n16, R.drawable.n17, R.drawable.n18, R.drawable.n19, R.drawable.n20, R.drawable.n21, R.drawable.n22,
            R.drawable.n23, R.drawable.n24};

    TagImageView[] imageViewcards;
    Cards cards;
    Wurfel wurfel;
    int currentpos = 0;
    int tempcounter;


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

                        for (String s : check) {
                            if (s.equals(tag)) {
                                View view = (View) event.getLocalState();
                                ViewGroup owner = (ViewGroup) view.getParent();
                                owner.removeView(view);
                            }
                        }
                    }
                    return true;
                }
            });
        }

    }


    @Override
    public boolean onLongClick(View v) {

        makeitfly(v);
        /*
        int state = wurfel.asint();

        if (state == 0) {

            GridLayout gridLayout = (GridLayout) ((ViewGroup) v.getParent());

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
            makeitfly(v);
        }
        else if (state == 1) {
            makeitfly(v);
        }
        else if (state == 4) {
            makeitfly(v);
        }
        else if (state == 5) {
            GridLayout gridLayout = (GridLayout) ((ViewGroup) v.getParent());

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
        */
        return false;
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        wurfel = new Wurfel();
        ((ImageView) findViewById(R.id.imageView)).setImageDrawable(wurfel.asbitmap());

        currentpos = (int) Math.floor(Math.random() * 2);
        rotate();
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

            if (state == 0) {
                //let the other handler handel this shit
            } else if (state == 1) {

            } else if (state == 4) {

            } else if (state == 5) {

            }
            return false;
        }
        return true;
    }

    public void rotate() {
        FrameLayout frameLayoutbio = (FrameLayout) findViewById(R.id.framelayout_bio);
        FrameLayout frameLayoutrest = (FrameLayout) findViewById(R.id.frameLayout_rest);
        FrameLayout frameLayoutrecyle = (FrameLayout) findViewById(R.id.framelayout_recyle);

        if (currentpos == 0) {
            frameLayoutbio.setBackgroundColor(Color.GRAY);
            frameLayoutrecyle.setBackgroundColor(Color.GRAY);
            frameLayoutrest.setBackgroundColor(Color.TRANSPARENT);
        } else if (currentpos == 1) {
            frameLayoutbio.setBackgroundColor(Color.GRAY);
            frameLayoutrecyle.setBackgroundColor(Color.TRANSPARENT);
            frameLayoutrest.setBackgroundColor(Color.GRAY);
        } else if (currentpos == 2) {
            frameLayoutbio.setBackgroundColor(Color.TRANSPARENT);
            frameLayoutrecyle.setBackgroundColor(Color.GRAY);
            frameLayoutrest.setBackgroundColor(Color.GRAY);
        }
    }

}
