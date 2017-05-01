package com.example.wro.walliky.tabOrientation;

/**
 * Created by zna on 17/01/2017.
 */

public class DataModule_OrientationTAB {

    private String nommdd;
    private float mLastX;
    private float mLastY;
    private float[] aux = new float[3];
    private float mLastZ;

    public DataModule_OrientationTAB(String nommdd) {
        this.nommdd = nommdd;
    }

    public synchronized void setvalues(float x, float y, float z) {
        aux[0] = x;
        aux[1] = y;
        aux[2] = z;
    }

    public synchronized float[] getvalues() {

        return aux;
    }


}
