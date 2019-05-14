package com.adojayfan.interview;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import com.adojayfan.interview.mvc.MVCActivity;
import com.adojayfan.interview.mvp.MVPActivity;
import com.adojayfan.interview.widget.FanShift;

public class MainActivity extends AppCompatActivity {

    private FanShift fanShift;
    private EditText etShift;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fanShift = findViewById(R.id.fan);
        etShift = findViewById(R.id.et_shift);

        fanShift.setFanImage(android.R.drawable.btn_dialog);

        fanShift.setShiftChangeListener(new FanShift.ShiftChangeListener() {
            @Override
            public void shiftChanged(int currentShift) {
                View inflate = null;
                if (currentShift == 1) {
                    inflate = LayoutInflater.from(getApplicationContext())
                            .inflate(R.layout.layout_temp_one, null, false);
                } else if (currentShift == 2) {
                    inflate = LayoutInflater.from(getApplicationContext())
                            .inflate(R.layout.layout_temp_two, null, false);
                }
                if (inflate != null) {
                    fanShift.addContentView(inflate);
                }
            }
        });

        fanShift.setCurrentShift(1);

        fanShift.rotateFan(true);
    }

    public void changeShift(View view) {
        String trim = etShift.getText().toString().trim();
        if (TextUtils.isEmpty(trim)) {
            return;
        }
        fanShift.setCurrentShift(Integer.parseInt(trim));
    }

    public void jumpToMVC(View view) {
        startActivity(new Intent(MainActivity.this, MVCActivity.class));
    }

    public void jumpToMVP(View view) {
        startActivity(new Intent(MainActivity.this, MVPActivity.class));
    }
}
