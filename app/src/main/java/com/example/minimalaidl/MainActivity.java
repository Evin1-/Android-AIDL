package com.example.minimalaidl;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.aidlapp.IEdwinInterface;
import com.example.aidlapp.User;

import java.util.Date;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "MainActivityTAG_";

    private IEdwinInterface edwinInterface;
    private ServiceConnection serviceConnection = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            edwinInterface = IEdwinInterface.Stub.asInterface(iBinder);
            Log.d(TAG, "onServiceConnected: ");
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            edwinInterface = null;
            Log.d(TAG, "onServiceDisconnected: ");
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        connectService();
    }

    private void connectService() {
        Intent intent = new Intent("com.example.aidlservice.MyService");
        intent.setPackage("com.example.aidlservice");
        bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE);
    }

    public void saveMagic(View view) {
        try {
            edwinInterface.addUser(new User("Edwin", 26, new Date()));
        } catch (RemoteException e) {
            Log.d(TAG, "saveMagic: " + e);
            e.printStackTrace();
        }
    }

    public void loadMagic(View view) {
        try {
            User user = edwinInterface.retrieveUser(1);
            Log.d(TAG, "loadMagic: " + user);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }


}
