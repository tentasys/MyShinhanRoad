package com.example.shinple;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class BackgroundTask extends AsyncTask<Void, Void, String> {

    String target;
    String param1;

    //TODO: 서버 주소 바뀌면 여기 바꿀것

    static public String server = "https://50309f89.ngrok.io/";
//    static public String server = "http://192.168.1.156/";
    // 서버 주소 저장
    public BackgroundTask(String target) {
        super();
        this.target = server + target;
    }

    // parameter 값 받기
    public BackgroundTask(String target, String param1) {
        super();
        this.target = server + target;
        if (param1 != null){
            this.param1 = param1;
        }
    }
    public BackgroundTask() {
        super();
    }


    @Override
    protected String doInBackground(Void... voids) {
        try{
            URL url = new URL(target);//URL 객체 생성

            //URL을 이용해서 웹페이지에 연결하는 부분
            HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();

            //parameter가 있다면 붙여서
            if(param1 != null){
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);

                DataOutputStream wr = new DataOutputStream(httpURLConnection.getOutputStream());
                wr.writeBytes(param1);
                wr.flush();
                wr.close();
            }
            //바이트단위 입력스트림 생성 소스는 httpURLConnection
            InputStream inputStream = httpURLConnection.getInputStream();

            //웹페이지 출력물을 버퍼로 받음 버퍼로 하면 속도가 더 빨라짐
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String temp;

            //문자열 처리를 더 빠르게 하기 위해 StringBuilder클래스를 사용함
            StringBuilder stringBuilder = new StringBuilder();

            //한줄씩 읽어서 stringBuilder에 저장함
            while((temp = bufferedReader.readLine()) != null){
                stringBuilder.append(temp + "\n");//stringBuilder에 넣어줌
            }

            //사용했던 것도 다 닫아줌
            bufferedReader.close();
            inputStream.close();
            httpURLConnection.disconnect();
            return stringBuilder.toString().trim();//trim은 앞뒤의 공백을 제거함

        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(String result) {
        if(result != null) {
        }
    }
}
