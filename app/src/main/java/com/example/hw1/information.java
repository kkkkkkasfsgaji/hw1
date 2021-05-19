package com.example.hw1;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class information extends AppCompatActivity {
    TextView info,DeviceName,Mac;
    ImageButton back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test);
        getSupportActionBar().hide();
        info=findViewById(R.id.info);
        DeviceName=findViewById(R.id.DeviceName);
        Mac=findViewById(R.id.MacAddress);
        Bundle bundle=this.getIntent().getExtras();
        String infomation=bundle.getString("infomation");
        info.setText(infomation);
        DeviceName.setText("裝置名稱："+bundle.getString("DN"));
        Mac.setText("裝置位置："+bundle.getString("MA"));
        info.setText("裝置訊號："+bundle.getString("IN"));
        back=findViewById(R.id.button_back);
        back.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}