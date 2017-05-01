package com.example.wro.walliky.capNXT;

import android.os.AsyncTask;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by wro on 31/01/2017.
 */

public class ThWorker_CapNXT extends AsyncTask<Void, Integer, Void> {
    private TextView tvX;
    private Calendar cal;
    private SimpleDateFormat sdf;
    private DataModule_Cap_NXT mdd_cap_nxt;


    public ThWorker_CapNXT(TextView tvX) {
        this.tvX = tvX;

    }

    @Override
    protected Void doInBackground(Void... params) {
        mdd_cap_nxt = new DataModule_Cap_NXT("mdd_cap_nxt");
        Read_CapNXT ceThreadLisCapteur = new Read_CapNXT(mdd_cap_nxt, "Thread cap listnner");
        ceThreadLisCapteur.start();
Integer v;
        for (int i = 0; i < 5000; i++) {
            try {
                v = Integer.valueOf(mdd_cap_nxt.getvalues());
                publishProgress(v);
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
    protected void onProgressUpdate(Integer... values) {
        cal = Calendar.getInstance();
        sdf = new SimpleDateFormat("HH:mm:ss");
        tvX.setText("["+sdf.format(cal.getTime())+"] " + values[0].toString());


    }
}


