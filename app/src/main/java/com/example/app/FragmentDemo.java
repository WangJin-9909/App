package com.example.app;

import android.view.View;
import android.widget.TextView;

import com.example.core_ui.toast.AppToast;
import com.example.framework.BaseFragment;
import com.example.networklib.BaseResponse;
import com.example.networklib.ServiceHelper;
import com.example.networklib.bean.VersionBean;

import butterknife.OnClick;
import rx.Subscriber;

public class FragmentDemo extends BaseFragment {
    private String tv;

    public FragmentDemo(String tv) {
        this.tv = tv;
    }


    @OnClick({R.id.btn_post_1})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_post_1:
                getVersion();
                break;


        }
    }


    private void getVersion() {
        ServiceHelper.getNetworkServer().getVersion()
                .compose(ServiceHelper.getDefaultScheduler())
                .subscribe(new Subscriber<BaseResponse<VersionBean>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(BaseResponse<VersionBean> versionBeanBaseResponse) {
                        AppToast.showToast(getContext(), versionBeanBaseResponse.getMsg().version);
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
