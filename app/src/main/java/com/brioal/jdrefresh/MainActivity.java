package com.brioal.jdrefresh;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    //进入普通刷新
    public void enterNormal(View view) {
        Intent intent = new Intent(this, NormalActivity.class);
        startActivity(intent);
    }

    //进入京东的刷新
    public void enterJD(View view) {
        Intent intent = new Intent(this, JDActivity.class);
        startActivity(intent);
    }

    //进入天猫的刷新
    public void enterTM(View view) {
        Intent intent = new Intent(this, TMActivity.class);
        startActivity(intent);
    }
}
