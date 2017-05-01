package com.example.wro.walliky.lib;

/**
 * Created by michaelrichard on 16/01/2017.
 */

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.UUID;

public class BTNxtCom {

    //MAC du NXT
    final String nxt1 = "00:16:53:0F:01:A1";
    BluetoothAdapter monAdaptBt;
    BluetoothSocket maSock;
    OutputStreamWriter out;
    InputStreamReader sread;

    public void activeBT() {
        monAdaptBt = BluetoothAdapter.getDefaultAdapter();
        if (!monAdaptBt.isEnabled()) {
            monAdaptBt.enable();
        }
        while (!monAdaptBt.isEnabled()) {
        }
    }

    public boolean connexionToNXT() {
        BluetoothDevice monRobot = monAdaptBt.getRemoteDevice(nxt1);

        try {
            maSock = monRobot.createRfcommSocketToServiceRecord(
                    UUID.fromString("00001101-0000-1000-8000-00805F9B34FB"));
            maSock.connect();
            out = new OutputStreamWriter(maSock.getOutputStream());
            sread = new InputStreamReader(maSock.getInputStream());
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    public String lireMessage() {
        char[] buffer = new char[1024];
        int bytes;
        String ch = "";
        if (maSock != null) {

            try {
                bytes = sread.read(buffer);
                ch = new String(buffer);
                ch = ch.substring(ch.lastIndexOf(';') + 1, bytes);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return ch;
    }

    public void ecrireMessage(int pwr, int cap) throws InterruptedException {
        String mess;
        mess = Integer.toString(pwr) + ";" + Integer.toString(cap);
        writeMessage(mess);
    }

    private void writeMessage(String msg) throws InterruptedException {

        if (maSock != null) {
            try {
                out.write(msg);
                out.flush();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
}