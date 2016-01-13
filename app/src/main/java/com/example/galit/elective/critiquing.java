package com.example.galit.elective;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ViewGroup;

public class critiquing extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_critiquing);

    /*    // create RangeSeekBar as Integer range between 1 and 10
        RangeSeekBar<Integer> seekBar = new RangeSeekBar<Integer>(1, 10, context);
        seekBar.setOnRangeSeekBarChangeListener(new RangeSeekBar.OnRangeSeekBarChangeListener<Integer>() {
            @Override
            public void onRangeSeekBarValuesChanged(RangeSeekBar<?> bar, Integer minValue, Integer maxValue) {
                // handle changed range values
                Log.i(TAG, "User selected new range values: MIN=" + minValue + ", MAX=" + maxValue);
            }
        });

// add RangeSeekBar to pre-defined layout
        ViewGroup layout = (ViewGroup) findViewById(grade_layout);
        layout.addView(seekBar);
*/
    }
}
