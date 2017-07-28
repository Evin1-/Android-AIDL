package com.example.aidlapp;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

/**
 * Created by user on 7/28/17.
 */

public class User implements Parcelable {
    private String name;
    private Integer age;
    private Date dateModified;

    public User(String name, Integer age, Date dateModified) {
        this.name = name;
        this.age = age;
        this.dateModified = dateModified;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Date getDateModified() {
        return dateModified;
    }

    public void setDateModified(Date dateModified) {
        this.dateModified = dateModified;
    }

    protected User(Parcel in) {
        name = in.readString();
        age = in.readByte() == 0x00 ? null : in.readInt();
        long tmpDateModified = in.readLong();
        dateModified = tmpDateModified != -1 ? new Date(tmpDateModified) : null;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        if (age == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeInt(age);
        }
        dest.writeLong(dateModified != null ? dateModified.getTime() : -1L);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<User> CREATOR = new Parcelable.Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", dateModified=" + dateModified +
                '}';
    }
}