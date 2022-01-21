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
        String text = "암복호화 테스트 iv는 랜덤_앞에 16바이트 붙여서 보내기";
        cipherText = aes256.encrypt(text);
        System.out.println(text);
        System.out.println(cipherText);
//        System.out.println(aes256.decrypt(cipherText));
    }

    public void dec_btn(View v) throws Exception {
        AES256 aes256 = new AES256();
        System.out.println(aes256.decrypt(cipherText));
    }
}