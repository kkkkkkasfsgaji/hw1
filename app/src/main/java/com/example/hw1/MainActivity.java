package com.example.hw1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.Manifest;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.le.BluetoothLeScanner;
import android.bluetooth.le.ScanCallback;
import android.bluetooth.le.ScanResult;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.Iterator;

public class MainActivity extends AppCompatActivity  {
    BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
    private boolean isScanning = false;
    ArrayList<scan> findDevice = new ArrayList<>();
    RecyclerView recyclerView;
    recycle mAdapter;

    BluetoothLeScanner mBluetoothLeScanner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        checkPermission(mBluetoothAdapter);
        bluetoothScan(mBluetoothAdapter);
    }

    private void bluetoothScan(BluetoothAdapter  mBluetoothAdapter) {
        mBluetoothLeScanner = mBluetoothAdapter.getBluetoothLeScanner();
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        recyclerView.setAdapter(mAdapter);

        final Button btScan = findViewById(R.id.Startscan);
        btScan.setOnClickListener((v) -> {
            if (!mBluetoothAdapter.isEnabled()) {
                Toast.makeText(getApplicationContext(), "Please enable BlueTooth", Toast.LENGTH_SHORT).show();
            } else {
                if (!isScanning) {
                    if (mBluetoothAdapter.isEnabled()) {
                        btScan.setText("關閉掃描");
                        findDevice.clear(); // clear items
                        mBluetoothLeScanner.startScan(mLeScanCallback);
                        Toast.makeText(getApplicationContext(), "started", Toast.LENGTH_SHORT).show();
                        mAdapter = new recycle(findDevice);
                        recyclerView.setAdapter(mAdapter);
                        isScanning = true;
                    }
                } else {
                    isScanning = false;
                    btScan.setText("開啟掃描");
                    findDevice.clear();
                    mBluetoothLeScanner.stopScan(mLeScanCallback);
                    mAdapter.clearDevice();
                }
            }
        });
    }
    private void checkPermission(BluetoothAdapter  mBluetoothAdapter) {
        int hasGone = ContextCompat.checkSelfPermission(this,Manifest.permission.ACCESS_FINE_LOCATION);
        if (hasGone != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MainActivity.this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    1);
            ActivityCompat.requestPermissions(MainActivity.this,
                    new String[]{Manifest.permission.ACCESS_COARSE_LOCATION},
                    1);
        }
        if (!getPackageManager().hasSystemFeature(PackageManager.FEATURE_BLUETOOTH_LE)) {
            Toast.makeText(this, "Not support Bluetooth", Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    protected void onPause() {
        super.onPause();
        final Button btScan = findViewById(R.id.Startscan);
        isScanning = false;
        btScan.setText("開啟掃描");
        mBluetoothLeScanner.stopScan(mLeScanCallback);
    }

    private ScanCallback mLeScanCallback = new ScanCallback() {
        @Override
        public void onScanResult(int callbackType, ScanResult result) {
            BluetoothDevice device = result.getDevice();
            int mRssi = result.getRssi();
            findDevice.add(new scan(device.getName()
                    , String.valueOf(mRssi)
                    , device.getAddress()));
            ArrayList newList = getSingle(findDevice);
            mAdapter = new recycle(newList);
            recyclerView.setAdapter(mAdapter);
        }
    };
    private ArrayList getSingle(ArrayList list) {
        ArrayList tempList = new ArrayList<>();
        try {
            Iterator it = list.iterator();
            while (it.hasNext()) {
                Object obj = it.next();
                if (!tempList.contains(obj)) {
                    tempList.add(obj);
                } else {
                    tempList.set(getIndex(tempList, obj), obj);
                }
            }
            return tempList;
        } catch (ConcurrentModificationException e) {
            return tempList;
        }
    }
    private int getIndex(ArrayList temp, Object obj) {
        for (int i = 0; i < temp.size(); i++) {
            if (temp.get(i).toString().contains(obj.toString())) {
                return i;
            }
        }
        return -1;
    }
}
