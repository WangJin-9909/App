package com.example.app;

import android.view.View;

import com.example.app_module.bean.BaseResponse;
import com.example.app_module.bean.ListHaoKanBean;
import com.example.core_ui.toast.AppToast;
import com.example.framework.BaseActivity;
import com.example.framework.utils.JsonUtils;
import com.example.networklib.ServiceHelper;
import com.example.utils.AppLogger;

import butterknife.OnClick;
import rx.Subscriber;

public class VideoActivity extends BaseActivity {
    @Override
    protected int getLayoutResId() {
        return R.layout.activity_video;
    }

    @Override
    protected void initData() {

    }


    @OnClick({R.id.btn_1})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_1:
                getvideolist();
                break;
        }
    }


    private void getvideolist() {
        ServiceHelper.getNetworkServer().getHaoKanVideo(1, 10)
                .compose(ServiceHelper.getDefaultScheduler())
                .subscribe(new Subscriber<BaseResponse<ListHaoKanBean>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        AppLogger.e(e.toString());
                    }

                    @Override
                    public void onNext(BaseResponse baseResponse) {
                        ListHaoKanBean result = (ListHaoKanBean) baseResponse.getResult();
                        AppLogger.e("result = " + result.total);
                        ListHaoKanBean.HaoKanBean haoKanBean = result.list.get(0);
                        AppToast.showToast(getContext(), haoKanBean.playUrl);
                    }
                });
    }

    @Override
    protected void initUi() {

    }
}
