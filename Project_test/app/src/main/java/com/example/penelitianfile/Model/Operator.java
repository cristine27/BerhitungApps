package com.example.penelitianfile.Model;

import android.graphics.Bitmap;

public class Operator {
    float x;
    float y;
    Bitmap bitmap;

    public Operator(float x, float y, Bitmap bitmap){
        this.x = x;
        this.y = y;
        this.bitmap = bitmap;
    }

    public float getY() {
        return y;
    }

    public float getX() {
        return x;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setY(float y) {
        this.y = y;
    }

    public void setX(float x) {
        this.x = x;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }
}

