package com.example.making_rgb;

import android.graphics.Color;
import android.graphics.ColorSpace;
import android.graphics.Interpolator;
import android.os.Bundle;
import android.text.style.BackgroundColorSpan;
import android.util.Log;
import android.view.View;
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

    static ArrayList<MyColor> listOfColors;

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
        btn_j_saveColor = findViewById(R.id.btn_v_saveColor);

        sb_j_redSeekBar.setMax(255);
        sb_j_greenSeekBar.setMax(255);
        sb_j_blueSeekBar.setMax(255);

        listOfColors = new ArrayList<MyColor>();

        seekBarChangeListener();

        if(listOfColors.size() != 0)
        {
            MyColor newColor = new MyColor(Integer.valueOf(et_j_redHexNum.getText().toString()), Integer.valueOf(et_j_greenHexNum.getText().toString()), Integer.valueOf(et_j_blueHexNum.getText().toString()));
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

                changeTextColor(i);

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



    }

    private void changeBackgroundColor(int hex)
    {
        View bg = this.getWindow().getDecorView();
        bg.setBackgroundColor(hex);
    }

    private void changeTextColor(int i)
    {
        /*if(i < 75)
        {
            lbl_j_redLabel.setTextColor(Color.WHITE);
            et_j_redHexNum.setTextColor(Color.WHITE);
            lbl_j_greenLabel.setTextColor(Color.WHITE);
            et_j_greenHexNum.setTextColor(Color.WHITE);
            lbl_j_blueLabel.setTextColor(Color.WHITE);
            et_j_blueHexNum.setTextColor(Color.WHITE);
            lbl_j_hexLabel.setTextColor(Color.WHITE);
            et_j_hexCode.setTextColor(Color.WHITE);
        }
        else
        {
            lbl_j_redLabel.setTextColor(Color.BLACK);
            et_j_redHexNum.setTextColor(Color.BLACK);
            lbl_j_greenLabel.setTextColor(Color.BLACK);
            et_j_greenHexNum.setTextColor(Color.BLACK);
            lbl_j_blueLabel.setTextColor(Color.BLACK);
            et_j_blueHexNum.setTextColor(Color.BLACK);
            lbl_j_hexLabel.setTextColor(Color.BLACK);
            et_j_hexCode.setTextColor(Color.BLACK);
        } */
    }

    private void addColorButtonListener()
    {
        btn_j_saveColor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int red = Integer.valueOf(et_j_redHexNum.getText().toString());
                int green = Integer.valueOf(et_j_greenHexNum.getText().toString());
                int blue = Integer.valueOf(et_j_blueHexNum.getText().toString());

                MyColor newColor = new MyColor(red, green, blue);

                listOfColors.add(newColor);
                adapter.notifyDataSetChanged();
                Log.d("Color Call", "Called");
            }
        });
    }

    private void fillListView()
    {
        adapter = new ColorListAdapter(this, listOfColors);
        lv_j_colorList.setAdapter(adapter);
    }

    private void addDummyDataToList()
    {
        MyColor newColor = new MyColor(255, 25, 65);
        listOfColors.add(newColor);

        newColor = new MyColor(156, 63, 1);
        listOfColors.add(newColor);
    }

    private int makeTextColor(int red, int green, int blue)
    {
        //return Color.rgb(255 - red, 255 - green, 255 - blue);
        return (127 < ((red + green + blue) / 3)) ? Color.WHITE : Color.BLACK;
        //int x = (y < z) ? a : b;
    }
}