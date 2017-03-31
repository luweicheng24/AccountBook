package com.gsww.www.accountbook;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.gsww.com.accountbook.R;
import com.gsww.www.accountbook.bean.Personal;
import com.gsww.www.accountbook.utils.DataManager;
import com.gsww.www.accountbook.utils.L;
import com.gsww.www.accountbook.utils.SharePrefenceUtils;

/**
 * Author : luweicheng on 2017/3/28 0028 17:40
 * E-mail ：1769005961@qq.com
 * GitHub : https://github.com/luweicheng24
 */

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText et_name, et_psw;
    private Button but_login;
    private String name, psw;
    private TextView tv_regist;
    private TextView tv_forget;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_in);
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
        initView();
        Intent i = getIntent();
        String name = i.getStringExtra("name");
        String psw = i.getStringExtra("password");
        if(name!=null&&psw!=null){
            et_psw.setText(psw);
            et_name.setText(name);
        }
    }

    private void initView() {
        et_name = (EditText) findViewById(R.id.et_name);
        et_psw = (EditText) findViewById(R.id.et_psw);
        but_login = (Button) findViewById(R.id.but_login);
        tv_regist = (TextView) findViewById(R.id.tv_regist);
        tv_forget = (TextView) findViewById(R.id.tv_forget);
        but_login.setOnClickListener(this);
        tv_regist.setOnClickListener(this);
        tv_forget.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        name = et_name.getText().toString();
        psw = et_psw.getText().toString();
        switch(v.getId()){
            case R.id.but_login:
                if (!TextUtils.isEmpty(name)) {
                    if (!TextUtils.isEmpty(name)) {
                        if (name.length() == 11) {
                            login(name,psw);
                        } else {
                            Toast.makeText(this, "用户名格式错误", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(this, "密码为空", Toast.LENGTH_SHORT).show();
                    }

                } else {
                    Toast.makeText(this, "用户名为空", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.tv_regist:
                startActivity(new Intent(this,RegistActivity.class));
                break;
            case R.id.tv_forget:
                Toast.makeText(this, "活该", Toast.LENGTH_SHORT).show();
                break;
        }


    }

    /**
     * 登录业务类
     * @param name
     * @param psw
     */
    private void login(String name, String psw) {
        Personal p = new Personal(null,name,psw);
       Boolean isSuccess =  DataManager.login(p);
        if(isSuccess){
            Toast.makeText(this, "登录成功", Toast.LENGTH_SHORT).show();
            Boolean isOk = SharePrefenceUtils.getInstance(this).writeLoginMessage(name,psw);
            if(isOk) {
                L.e("登录信息保存成功");
                startActivity(new Intent(this,MainActivity.class));
            }else{
                Toast.makeText(this, "用户信息不存失败", Toast.LENGTH_SHORT).show();
            }
            finish();
        }else{
            Toast.makeText(this, "账户不存在", Toast.LENGTH_SHORT).show();
            et_name.setText("");
            et_psw.setText("");
        }

    }
}
