package com.example.wro.walliky.tabOrientation;

import android.os.AsyncTask;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by zna on 17/01/2017.
 */

public class ThWorker_OrientationTAB extends AsyncTask<Void, float[], Void> {
    private TextView tvX;
    private TextView tvY;
    private TextView tvZ;
    private ImageView img;

    public ThWorker_OrientationTAB(TextView tvX, TextView tvY, TextView tvZ) {
        this.tvX = tvX;
        this.tvY = tvY;
        this.tvZ = tvZ;
    }

    @Override
    protected Void doInBackground(Void... params) {
        DataModule_OrientationTAB cemdd = new DataModule_OrientationTAB("ceMDD");
        Read_OrientationTAB ceThreadLisCapteur = new Read_OrientationTAB(cemdd, "ceThread listner");
        ceThreadLisCapteur.start();
        float num[] = new float[3];


        for (int i = 0; i < 50; i++) {
            try {
                //System.out.println("x y et z : " + num);

                num[0] = cemdd.getvalues()[0];
                num[1] = cemdd.getvalues()[1];
                num[2] = cemdd.getvalues()[2];
                publishProgress(num);
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.interrupted();
            }
        }
        return null;
    }

    @Override
    protected void onPreExecute() {
    }

    @Override
    protected void onProgressUpdate(float[]... values) { // values c'est un tableau de tableua , le plus recent c'est celui
        //System.out.println("x y et z : "+ values[0][0]);
        tvX.setText(String.valueOf(values[0][0]));
        tvY.setText(String.valueOf(values[0][1]));
        tvZ.setText(String.valueOf(values[0][2]));



    }
}

