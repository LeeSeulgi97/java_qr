package com.soha.javaqrcode;

import android.util.Log;

import org.json.JSONObject;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class UserInfoTask {

    private Disposable userInfoTask;
    private final String USER_AGENT = "Mozilla/5.0";
    private String email = "eve.holt@reqres.in";
    private String password = "cityslicka";
    private String response;

    public void AutoActionsTask(String email, String phone) {
        this.email = email;
        this.password = phone;
    }

    public void sendUserInfo() {
        Log.e("tmfrl","통신 시작");

        String urlString = ("https://reqres.in/api/login");

        userInfoTask = Observable.fromCallable(()->{
            HttpURLConnection conn;
            URL url = new URL(urlString);

            // url 연결
            conn = (HttpURLConnection) url.openConnection();

            // 서버 접속시 연결 시간
            conn.setConnectTimeout(10000);
            // Read시 연결 시간
            conn.setReadTimeout(100000);

            // 요청방식 선택
            conn.setRequestMethod("POST");

            // 타입설정
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("Accept", "application/json");

            // User-Agent 값 설정
//            conn.setRequestProperty("User-Agent", USER_AGENT);

            // OutputStream으로 Post 데이터를 넘겨주겠다는 옵션
            conn.setDoOutput(true);

            // InputStream으로 서버로 부터 응답을 받겠다는 옵션
            conn.setDoInput(true);

            // 서버로 전달할 Json객체 생성
            JSONObject json = new JSONObject();

            // Json객체에 유저의 name, phone, address 값 세팅
            // Json의 파라미터는 Key, Value 형식
            json.put("email", email);
            json.put("password", password);

            // Request Body에 데이터를 담기위한 OutputStream 객체 생성
            OutputStream outputStream;
            outputStream = conn.getOutputStream();
            outputStream.write(json.toString().getBytes());
            outputStream.flush();

            // 실제 서버로 Request 요청 하는 부분 (응답 코드를 받음, 200은 성공, 나머지 에러)
            int response = conn.getResponseCode();
            String responseMessage =  conn.getResponseMessage();

            InputStream inputStream;
            inputStream = conn.getInputStream();
            String datav = String.valueOf(inputStream.read());


            Log.e("tmfrl","통신값"+response);
            Log.e("tmfrl","통신값2"+responseMessage);
            Log.e("tmfrl","통신값"+datav);
//            response = String.valueOf(response);
            // 접속해지
            conn.disconnect();
            return response;
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(s -> {
                    userInfoTask.dispose();
                }, throwable -> throwable.printStackTrace());
    }
}