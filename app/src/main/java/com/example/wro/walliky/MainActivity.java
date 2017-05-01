package com.example.wro.walliky;

import android.bluetooth.BluetoothAdapter;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.media.Image;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wro.walliky.bluetoothLink.View_BluetouthLink;
import com.example.wro.walliky.capNXT.ThWorker_CapNXT;
import com.example.wro.walliky.command_NXT.JoystickView;
import com.example.wro.walliky.lib.BTNxtCom;
import com.example.wro.walliky.tabOrientation.ThWorker_OrientationTAB;
import com.example.wro.walliky.tabOrientation.View_OrientationTAB;

//TODO architecture par composants
public class MainActivity extends AppCompatActivity implements JoystickView.JoystickListener{
    public static SensorManager mSensorManager;
    private final float NOISE = (float) 2.0;
    double ax, ay, az;   // these are the acceleration in x,y and z axis




    private EditText editText = null;
    private TextView textView = null;
    private SensorManager sensorManager;
    private float mLastX, mLastY, mLastZ;
    private boolean mInitialized;
    private Sensor mAccelerometer;
    private JoystickView joystickView;


    View_OrientationTAB view_orientationTAB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        view_orientationTAB = (View_OrientationTAB) findViewById(R.id.view_orientationtab_id);

        mInitialized = false;
        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);


        //image_direction = (ImageView) findViewById(R.id.image_for_direction_id);
        //image_direction.setImageResource(R.drawable.direction_image);

        //joystickView = (JoystickView) findViewById(R.id.joystick_id);
        JoystickView joystick = new JoystickView(this);
        //view_orientationTAB.startAquisation();






    }



    @Override
    public void onJoystickMoved(float xPercent, float yPercent, int id)  {
        switch (id)
        {
            // Y is what do forward here
            //avancer/tourner/reculer
            //TODO puissance seront envoyées toutes les 100ms
            case R.id.joystick_id:
                Log.d("Joystick ", "X percent: " +Integer.valueOf((int) (xPercent*100)) + " Y percent: " + Integer.valueOf((int) (yPercent*100)));
                //System.out.println("X percent: " + xPercent*100 + " Y percent: " + yPercent*100);
                if (View_BluetouthLink.isconnected.isChecked()){


                try {
                    View_BluetouthLink.monBTNxtCom.ecrireMessage(Integer.valueOf((int) (yPercent*100)),Integer.valueOf((int) (xPercent*100)));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                }
                break;

        }
    }
}
