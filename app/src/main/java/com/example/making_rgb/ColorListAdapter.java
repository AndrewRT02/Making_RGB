package com.example.making_rgb;

import android.content.Context;
import android.graphics.Color;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.contentcapture.ContentCaptureCondition;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class ColorListAdapter extends BaseAdapter
{
    Context context;
    ArrayList<ColorInfo> listOfColors;

    public ColorListAdapter(Context c, ArrayList<ColorInfo> ls)
    {
        context = c;
        listOfColors = ls;
    }

    /* public ColorListAdapter(MainActivity c, ArrayList<android.graphics.Color> listOfColors)
    {

    } */

    @Override
    public int getCount()
    {
        return listOfColors.size();
    }

    @Override
    public Object getItem(int i)
    {
        return listOfColors.get(i);
    }

    @Override
    public long getItemId(int i)
    {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup)
    {
        if(view == null)
        {
            LayoutInflater mInflator = (LayoutInflater) context.getSystemService(MainActivity.LAYOUT_INFLATER_SERVICE);
            view = mInflator.inflate(R.layout.custom_cell, null);
        }

        TextView red = view.findViewById(R.id.tv_v_customRed);
        TextView green = view.findViewById(R.id.tv_v_customGreen);
        TextView blue = view.findViewById(R.id.tv_v_customBlue);
        TextView hexNum = view.findViewById(R.id.tv_v_customHex);

        TextView redLabel = view.findViewById(R.id.customRedLabel);
        TextView greenLabel = view.findViewById(R.id.customGreenLabel);
        TextView blueLabel = view.findViewById(R.id.customBlueLabel);
        TextView hexLabel = view.findViewById(R.id.customHexLabel);


        ColorInfo color = listOfColors.get(i);

        red.setText(Integer.toString(color.getRed()));
        green.setText(Integer.toString(color.getGreen()));
        blue.setText(Integer.toString(color.getBlue()));

        int redHexValue = Integer.parseInt(red.getText().toString());
        int greenHexValue = Integer.parseInt(green.getText().toString());
        int blueHexValue = Integer.parseInt(blue.getText().toString());

        int hex;
        hex = Color.rgb(redHexValue, greenHexValue, blueHexValue);
        view.setBackgroundColor(hex);
        hexNum.setText(Integer.toHexString(hex).toUpperCase().substring(2));

        int textColor;
        textColor = makeTextColor(redHexValue, greenHexValue, blueHexValue);

        red.setTextColor(textColor);
        redLabel.setTextColor(textColor);
        green.setTextColor(textColor);
        greenLabel.setTextColor(textColor);
        blue.setTextColor(textColor);
        blueLabel.setTextColor(textColor);
        hexNum.setTextColor(textColor);
        hexLabel.setTextColor(textColor);


        return view;
    }

    private int makeTextColor(int red, int green, int blue)
    {
        //return Color.rgb(255 - red, 255 - green, 255 - blue);
        return (127 < ((red + green + blue) / 3)) ? Color.BLACK : Color.WHITE;
        //int x = (y < z) ? a : b;
    }
}
