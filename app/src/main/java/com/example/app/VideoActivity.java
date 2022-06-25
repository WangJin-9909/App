package com.example.app;

import android.net.Uri;
import android.view.View;
import android.widget.VideoView;


import com.example.app.wigdet.player.VerticalVideoView;
import com.example.app_module.bean.BaseResponse;
import com.example.app_module.bean.ListHaoKanBean;
import com.example.core_ui.toast.AppToast;
import com.example.framework.BaseActivity;
import com.example.framework.utils.JsonUtils;
import com.example.networklib.ServiceHelper;
import com.example.utils.AppLogger;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import rx.Subscriber;

public class VideoActivity extends BaseActivity {
    @BindView(R.id.video_player)
    VerticalVideoView videoView;

    private List<ListHaoKanBean.HaoKanBean> list;
    private int current_index;

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_viceo_2;
    }

    @Override
    protected void initData() {
        videoView = findViewById(R.id.video_player);
        videoView.setVideoURI(Uri.parse("http://vd3.bdstatic.com/mda-nagbbiar33cz15b2/cae_h264_delogo/1642407610878382293/mda-nagbbiar33cz15b2.mp4"));
        videoView.postDelayed(new Runnable() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        videoView.start();
                    }
                });
            }
        }, 500);
    }

    /*
    @OnClick({R.id.btn_1, R.id.btn_2})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_1:
                getvideolist();
                break;

            case R.id.btn_2:
                videoView.setVideoURI(Uri.parse(list.get(current_index++).playUrl));
                videoView.start();

                break;


        }
    }*/


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
                        list = result.list;
                        current_index = 0;
                        videoView.setVideoURI(Uri.parse(result.list.get(0).playUrl));
                        videoView.start();
                    }
                });
    }

    @Override
    protected void initUi() {

    }
}
