package com.example.penelitianfile.Model;

import android.graphics.Bitmap;

public class Operand {
    float x;
    float y;
    Bitmap bitmap;

    public Operand(float x, float y, Bitmap bitmap){
        this.x = x;
        this.y = y;
        this.bitmap = bitmap;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }
}
