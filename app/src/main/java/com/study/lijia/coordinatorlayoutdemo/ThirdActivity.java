package com.study.lijia.coordinatorlayoutdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class ThirdActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

//        final TextView depentent = (TextView) findViewById(R.id.depentent);
//        depentent.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                ViewCompat.offsetTopAndBottom(v, 5);
//            }
//        });
    }
}
