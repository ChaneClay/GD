package com.dgut.dg.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.dgut.dg.R;

import butterknife.OnClick;


public class CodeVerifyActivity extends AppCompatActivity {

    private Button mBtnSub;
    private EditText mEtCode;

    private String TAG = "code";
    String oriCode = "";
    String correctCode = "";
    String email = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_code_verify);
        mBtnSub = findViewById(R.id.btn_sub);
        mEtCode = findViewById(R.id.et_code);


        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        correctCode = bundle.getString("code");




        mEtCode.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                oriCode = mEtCode.getText().toString();

                OnClick onClick = new OnClick();
                if (correctCode.equals(oriCode)){
                    Log.i(TAG, "onCreate: 验证码正确");

                    // 直接跳转
                    Intent intent = new Intent(CodeVerifyActivity.this, HomeActivity.class);


                    startActivity(intent);

                }else {
                    mBtnSub.setOnClickListener(onClick);
                    Log.i(TAG, "afterTextChanged: correctCode: " + correctCode);
                    Log.i(TAG, "afterTextChanged: code: " + oriCode);
                }
            }
        });



    }

    class OnClick implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            if (correctCode.equals(oriCode)){
                Intent intent = new Intent(CodeVerifyActivity.this, HomeActivity.class);
                startActivity(intent);
            }else {
                Toast.makeText(CodeVerifyActivity.this, "验证码错误", Toast.LENGTH_SHORT).show();
                mEtCode.setText("");
            }

        }
    }
}