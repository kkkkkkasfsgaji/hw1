package com.example.hw1;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class scan {
    private String deviceName;
    private String rssi;
    private String address;

    public scan(String deviceName, String rssi, String address) {
        this.deviceName = deviceName;
        this.rssi = rssi;
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public String getRssi() {
        return rssi;
    }

    public String getDeviceName() {
        return deviceName;
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        scan p = (scan)obj;

        return this.address.equals(p.address);
    }

    @NonNull
    @Override
    public String toString() {
        return this.address;
    }
}