package com.example.framework;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseActivity extends AppCompatActivity {
    private Unbinder binder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }


        setContentView(getLayoutResId());
        binder = ButterKnife.bind(this);

        initUi();

        initData();
    }

    public void readyGo(Context context, Class<?> clazz, Bundle bundle) {
        if (null == clazz) {
            throw new IllegalArgumentException("activity should not be null");
        }
        Intent intent = new Intent(context, clazz);
        if (null != bundle) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }

    protected Context getContext() {
        return getApplicationContext();
    }

    protected abstract int getLayoutResId();

    protected abstract void initData();

    protected abstract void initUi();

    @Override
    protected void onDestroy() {
        super.onDestroy();

        binder.unbind();
    }


}
