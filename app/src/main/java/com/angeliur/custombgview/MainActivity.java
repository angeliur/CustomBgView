package com.angeliur.custombgview;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    private LinearLayout ll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ll = (LinearLayout) findViewById(R.id.ll);

        CustomBgView bgView = new CustomBgView(this);
        bgView.setBgColor(Color.BLUE);
        bgView.setRightRatio(4.0f);
        ll.addView(bgView);
    }
}
