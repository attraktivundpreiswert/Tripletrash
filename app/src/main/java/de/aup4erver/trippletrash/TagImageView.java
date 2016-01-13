package de.aup4erver.trippletrash;

import android.content.Context;
import android.widget.ImageView;

/**
 * Created by sebastian on 11.01.16.
 */
public class TagImageView extends ImageView {

    String tagstring;

    public TagImageView(Context context) {
        super(context);
    }

    public void setTag(String tag) {
        this.tagstring = tag;
    }

    public String getStringTag() {
        return tagstring;
    }
}
