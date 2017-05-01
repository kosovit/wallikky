package com.example.wro.walliky.tabOrientation;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.os.AsyncTask;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wro.walliky.R;
import com.example.wro.walliky.bluetoothLink.View_BluetouthLink;
import com.example.wro.walliky.capNXT.ThWorker_CapNXT;
import com.example.wro.walliky.capNXT.View_CapNXT;

/**
 * Created by zna on 02/02/2017.
 */

public class View_OrientationTAB extends LinearLayout{
    private TextView tvX;
    private TextView tvY;
    private TextView tvZ;

    Context context;

    private ImageView image_direction;

    public Button getBtn_acquisition() {
        return btn_acquisition;
    }

    private Button btn_acquisition = null;

    public View_OrientationTAB(Context context) {
        super(context);
        this.context=context;
    }

    public View_OrientationTAB(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context=context;
        init(context, attrs);


    }

    public View_OrientationTAB(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context=context;
        init(context, attrs);

    }

    public TextView getTvX() {
        return tvX;
    }

    public TextView getTvY() {
        return tvY;
    }

    public TextView getTvZ() {
        return tvZ;
    }

    private void init(final Context ctx, AttributeSet attrs) {

        LayoutInflater li = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View v = li.inflate(R.layout.accelerometer, null);
        tvX = (TextView) v.findViewById(R.id.x_axis);
        tvX.setTextColor(Color.RED);
        tvY = (TextView) v.findViewById(R.id.y_axis);
        tvY.setTextColor(Color.BLUE);

        tvZ = (TextView) v.findViewById(R.id.z_axis);
        tvZ.setTextColor(Color.GREEN);

        btn_acquisition = (Button) v.findViewById(R.id.button);

        addView(v);

        TypedArray a = ctx.obtainStyledAttributes(attrs, R.styleable.vue_acc);

        a.recycle();
        startAquisation();
        /*

        */

    }
    public void startAquisation(){
        btn_acquisition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("","execution du thread worker");
                new ThWorker_OrientationTAB(tvX,tvY ,tvZ).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
                if (View_BluetouthLink.isconnected.isChecked())
                new ThWorker_CapNXT(View_CapNXT.txt_compass_NXT).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
                Toast toast = Toast.makeText( context, "Activation de l'ecquisition", Toast.LENGTH_SHORT);
                toast.show();
            }
        });

    }
}
