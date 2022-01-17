package com.soha.javaqrcode;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity{


    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void qrbtn(View v){
        Intent intent = new Intent(this,QrCode.class);
        startActivity(intent);
    }

    public void randombtn(View v){
        int random_num;
        random_num = (int) (Math.random()*1000000);
        Toast.makeText(getApplicationContext(), "랜덤 숫자"+ random_num,
                Toast.LENGTH_LONG).show();
    }

    public void passwordbtn(View v){
        Intent intent = new Intent(this,password.class);
        startActivity(intent);
    }
}
