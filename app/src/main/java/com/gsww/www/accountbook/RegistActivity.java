package com.gsww.www.accountbook;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.gsww.com.accountbook.R;
import com.gsww.www.accountbook.bean.Personal;
import com.gsww.www.accountbook.utils.DataManager;


/**
 * Author : luweicheng on 2017/3/29 0029 09:50
 * E-mail ：1769005961@qq.com
 * GitHub : https://github.com/luweicheng24
 */

public class RegistActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText et_phone;
    private EditText et_psw;
    private Button but_regist;
    private String name;
    private String psw;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regist);
        initView();
    }

    private void initView() {
        et_phone = (EditText) findViewById(R.id.et_reg_name);
        et_psw = (EditText) findViewById(R.id.et_reg_psw);
        but_regist = (Button) findViewById(R.id.but_regist);
        but_regist.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        name = et_phone.getText().toString();
        psw = et_psw.getText().toString();
        if (!TextUtils.isEmpty(name)) {
            if (!TextUtils.isEmpty(name)) {
                if (name.length() == 11) {
                    Personal p = new Personal(null, name, psw);
                    regist(p);
                } else {
                    Toast.makeText(this, "用户名格式错误", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(this, "密码为空", Toast.LENGTH_SHORT).show();
            }

        } else {
            Toast.makeText(this, "用户名为空", Toast.LENGTH_SHORT).show();
        }
    }

    private void regist(Personal p) {
        if (DataManager.regist(p)) {
            Toast.makeText(this, "注册成功", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, LoginActivity.class);
            intent.putExtra("name",name);
            intent.putExtra("password",psw);
            startActivity(intent);
            finish();
        } else {
            Toast.makeText(this, "该用户已经存在", Toast.LENGTH_SHORT).show();
            et_phone.setText("");
            et_psw.setText("");
        }

    }
}