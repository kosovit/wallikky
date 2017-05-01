package com.example.wro.walliky.capNXT;

/**
 * Created by wro on 31/01/2017.
 */

public class DataModule_Cap_NXT {
    private String nom_mdd ;
    private int direction ;

    public DataModule_Cap_NXT(String nommdd) {
        this.nom_mdd = nommdd;
    }

    public synchronized void setvalues(int x) {
        direction = x % 360;
    }

    public synchronized int getvalues() {
        return direction;
    }


}

