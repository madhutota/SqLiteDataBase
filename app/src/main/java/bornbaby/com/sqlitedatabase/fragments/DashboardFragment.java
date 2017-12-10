package bornbaby.com.sqlitedatabase.fragments;


import android.annotation.SuppressLint;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import bornbaby.com.sqlitedatabase.MainActivity;
import bornbaby.com.sqlitedatabase.R;


public class DashboardFragment extends Fragment implements SensorEventListener {
    public static final String TAG = DashboardFragment.class.getSimpleName();

    private TextView countText, stepsText;

    private View view;
    private MainActivity parent;
    SensorManager sensorManager;
    boolean running = false;

    private Button minusBtn;
    private Button resetBtn;
    private Button plusBtn;

    private int count = 0;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        parent = (MainActivity) getActivity();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (view != null)
            return view;
        view = inflater.inflate(R.layout.fragment_dashboard, container, false);
        inItUI();
        return view;
    }

    private void inItUI() {

        countText = view.findViewById(R.id.countText);
        stepsText = view.findViewById(R.id.stepsText);
        minusBtn = view.findViewById(R.id.minusBtn);
        resetBtn = view.findViewById(R.id.resetBtn);
        plusBtn = view.findViewById(R.id.plusBtn);
        minusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count--;
                countText.setText(count + "");

            }
        });
        resetBtn.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                count = 0;
                countText.setText(count + "");

            }
        });
        plusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count++;
                countText.setText(count + "");

            }
        });

        sensorManager = (SensorManager) parent.getSystemService(Context.SENSOR_SERVICE);
    }

    @Override
    public void onResume() {
        super.onResume();
        running = true;
        Sensor countSensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);

        if (countSensor != null) {
            sensorManager.registerListener(this, countSensor, SensorManager.SENSOR_DELAY_UI);
        } else {
            Toast.makeText(parent, "Sensor Not found !!", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        running = false;
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (running) {
            countText.setText((int) event.values[0]+"");
            count = (int) event.values[0];
        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
