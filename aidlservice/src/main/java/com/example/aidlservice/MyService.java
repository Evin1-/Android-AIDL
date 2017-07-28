package com.example.aidlservice;

import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import com.example.aidlapp.IEdwinInterface;
import com.example.aidlapp.User;

import java.util.ArrayList;

public class MyService extends Service {
    private static final String TAG = "MyServiceTAG_";

    private ArrayList<User> users;
    private ArrayList<ComponentName> componentNames;

    public MyService() {
        users = new ArrayList<>();
        componentNames = new ArrayList<>();
    }

    @Override
    public IBinder onBind(Intent intent) {
        componentNames.add(intent.getComponent());

        Log.d(TAG, "onBind: " + componentNames);
        return binder;
    }

    private final IEdwinInterface.Stub binder = new IEdwinInterface.Stub() {

        @Override
        public void addUser(User user) throws RemoteException {
            Log.d(TAG, "addUser: ");
            users.add(user);
        }

        @Override
        public User retrieveUser(int position) throws RemoteException {
            Log.d(TAG, "retrieveUser: " + users + "\n" + componentNames);
            if (position < users.size()) {
                return users.get(position);
            }
            return null;
        }
    };
}
