package com.dgut.dg.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bigkoo.pickerview.adapter.ArrayWheelAdapter;
import com.contrarywind.listener.OnItemSelectedListener;
import com.contrarywind.view.WheelView;
import com.dgut.dg.R;

import java.util.ArrayList;
import java.util.List;

/**
 * desc:
 * author: Created by lixiaotong on 2019-07-30
 * e-mail: 516030811@qq.com
 */
public class TestCircleWheelViewActivity extends AppCompatActivity {

    private Button mBtnSave;
    private Button mBtnCancel;
    private String data;
    Intent intent;
    private String flag = "0";


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_circle_wheelview);

        intent = getIntent();
        Bundle bundle = intent.getExtras();
        flag = bundle.getString("flag");

        if (flag.equals("0")){
            Log.i("TAG", "onCreate: 第一种情况");
        }else if(flag.equals("1")){
            Log.i("TAG", "onCreate: 第二种情况");
        }


        mBtnSave = findViewById(R.id.btn_save);
        mBtnCancel = findViewById(R.id.btn_cancel);

        WheelView wheelView = findViewById(R.id.wheelview);
        wheelView.setTextSize(20);
        wheelView.setLineSpacingMultiplier(2f);
        // wheelView.setDividerWidth(6);
        wheelView.setDividerType(WheelView.DividerType.CIRCLE);

        final List<String> mOptionsItems = new ArrayList<>();

        if (flag.equals("0")){
            for (int i = 0; i < 50; i++) {
                mOptionsItems.add((150+i)+"cm");
            }
        }else {
            for (int i = 0; i < 50; i++) {
                mOptionsItems.add((45+i)+"kg");
            }
        }




        wheelView.setAdapter(new ArrayWheelAdapter(mOptionsItems));
        wheelView.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(int index) {

                data = mOptionsItems.get(index);
            }
        });


        mBtnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (flag.equals("0")){
                    intent.putExtra("height", data);
                    setResult(0x123, intent);
                }else {
                    intent.putExtra("weight", data);
                    setResult(0x456, intent);
                }
                TestCircleWheelViewActivity.this.finish();

            }
        });
        mBtnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                TestCircleWheelViewActivity.this.finish();
            }
        });
    }
}
