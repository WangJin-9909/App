package com.example.app;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.core_ui.toast.AppToast;
import com.example.framework.BaseFragment;
import com.example.networklib.BaseResponse;
import com.example.networklib.ServiceHelper;
import com.example.utils.AppLogger;
import com.google.gson.JsonObject;

import java.util.HashMap;
import java.util.Map;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import butterknife.OnClick;
import rx.Subscriber;

public class FragmentDemo extends BaseFragment {
    private String tv;

    public FragmentDemo(String tv) {
        this.tv = tv;
    }


    @OnClick({R.id.btn_post_1, R.id.btn_post_2})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_post_1:
                wanAdnroidRegister();
                break;
            case R.id.btn_post_2:

                break;

        }
    }


    private void wanAdnroidRegister() {
        ServiceHelper.getNetworkServer().signup("username1234", "username1234", "username1234")
                .compose(ServiceHelper.getDefaultScheduler())
                .subscribe(new Subscriber<BaseResponse>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        AppToast.showToast(getContext(), e.toString());

                    }

                    @Override
                    public void onNext(BaseResponse jsonObjectBaseResponse) {
                        AppToast.showToast(getContext(), jsonObjectBaseResponse.toString());
                    }
                });
    }


    @Override
    protected void initViewsAndEvents() {
        View view = View.inflate(getContext(), R.layout.fragment_test, null);
        TextView textView = (TextView) view.findViewById(R.id.tv_1);
        textView.setText(tv);

    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.fragment_test;
    }




}
