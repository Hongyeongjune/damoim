package com.yeongjae.damoim.view.signup.phone;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.app.Activity;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.yeongjae.damoim.R;
import com.yeongjae.damoim.data.SignUp;
import com.yeongjae.damoim.data.VerificationCode;
import com.yeongjae.damoim.data.source.searchInformation.SearchInformationRepository;
import com.yeongjae.damoim.util.ToastUtils;
import com.yeongjae.damoim.view.signup.age.SignUpAgeActivity;
import com.yeongjae.damoim.view.signup.phone.presenter.SignUpPhoneContract;
import com.yeongjae.damoim.view.signup.phone.presenter.SignUpPhonePresenter;
import com.yeongjae.damoim.view.verificationcode.VerificationCodeActivity;

import java.util.ArrayList;

public class SignUpPhoneActivity extends AppCompatActivity implements SignUpPhoneContract.View {

    private Context context;
    private EditText etPhone;
    private Button btnPhone;
//    private EditText etVerificationCode;
//    private Button btnVerificationCode;
//    private LinearLayout linearVerificationCode;
    private SignUp signUp;
    private SignUpPhoneContract.Presenter presenter;
    private VerificationCode verificationCode;

    private SmsManager smsManager;
    private static final int PERMISSION_REQUEST_CODE = 1;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_phone);
        
        init();
    }

    private void init() {

        context = getApplicationContext();
        etPhone = (EditText) findViewById(R.id.et_signup_phone);
        btnPhone = (Button) findViewById(R.id.btn_signup_phone);
//        etVerificationCode = (EditText) findViewById(R.id.et_signup_verification_code);
//        btnVerificationCode = (Button) findViewById(R.id.btn_signup_verification_code);
//        linearVerificationCode = (LinearLayout) findViewById(R.id.linear_signup_verification_code);
        presenter = new SignUpPhonePresenter(this);

        verificationCode = SearchInformationRepository.getInstanceVerificationCode();

        Intent intent = getIntent();
        signUp = (SignUp) intent.getSerializableExtra("SignUp");

        btnPhone.setOnClickListener(v -> presenter.startVerificationCode(etPhone.getText().toString()));
//        btnVerificationCode.setOnClickListener(v -> presenter.requestRepeat(etPhone.getText().toString()));


    }

    @Override
    public void showErrorMessage(String message) {
        ToastUtils.showToast(context, message);
    }

    @Override
    public void startNextSignUpActivity(String phone, String code) {
        Intent intent = new Intent(context, VerificationCodeActivity.class);
        signUp.setPhone(phone);
        intent.putExtra("SignUp" , signUp);

        verificationCode.setPhone(phone);
        verificationCode.setVerificationCode(code);
        verificationCode.setType("SignUp");
        intent.putExtra("VerificationCode", verificationCode);

        startActivity(intent);
    }

    @Override
    public void showVerificationMessage(String message) {
        ToastUtils.showToast(context, message);
    }

    @Override
    public void sendSMS(String phone, String verificationCode) {

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {

            if (checkSelfPermission(Manifest.permission.SEND_SMS)
                    == PackageManager.PERMISSION_DENIED) {

                Log.d("permission", "permission denied to SEND_SMS - requesting it");
                String[] permissions = {Manifest.permission.SEND_SMS};

                requestPermissions(permissions, PERMISSION_REQUEST_CODE);

            }
        }

        PendingIntent sentIntent = PendingIntent.getBroadcast(this, 0, new Intent("SMS_SENT_ACTION"), 0);
        PendingIntent deliveredIntent = PendingIntent.getBroadcast(this, 0, new Intent("SMS_DELIVERED_ACTION"), 0);

        registerReceiver(new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                switch(getResultCode()){
                    case Activity.RESULT_OK:
                        // 전송 성공
                        ToastUtils.showToast(context, "전송 완료");
                        break;
                    case SmsManager.RESULT_ERROR_GENERIC_FAILURE:
                        // 전송 실패
                        ToastUtils.showToast(context, "전송 실패");
                        break;
                    case SmsManager.RESULT_ERROR_NO_SERVICE:
                        // 서비스 지역 아님
                        ToastUtils.showToast(context, "서비스 지역이 아닙니다.");
                        break;
                    case SmsManager.RESULT_ERROR_RADIO_OFF:
                        // 무선 꺼짐
                        ToastUtils.showToast(context, "무선이 꺼져있습니다.");
                        break;
                    case SmsManager.RESULT_ERROR_NULL_PDU:
                        // PDU 실패
                        ToastUtils.showToast(context, "PDU NULL");
                        break;
                }
            }
        }, new IntentFilter("SMS_SENT_ACTION"));

        registerReceiver(new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                switch (getResultCode()){
                    case Activity.RESULT_OK:
                        // 도착 완료
                        ToastUtils.showToast(context, "도착 완료");
                        break;
                    case Activity.RESULT_CANCELED:
                        // 도착 안됨
                        ToastUtils.showToast(context, "도착 실패");
                        break;
                }
            }
        }, new IntentFilter("SMS_DELIVERED_ACTION"));

        SmsManager mSmsManager = SmsManager.getDefault();
        mSmsManager.sendTextMessage(phone, null, "인증번호 : " + verificationCode, sentIntent, deliveredIntent);

//        //송신 인텐트
//        PendingIntent sentPI = PendingIntent.getBroadcast(this, 0, new Intent("SMS_SENT"), 0);
//        //수신 인텐트
//        PendingIntent recvPI = PendingIntent.getBroadcast(this, 0, new Intent("SMS_DELIVERED"), 0);
//
//        registerReceiver(mSentReceiver, new IntentFilter("SMS_SENT"));
//        registerReceiver(mRecvReceiver, new IntentFilter("SMS_DELIVERED"));
//
//
//        smsManager.sendTextMessage(phone, null, "인증번호 : " + verificationCode, sentPI, recvPI);


    }

//    BroadcastReceiver mSentReceiver = new BroadcastReceiver() {
//        @Override
//        public void onReceive(Context context, Intent intent) {
//            switch (getResultCode()){
//                case RESULT_OK:
//                    ToastUtils.showToast(context, "송신 성공");
//                    break;
//                case SmsManager.RESULT_ERROR_GENERIC_FAILURE:
//                    ToastUtils.showToast(context, "ERROR_GENERIC_FAILURE");
//                    break;
//                case SmsManager.RESULT_ERROR_NO_SERVICE:
//                    ToastUtils.showToast(context, "ERROR_NO_SERVICE");
//                    break;
//                case SmsManager.RESULT_ERROR_NULL_PDU:
//                    ToastUtils.showToast(context, "ERROR_NULL_PDU");
//                    break;
//                case SmsManager.RESULT_ERROR_RADIO_OFF:
//                    ToastUtils.showToast(context, "ERROR_RADIO_OFF");
//                    break;
//            }
//        }
//    };
//
//    BroadcastReceiver mRecvReceiver = new BroadcastReceiver() {
//        @Override
//        public void onReceive(Context context, Intent intent) {
//            switch (getResultCode()){
//                case RESULT_OK:
//                    ToastUtils.showToast(context, "SMS Delivered");
//                    break;
//                case RESULT_CANCELED:
//                    ToastUtils.showToast(context, "SMS Delivered Fail");
//                    break;
//            }
//        }
//    };


//    @Override
//    public void showInputVerificationCode() {
//        int view = linearVerificationCode.getVisibility();
//
//        if(view == View.INVISIBLE) {
//            linearVerificationCode.setVisibility(View.VISIBLE);
//        }
//    }
}
