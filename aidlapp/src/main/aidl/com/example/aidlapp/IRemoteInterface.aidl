// IEdwinInterface.aidl
package com.example.aidlapp;
// Declare any non-default types here with import statements
import com.example.aidlapp.User;

interface IRemoteInterface {
    void addUser(in User user);
    User retrieveUser(int position);
    List<User> retrieveUsers();
}
