package com.example.making_rgb;

import java.io.Serializable;

public class Color implements Serializable
{
    int red;
    int green;
    int blue;

    public Color()
    {

    }

    public Color(int r, int g, int b)
    {
        red = r;
        green = g;
        blue = b;
    }

    public int getRed() {
        return red;
    }

    public void setRed(int red) {
        this.red = red;
    }

    public int getGreen() {
        return green;
    }

    public void setGreen(int green) {
        this.green = green;
    }

    public int getBlue() {
        return blue;
    }

    public void setBlue(int blue) {
        this.blue = blue;
    }
}
