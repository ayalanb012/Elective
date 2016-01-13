package com.example.galit.elective;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ViewGroup;

public class critiquing extends AppCompatActivity {

    private static final String TAG = critiquing.class.getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_critiquing);

        // create RangeSeekBar as Integer range between 1 and 10
        RangeSeekBar<Integer> seekBar1 = new RangeSeekBar<Integer>(1, 10, getApplicationContext());
        seekBar1.setOnRangeSeekBarChangeListener(new RangeSeekBar.OnRangeSeekBarChangeListener<Integer>() {
            @Override
            public void onRangeSeekBarValuesChanged(RangeSeekBar<?> bar, Integer minValue, Integer maxValue) {
                // handle changed range values
                Log.i(TAG, "User selected new range values: MIN=" + minValue + ", MAX=" + maxValue);
            }
        });
       // add RangeSeekBar to pre-defined layout
        ViewGroup layout1 = (ViewGroup) findViewById(R.id.grade_slideBar);
        seekBar1.setSelectedMinValue(5);  //----------------------------------------->>>> needs to be acorrding to current course grade
        seekBar1.setSelectedMaxValue(8);
        layout1.addView(seekBar1);

        // create RangeSeekBar as Integer range between 1 and 10
        RangeSeekBar<Integer> seekBar2 = new RangeSeekBar<Integer>(1, 10, getApplicationContext());
        seekBar2.setOnRangeSeekBarChangeListener(new RangeSeekBar.OnRangeSeekBarChangeListener<Integer>() {
            @Override
            public void onRangeSeekBarValuesChanged(RangeSeekBar<?> bar, Integer minValue, Integer maxValue) {
                // handle changed range values
                Log.i(TAG, "User selected new range values: MIN=" + minValue + ", MAX=" + maxValue);
            }
        });
        // add RangeSeekBar to pre-defined layout
        ViewGroup layout2 = (ViewGroup) findViewById(R.id.interst_slidebar);
        seekBar1.setSelectedMinValue(1);  //----------------------------------------->>>> needs to be acorrding to current course grade
        seekBar1.setSelectedMaxValue(3);
        layout2.addView(seekBar2);

        // create RangeSeekBar as Integer range between 1 and 10
        RangeSeekBar<Integer> seekBar3 = new RangeSeekBar<Integer>(1, 10, getApplicationContext());
        seekBar3.setOnRangeSeekBarChangeListener(new RangeSeekBar.OnRangeSeekBarChangeListener<Integer>() {
            @Override
            public void onRangeSeekBarValuesChanged(RangeSeekBar<?> bar, Integer minValue, Integer maxValue) {
                // handle changed range values
                Log.i(TAG, "User selected new range values: MIN=" + minValue + ", MAX=" + maxValue);
            }
        });
        // add RangeSeekBar to pre-defined layout
        ViewGroup layout3 = (ViewGroup) findViewById(R.id.difficulty_slidebar);
        seekBar1.setSelectedMinValue(3);  //----------------------------------------->>>> needs to be acorrding to current course grade
        seekBar1.setSelectedMaxValue(6);
        layout3.addView(seekBar3);

        // create RangeSeekBar as Integer range between 1 and 10
        RangeSeekBar<Integer> seekBar4 = new RangeSeekBar<Integer>(1, 10, getApplicationContext());
        seekBar4.setOnRangeSeekBarChangeListener(new RangeSeekBar.OnRangeSeekBarChangeListener<Integer>() {
            @Override
            public void onRangeSeekBarValuesChanged(RangeSeekBar<?> bar, Integer minValue, Integer maxValue) {
                // handle changed range values
                Log.i(TAG, "User selected new range values: MIN=" + minValue + ", MAX=" + maxValue);
            }
        });
        // add RangeSeekBar to pre-defined layout
        ViewGroup layout4 = (ViewGroup) findViewById(R.id.lecture_slidebar);
        seekBar1.setSelectedMinValue(1);  //----------------------------------------->>>> needs to be acorrding to current course grade
        seekBar1.setSelectedMaxValue(8);
        layout4.addView(seekBar4);

    }
}
