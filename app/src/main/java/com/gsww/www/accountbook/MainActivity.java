package com.gsww.www.accountbook;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.gsww.com.accountbook.R;
import com.gsww.www.accountbook.bean.AccountsBean;
import com.gsww.www.accountbook.utils.DataManager;

import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Toolbar mToolBar;
    private RecyclerView mRecycleView;
    private List<AccountsBean> listAcc;
    private MyAdapter adapter;
    private FloatingActionButton fab;
    private TextView cancel, sure;
    private View dialogView;
    private EditText et_cost_name, et_cost_price;
    private AlertDialog.Builder build;
    private AlertDialog dialog;
    private DatePicker date;
    private int REQUEST_CODE_CONTACT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*DataManager.insertAccount(new AccountsBean(null,"吃饭","20000000",34l));
        DataManager.queryAccountList();*/
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            //透明状态栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            //透明导航栏
            // getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
        setContentView(R.layout.activity_main);

        //版本大于6.0的情况
        if (Build.VERSION.SDK_INT >= 23) {
            String[] permissions = {Manifest.permission.WRITE_EXTERNAL_STORAGE};
            //验证是否许可权限
            for (String str : permissions) {
                if (this.checkSelfPermission(str) != PackageManager.PERMISSION_GRANTED) {
                    //申请权限
                }
                this.requestPermissions(permissions, REQUEST_CODE_CONTACT);
            }

        }
        initView();
        setSupportActionBar(mToolBar);
        initData();

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CODE_CONTACT) {
            Toast.makeText(this, "授权成功", Toast.LENGTH_SHORT).show();
        } else {

            Toast.makeText(this, "授权失败", Toast.LENGTH_SHORT).show();

        }

    }

    private void initData() {

        listAcc = DataManager.queryAccountList();
        if (adapter == null) {
            adapter = new MyAdapter(this, listAcc);

            mRecycleView.setAdapter(adapter);
        } else {
            adapter.notifyDataSetChanged();
        }

    }

    private void initView() {
        mToolBar = (Toolbar) findViewById(R.id.mToolBar);
        mRecycleView = (RecyclerView) findViewById(R.id.rc_accounts);
        fab = (FloatingActionButton) findViewById(R.id.but_fab);
        initDialogView();

        mRecycleView.setLayoutManager(new GridLayoutManager(this, 1));
        fab.setOnClickListener(this);
    }

    private void initDialogView() {

        dialogView = getLayoutInflater().from(this).inflate(R.layout.dialog_add_cost, null);
        et_cost_name = (EditText) dialogView.findViewById(R.id.et_cost_name);
        et_cost_price = (EditText) dialogView.findViewById(R.id.et_cost_price);
        cancel = (TextView) dialogView.findViewById(R.id.tv_cancel);
        date = (DatePicker) dialogView.findViewById(R.id.data_picker);
        sure = (TextView) dialogView.findViewById(R.id.tv_sure);
        cancel.setOnClickListener(this);
        sure.setOnClickListener(this);
        build = new AlertDialog.Builder(this);
        build.setView(dialogView);
        dialog = build.create();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return true;
    }

    @Override
    public void onClick(View v) {
        String des = et_cost_name.getText().toString();
        String price = et_cost_price.getText().toString();
        switch (v.getId()) {
            case R.id.but_fab:
                dialog.show();
                break;
            case R.id.tv_cancel:
                dialog.dismiss();
                break;
            case R.id.tv_sure:
                if (!TextUtils.isEmpty(des) && !TextUtils.isEmpty(price)) {
                    StringBuilder str_date = new StringBuilder();
                    String year = date.getYear() + "";
                    String month = date.getMonth() + "";
                    String day = date.getDayOfMonth() + "";
                    str_date.append(year + "-" + month + "-" + day + "");
                    AccountsBean acc = new AccountsBean(null, des, str_date.toString(), Long.parseLong(price));
                    if (DataManager.insertAccount(acc)) {
                        listAcc.add(acc);
                        initData();
                        dialog.dismiss();
                    } else {
                        Toast.makeText(this, "添加失败", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(this, "请输入消费记录", Toast.LENGTH_SHORT).show();
                }
                break;
        }


    }
}
