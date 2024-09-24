package com.example.making_rgb;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.contentcapture.ContentCaptureCondition;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ColorListAdapter extends BaseAdapter
{
    Context context;
    ArrayList<Color> listOfColors;

    public ColorListAdapter(Context c, ArrayList<Color> ls)
    {
        context = c;
        listOfColors = ls;
    }

    public ColorListAdapter(MainActivity c, ArrayList<android.graphics.Color> listOfColors)
    {

    }

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

        TextView red = view.findViewById(R.id.tv_v_redHexNum);
        TextView green = view.findViewById(R.id.tv_v_greenHexNum);
        TextView blue = view.findViewById(R.id.tv_v_blueHexNum);

        Color color = listOfColors.get(i);

        red.setText(Integer.toString(color.getRed()));
        green.setText(Integer.toString(color.getGreen()));
        blue.setText(Integer.toString(color.getBlue()));

        return view;
    }
}
