package com.gzy.paymentlistener;

import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.provider.Settings;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationManagerCompat;

import java.util.Set;

public class MainActivity extends AppCompatActivity {

    private EditText mUrlEt;

    private EditText mKeyEt;

    private TextView mUrlTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initData();
        initListener();
    }

    private void initView() {
        Button listenerBtn = findViewById(R.id.monitor_btn);
        mUrlTv = findViewById(R.id.url_tv);
        mUrlEt = findViewById(R.id.url_et);
        mKeyEt = findViewById(R.id.param_name_et);
        Button confirmBtn = findViewById(R.id.confirm_btn);

        listenerBtn.setOnClickListener(v -> openNotificationListenSettings());

        confirmBtn.setOnClickListener(v -> {
            String url = mUrlEt.getText().toString();
            String key = mKeyEt.getText().toString();
            if (TextUtils.isEmpty(url) || TextUtils.isEmpty(key)) {
                Toast.makeText(MainActivity.this, R.string.url_parameter_empty,
                        Toast.LENGTH_SHORT).show();
            } else {
                Global.url = url;
                Global.key = key;
                mUrlTv.setText(String.format(getString(R.string.url_param), url, key));
                SpUtils.putString(MainActivity.this, SpUtils.URL, url);
                SpUtils.putString(MainActivity.this, SpUtils.KEY, key);
            }
        });
    }

    private void initData() {
        Global.url = SpUtils.getString(this, SpUtils.URL, "");
        Global.key = SpUtils.getString(this, SpUtils.KEY, "");
        mUrlTv.setText(String.format(getString(R.string.url_param), Global.url, Global.key));
    }

    private void initListener() {
        if (isNotificationListenerEnabled()) {
            PackageManager pm = getPackageManager();
            pm.setComponentEnabledSetting(new ComponentName(this, PaymentNotificationListenerService.class),
                    PackageManager.COMPONENT_ENABLED_STATE_DISABLED, PackageManager.DONT_KILL_APP);
            pm.setComponentEnabledSetting(new ComponentName(this, PaymentNotificationListenerService.class),
                    PackageManager.COMPONENT_ENABLED_STATE_ENABLED, PackageManager.DONT_KILL_APP);
        }
    }

    /**
     * 是否拥有通知使用权
     *
     * @return true 拥有权限
     */
    public boolean isNotificationListenerEnabled() {
        Set<String> packageNames = NotificationManagerCompat.getEnabledListenerPackages(this);
        return packageNames.contains(getPackageName());
    }

    /**
     * 打开通知使用权授权页
     */
    public void openNotificationListenSettings() {
        try {
            Intent intent = new Intent(Settings.ACTION_NOTIFICATION_LISTENER_SETTINGS);
            startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
