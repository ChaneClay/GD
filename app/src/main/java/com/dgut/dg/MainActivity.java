package com.dgut.dg;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.dgut.dg.Activity.CodeVerifyActivity;
import com.dgut.dg.Activity.HomeActivity;
import com.dgut.dg.entity.PersonalInfo;
import com.dgut.dg.Utils.RandomData;
import com.dgut.dg.Utils.SendEmail;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import pl.com.salsoft.sqlitestudioremote.SQLiteStudioService;

public class MainActivity extends AppCompatActivity {

    private Button mBtnLogin;
    private EditText mEtMail;
    private Button mBtnReg;

    String TAG = "mmm";

    private long verificationCode=0;            //生成的验证码
    private String email;                       //邮箱



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        //开启service
        SQLiteStudioService.instance().start(this);

        mEtMail = findViewById(R.id.et_mail);
        mBtnLogin = findViewById(R.id.btn_login);
        mBtnReg = findViewById(R.id.btn_register);

        mBtnLogin.setEnabled(false);
        mBtnLogin.setClickable(false);

        mEtMail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String mail = mEtMail.getText().toString();
                boolean flag = isEmail(mail);
                if (flag){
                    Toast.makeText(MainActivity.this, "格式正确" , Toast.LENGTH_SHORT).show();
                    mBtnLogin.setClickable(true);
                    mBtnLogin.setEnabled(true);
                }
            }
        });



        mBtnLogin = findViewById(R.id.btn_login);
        mBtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                email=mEtMail.getText().toString();

                RandomData rn = new RandomData();
                verificationCode = rn.getRandomNumber(6);

                sendVerificationCode(email, verificationCode);  //发送验证码

                Intent intent = new Intent(MainActivity.this, CodeVerifyActivity.class);



                Bundle bundle = new Bundle();

                bundle.putString("code", String.valueOf(verificationCode));
                PersonalInfo.setEmail(email);
                intent.putExtras(bundle);

                startActivity(intent);

            }
        });


        mBtnReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });



    }


    public static boolean isEmail(String email){
        if (null==email || "".equals(email)) return false;
        Pattern p =  Pattern.compile("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*");//复杂匹配
        Matcher m = p.matcher(email);

        return m.matches();

    }


//    发送验证码
    private void sendVerificationCode(final String email, long verificationCode) {

        try {
            new Thread() {
                @Override
                public void run() {
                    super.run();
                    try {

//                        RandomNumber rn = new RandomNumber();
//                        verificationCode = rn.getRandomNumber(6);
                        SendEmail se = new SendEmail(email);
                        se.sendHtmlEmail(verificationCode);//发送html邮件


//                        Looper.prepare();
//                        Toast.makeText(MainActivity.this,"发送成功", Toast.LENGTH_LONG).show();
//                        Looper.loop();// 进入loop中的循环，查看消息队列


                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }.start();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

//    判断输入的验证码是否正确
    private void judgeVerificationCode() {
        if(Integer.parseInt(mEtMail.getText().toString())==verificationCode){    //验证码和输入一致
            Toast.makeText(MainActivity.this,"验证成功", Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(MainActivity.this, "验证失败", Toast.LENGTH_LONG).show();
        }
    }






}