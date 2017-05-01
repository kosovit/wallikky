package com.example.wro.walliky.bluetoothLink;

import android.bluetooth.BluetoothAdapter;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.os.AsyncTask;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wro.walliky.R;
import com.example.wro.walliky.lib.BTNxtCom;
import com.example.wro.walliky.tabOrientation.ThWorker_OrientationTAB;

/**
 * Created by zna on 02/02/2017.
 */

public class View_BluetouthLink extends LinearLayout {
    public static BTNxtCom monBTNxtCom;

    private Button btn_connect = null;
    private Button btn_blu = null;
    public static CheckBox isenabled = null;
    public static CheckBox isconnected = null;


    public View_BluetouthLink(Context context) {
        super(context);
    }

    public View_BluetouthLink(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);

    }

    public View_BluetouthLink(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);

    }
    private void init(final Context ctx, AttributeSet attrs) {

        LayoutInflater li = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View v = li.inflate(R.layout.bluetoothlink, null);
        btn_blu = (Button) v.findViewById(R.id.btn_activer_bl);
        isenabled = (CheckBox) v.findViewById(R.id.cb_isenabled);

        monBTNxtCom = new BTNxtCom();



        addView(v);

        TypedArray a = ctx.obtainStyledAttributes(attrs, R.styleable.vue_acc);

        a.recycle();

        btn_blu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("","Activation blue !");
                monBTNxtCom.activeBT();

                isenabled.setChecked(true);
            }
        });

        btn_connect = (Button) v.findViewById(R.id.btn_connect);
        isconnected = (CheckBox) v.findViewById(R.id.cb_isconnected);
        btn_connect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("","Connection Au Robot ..!" );
                monBTNxtCom.connexionToNXT();
                isconnected.setChecked(true);
            }
        });
        final BroadcastReceiver mReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                final String action = intent.getAction();

                if (action.equals(BluetoothAdapter.ACTION_STATE_CHANGED)) {
                    final int state = intent.getIntExtra(BluetoothAdapter.EXTRA_STATE,
                            BluetoothAdapter.ERROR);
                    String text = "";
                    switch (state) {
                        case BluetoothAdapter.STATE_OFF:
                            text = "Bluetooth off";
                            break;
                        case BluetoothAdapter.STATE_TURNING_OFF:
                            text = "Turning Bluetooth off...";
                            break;
                        case BluetoothAdapter.STATE_ON:
                            text = "Bluetooth on" ;
                            break;
                        case BluetoothAdapter.STATE_TURNING_ON:
                            text = "Turning Bluetooth on...";
                            break;
                    }
                    Toast toast = Toast.makeText(context, text, Toast.LENGTH_SHORT);
                    toast.show();
                }
            }
        };


    }

}
