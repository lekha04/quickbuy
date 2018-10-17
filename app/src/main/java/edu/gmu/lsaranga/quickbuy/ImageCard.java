package edu.gmu.lsaranga.quickbuy;

import android.view.View;

public class ImageCard {
    String imageCaption;
    int imageResourceId;

    public String getImageCaption() {
        return imageCaption;
    }
    public void setImageCaption(String caption) {
        this.imageCaption = caption;
    }

    public int getImageResourceId() {
        return imageResourceId;
    }
    public void setImageResourceId(int imageResourceId) {
        this.imageResourceId = imageResourceId;
    }

    public int captionVisibility() {
        return imageCaption != null ? View.VISIBLE : View.INVISIBLE;
    }
}
