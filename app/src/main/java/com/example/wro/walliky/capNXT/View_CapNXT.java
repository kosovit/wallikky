package com.example.wro.walliky.capNXT;

import android.bluetooth.BluetoothAdapter;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
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

/**
 * Created by zna on 02/02/2017.
 */

public class View_CapNXT  extends LinearLayout{
    public static TextView txt_compass_NXT;

    public View_CapNXT(Context context) {
        super(context);
    }

    public View_CapNXT(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);

    }

    public View_CapNXT(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);

    }
    private void init(final Context ctx, AttributeSet attrs) {

        LayoutInflater li = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View v = li.inflate(R.layout.capnxt, null);
        txt_compass_NXT = (TextView) v.findViewById(R.id.textView_compassNXT_id);

        addView(v);
        TypedArray a = ctx.obtainStyledAttributes(attrs, R.styleable.vue_acc);

        a.recycle();


    }
}
