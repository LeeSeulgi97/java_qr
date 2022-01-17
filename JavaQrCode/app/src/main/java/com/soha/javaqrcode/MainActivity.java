package com.soha.javaqrcode;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.journeyapps.barcodescanner.CaptureActivity;

public class MainActivity extends AppCompatActivity{
    IntentIntegrator integrator;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

//    et.setOnEditorActionListener(new TextView.OnEditorActionListener() {
//        @Override public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
//            if(actionId == EditorInfo.IME_ACTION_SEARCH){
//                // 키보드 숨기기
//                InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
//                imm.hideSoftInputFromWindow(v.getWindowToken(),0);
//                return true; }
//            return false; }
//    });
    integrator = new IntentIntegrator(this);
    //바코드 안의 텍스트
        integrator.setPrompt("바코드를 사각형 안에 비춰주세요");
         //바코드 인식시 소리 여부
        integrator.setBeepEnabled(false);
        integrator.setBarcodeImageEnabled(true);
        integrator.setCaptureActivity(CaptureActivity.class);
        //바코드 스캐너 시작
        integrator.initiateScan();
    }

    @Override public void onBackPressed() {
        super.onBackPressed();
//        //스캐너 재시작
//        integrator.initiateScan();
    }

    @Override protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);

        if(result != null){
            if(result.getContents() == null){
                Toast.makeText(this, "Cancelled", Toast.LENGTH_LONG).show();
            }else{
//                //qr코드를 읽어서 EditText에 입력해줍니다.
//                et.setText(result.getContents());
                Toast.makeText(this, "Scanned: " + result.getContents(), Toast.LENGTH_SHORT).show();
                integrator.initiateScan();
            }
        }else{
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}
