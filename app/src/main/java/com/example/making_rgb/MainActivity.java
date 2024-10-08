package com.example.making_rgb;

import android.graphics.Color;
import android.graphics.ColorSpace;
import android.graphics.Interpolator;
import android.os.Bundle;
import android.text.style.BackgroundColorSpan;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    TextView lbl_j_redLabel;
    TextView lbl_j_greenLabel;
    TextView lbl_j_blueLabel;
    TextView lbl_j_hexLabel;
    TextView et_j_redHexNum;
    TextView et_j_greenHexNum;
    TextView et_j_blueHexNum;
    TextView et_j_hexCode;

    SeekBar sb_j_redSeekBar;
    SeekBar sb_j_greenSeekBar;
    SeekBar sb_j_blueSeekBar;

    ListView lv_j_colorList;

    Button btn_j_saveColor;

    static ArrayList<ColorInfo> listOfColors;

    ColorListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        lbl_j_redLabel = findViewById(R.id.redLabel);
        lbl_j_greenLabel = findViewById(R.id.greenLabel);
        lbl_j_blueLabel = findViewById(R.id.blueLabel);
        lbl_j_hexLabel = findViewById(R.id.hexLabel);
        et_j_redHexNum = findViewById(R.id.tv_v_redHexNum);
        et_j_greenHexNum = findViewById(R.id.tv_v_greenHexNum);
        et_j_blueHexNum = findViewById(R.id.tv_v_blueHexNum);
        et_j_hexCode = findViewById(R.id.tv_v_hexCode);
        sb_j_redSeekBar = findViewById(R.id.sb_v_redSeekbar);
        sb_j_greenSeekBar = findViewById(R.id.sb_v_greenSeekbar);
        sb_j_blueSeekBar = findViewById(R.id.sb_v_blueSeekbar);
        lv_j_colorList = findViewById(R.id.lv_v_colorList);
        lv_j_colorList.setClickable(true);
        btn_j_saveColor = findViewById(R.id.btn_v_saveColor);

        sb_j_redSeekBar.setMax(255);
        sb_j_greenSeekBar.setMax(255);
        sb_j_blueSeekBar.setMax(255);

        listOfColors = new ArrayList<ColorInfo>();

        seekBarChangeListener();

        if(listOfColors.size() != 0)
        {
            ColorInfo newColor = new ColorInfo(Integer.valueOf(et_j_redHexNum.getText().toString()), Integer.valueOf(et_j_greenHexNum.getText().toString()), Integer.valueOf(et_j_blueHexNum.getText().toString()));
            listOfColors.add(newColor);
        }
        else
        {
            addDummyDataToList();
        }
        addColorButtonListener();
        fillListView();
    }

private void seekBarChangeListener()
    {
        sb_j_redSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                et_j_redHexNum.setText(String.valueOf(i));

                int redHexValue = Integer.parseInt(et_j_redHexNum.getText().toString());
                int greenHexValue = Integer.parseInt(et_j_greenHexNum.getText().toString());
                int blueHexValue = Integer.parseInt(et_j_blueHexNum.getText().toString());

                int textColor;
                textColor = makeTextColor(redHexValue, greenHexValue, blueHexValue);

                lbl_j_redLabel.setTextColor(textColor);
                et_j_redHexNum.setTextColor(textColor);
                lbl_j_greenLabel.setTextColor(textColor);
                et_j_greenHexNum.setTextColor(textColor);
                lbl_j_blueLabel.setTextColor(textColor);
                et_j_blueHexNum.setTextColor(textColor);
                lbl_j_hexLabel.setTextColor(textColor);
                et_j_hexCode.setTextColor(textColor);

                int hexNum;

                hexNum = Color.rgb(redHexValue, greenHexValue, blueHexValue);

                et_j_hexCode.setText(Integer.toHexString(hexNum).toUpperCase().substring(2));
                //et_j_hexCode.setText(String.valueOf(hexNum));

                changeBackgroundColor(hexNum);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        sb_j_greenSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                et_j_greenHexNum.setText(String.valueOf(i));

                //changeTextColor(i);

                int redHexValue = Integer.parseInt(et_j_redHexNum.getText().toString());
                int greenHexValue = Integer.parseInt(et_j_greenHexNum.getText().toString());
                int blueHexValue = Integer.parseInt(et_j_blueHexNum.getText().toString());

                int textColor;
                textColor = makeTextColor(redHexValue, greenHexValue, blueHexValue);

                lbl_j_redLabel.setTextColor(textColor);
                et_j_redHexNum.setTextColor(textColor);
                lbl_j_greenLabel.setTextColor(textColor);
                et_j_greenHexNum.setTextColor(textColor);
                lbl_j_blueLabel.setTextColor(textColor);
                et_j_blueHexNum.setTextColor(textColor);
                lbl_j_hexLabel.setTextColor(textColor);
                et_j_hexCode.setTextColor(textColor);

                int hexNum;

                hexNum = Color.rgb(redHexValue, greenHexValue, blueHexValue);

                et_j_hexCode.setText(Integer.toHexString(hexNum).toUpperCase().substring(2));

                //et_j_hexCode.setText(String.valueOf(hexNum));

                changeBackgroundColor(hexNum);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        sb_j_blueSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

                et_j_blueHexNum.setText(String.valueOf(i));

                //changeTextColor(i);

                int redHexValue = Integer.parseInt(et_j_redHexNum.getText().toString());
                int greenHexValue = Integer.parseInt(et_j_greenHexNum.getText().toString());
                int blueHexValue = Integer.parseInt(et_j_blueHexNum.getText().toString());

                int textColor;
                textColor = makeTextColor(redHexValue, greenHexValue, blueHexValue);

                lbl_j_redLabel.setTextColor(textColor);
                et_j_redHexNum.setTextColor(textColor);
                lbl_j_greenLabel.setTextColor(textColor);
                et_j_greenHexNum.setTextColor(textColor);
                lbl_j_blueLabel.setTextColor(textColor);
                et_j_blueHexNum.setTextColor(textColor);
                lbl_j_hexLabel.setTextColor(textColor);
                et_j_hexCode.setTextColor(textColor);

                int hexNum;

                hexNum = Color.rgb(redHexValue, greenHexValue, blueHexValue);

                et_j_hexCode.setText(Integer.toHexString(hexNum).toUpperCase().substring(2));

                //et_j_hexCode.setText(String.valueOf(hexNum));

                changeBackgroundColor(hexNum);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        //Show the selected colors and where the seekbars are to make it
        lv_j_colorList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                ColorInfo getColor = (ColorInfo) adapter.getItem(i);

                int oldRed = getColor.getRed();
                int oldGreen = getColor.getGreen();
                int oldBlue = getColor.getBlue();

                et_j_redHexNum.setText(String.valueOf(oldRed));
                et_j_greenHexNum.setText(String.valueOf(oldGreen));
                et_j_blueHexNum.setText(String.valueOf(oldBlue));

                int text = makeTextColor(oldRed, oldGreen, oldBlue);
                changeTextColorBack(text);

                sb_j_redSeekBar.setProgress(oldRed);
                sb_j_greenSeekBar.setProgress(oldGreen);
                sb_j_blueSeekBar.setProgress(oldBlue);

                Log.d("Got item:", "12");


            }
        });

    }

    private void changeBackgroundColor(int hex)
    {
        View bg = this.getWindow().getDecorView();
        bg.setBackgroundColor(hex);
    }

    //Simply change the text boxes text color
    private void changeTextColorBack(int x)
    {
            lbl_j_redLabel.setTextColor(x);
            et_j_redHexNum.setTextColor(x);
            lbl_j_greenLabel.setTextColor(x);
            et_j_greenHexNum.setTextColor(x);
            lbl_j_blueLabel.setTextColor(x);
            et_j_blueHexNum.setTextColor(x);
            lbl_j_hexLabel.setTextColor(x);
            et_j_hexCode.setTextColor(x);
    }

    //Saves the colors in the list and resets the seekbars and numbers and hex code
    private void addColorButtonListener()
    {
        btn_j_saveColor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int red = Integer.valueOf(et_j_redHexNum.getText().toString());
                int green = Integer.valueOf(et_j_greenHexNum.getText().toString());
                int blue = Integer.valueOf(et_j_blueHexNum.getText().toString());

                ColorInfo newColor = new ColorInfo(red, green, blue);

                listOfColors.add(newColor);
                adapter.notifyDataSetChanged();
                Log.d("Color Call", "Called");

                sb_j_redSeekBar.setProgress(0);
                sb_j_greenSeekBar.setProgress(0);
                sb_j_blueSeekBar.setProgress(0);

                changeBackgroundColor(Color.WHITE);

                changeTextColorBack(Color.BLACK);
            }
        });
    }

    //adapter
    private void fillListView()
    {
        adapter = new ColorListAdapter(this, listOfColors);
        lv_j_colorList.setAdapter(adapter);
    }

    //Just add some data to the list
    private void addDummyDataToList()
    {
        ColorInfo newColor = new ColorInfo(255, 25, 65);
        listOfColors.add(newColor);

        newColor = new ColorInfo(156, 63, 1);
        listOfColors.add(newColor);
    }

    //Make the color and this will choose whether the text color will be black or white
    private int makeTextColor(int red, int green, int blue)
    {
        //return Color.rgb(255 - red, 255 - green, 255 - blue);
        return (127 < ((red + green + blue) / 3)) ? Color.BLACK : Color.WHITE;
        //int x = (y < z) ? a : b;
    }

}