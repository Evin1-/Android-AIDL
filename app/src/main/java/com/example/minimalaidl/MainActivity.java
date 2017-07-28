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

import com.example.aidlapp.IRemoteInterface;
import com.example.aidlapp.User;

import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "MainActivityTAG_";

    private IRemoteInterface remoteInterface;
    private ServiceConnection serviceConnection = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            remoteInterface = IRemoteInterface.Stub.asInterface(iBinder);
            Log.d(TAG, "onServiceConnected: ");
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            remoteInterface = null;
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
            remoteInterface.addUser(new User("Edwin", 26, new Date()));
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void loadMagic(View view) {
        try {
            User user = remoteInterface.retrieveUser(1);
            Log.d(TAG, "loadMagic: " + user);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }


    public void retrieveMagic(View view) {
        try {
            List<User> users = remoteInterface.retrieveUsers();
            Log.d(TAG, "retrieveMagic: " + users);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
