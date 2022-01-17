package com.soha.javaqrcode;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class password extends AppCompatActivity {

    String cipherText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password);
    }

    public void enc_btn(View v) throws Exception {

        AES256 aes256 = new AES256();
        String text = "테스트문구 확인1234!";
        cipherText = aes256.encrypt(text);
        System.out.println(text);
        System.out.println(cipherText);
        System.out.println(aes256.decrypt(cipherText));
    }

    public void dec_btn(View v){
        Intent intent = new Intent(this,QrCode.class);
        startActivity(intent);
    }
}