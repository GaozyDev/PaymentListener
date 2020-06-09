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

    private EditText mParamNameEt;

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
        mParamNameEt = findViewById(R.id.param_name_et);
        Button confirmBtn = findViewById(R.id.confirm_btn);

        listenerBtn.setOnClickListener(v -> openNotificationListenSettings());

        confirmBtn.setOnClickListener(v -> {
            String url = mUrlEt.getText().toString();
            String parameterName = mParamNameEt.getText().toString();
            if (TextUtils.isEmpty(url) || TextUtils.isEmpty(parameterName)) {
                Toast.makeText(MainActivity.this, R.string.url_parameter_empty,
                        Toast.LENGTH_SHORT).show();
            } else {
                Global.url = url;
                Global.paramName = parameterName;
                mUrlTv.setText(String.format(getString(R.string.url_param), url, parameterName));
                SpUtils.putString(MainActivity.this, SpUtils.URL, url);
                SpUtils.putString(MainActivity.this, SpUtils.PARAM_NAME, parameterName);
            }
        });
    }

    private void initData() {
        Global.url = SpUtils.getString(this, SpUtils.URL, "");
        Global.paramName = SpUtils.getString(this, SpUtils.PARAM_NAME, "");
        mUrlTv.setText(String.format(getString(R.string.url_param), Global.url, Global.paramName));
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

    public boolean isNotificationListenerEnabled() {
        Set<String> packageNames = NotificationManagerCompat.getEnabledListenerPackages(this);
        return packageNames.contains(getPackageName());
    }

    public void openNotificationListenSettings() {
        try {
            Intent intent = new Intent(Settings.ACTION_NOTIFICATION_LISTENER_SETTINGS);
            startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
