package com.example.wro.walliky.tabOrientation;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;

import static com.example.wro.walliky.MainActivity.mSensorManager;

/**
 * Created by zna on 17/01/2017.
 */

public class Read_OrientationTAB extends Thread {
    private DataModule_OrientationTAB monmdd;
    private String nom;
    private Sensor mAccelerometer = null;
    private AppCompatActivity appCompatActivity;
    private Float x, y, z;


    public Read_OrientationTAB(DataModule_OrientationTAB monmdd, String nom) {
        super();
        this.monmdd = monmdd;
        this.nom = nom;
        //this.appCompatActivity = appCompatActivity;
    }

    // l'abonement se fit dans le thread graphique


    @Override
    public void run() {
        mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mSensorManager.registerListener(new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {
                x = event.values[0];
                y = event.values[1];
                z = event.values[2];
                //System.out.println("x y et z : " + x + y + z);
                monmdd.setvalues(x, y, z);

            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {

            }

        }, mAccelerometer, SensorManager.SENSOR_DELAY_NORMAL);


    }


}
