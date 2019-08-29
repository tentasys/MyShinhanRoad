//package com.example.shinple.Notification;
//
//import com.google.firebase.iid.FirebaseInstanceId;
//import com.google.firebase.iid.FirebaseInstanceIdService;
//
//public class MyFirebaseInstanceIdService extends FirebaseInstanceIdService{
//    @Override
//    public void onTokenRefresh(){
//        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
//        sendRegistrationToServer(refreshedToken);
//    }
//
//    private void sendRegistrationToServer(String token) {
//        //TODO: 디바이스 토큰이 생성되거나 재생성 될 시 동작할 코드 작성
//    }
//}