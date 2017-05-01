package com.example.wro.walliky.capNXT;

import android.util.Log;

import static com.example.wro.walliky.bluetoothLink.View_BluetouthLink.monBTNxtCom;

/**
 * Created by zna on 31/01/2017.
 */

public class Read_CapNXT extends Thread {

    private DataModule_Cap_NXT mdd_cap;
    private String nom_mdd;

    public Read_CapNXT(DataModule_Cap_NXT monmdd, String nom) {
        super();
        this.mdd_cap = monmdd;
        this.nom_mdd = nom;
    }
    @Override
    public void run() {
        String message= "33";
        int val = 10; // default value
        while (true){
            try {
                message = monBTNxtCom.lireMessage();
                Log.e("","NXT msg: "+ message);
            val = Integer.valueOf(message);
                this.mdd_cap.setvalues(val);
                sleep(500); //le cap sera récupéré toutes les 500ms.
            } catch (InterruptedException e) {
                Log.e("","error in converting NXT_msg to integer !");
            }
        }


}
}
