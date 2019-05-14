package com.adojayfan.interview.mvc;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import com.adojayfan.interview.R;
import com.adojayfan.interview.model.DataManager;
import com.adojayfan.interview.model.User;

public class MVCActivity extends AppCompatActivity implements DataManager.OnModelListener<User> {

    private DataManager dataManager;

    private TextView tvUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mvc);

        tvUser = findViewById(R.id.tv_user_info);

        dataManager = new DataManager(getApplicationContext());
    }

    public void getUserInfo(View view) {
        dataManager.getUserInfo("12313321233", MVCActivity.this);
    }

    @Override
    public void onSuccess(User user) {
        tvUser.setText("姓名:" + user.getName() + "   年龄:" + user.getAge());
    }

    @Override
    public void onFailed(String errMsg) {
        Toast.makeText(this, errMsg, Toast.LENGTH_SHORT).show();
    }
}
