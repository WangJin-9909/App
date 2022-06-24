package com.example.framework;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.utils.AppLogger;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseFragment extends Fragment {
    private Unbinder binder;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (getContentViewLayoutID() != 0) {
            return inflater.inflate(getContentViewLayoutID(), null);
        } else {
            return super.onCreateView(inflater, container, savedInstanceState);
        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binder = ButterKnife.bind(this, view);

        initViewsAndEvents();
    }

    protected abstract void initViewsAndEvents();

    @Override
    public void onResume() {
        super.onResume();

        //父类统一记录页面访问轨迹，用于埋点
        AppLogger.d("Page Navigator = " + getClass().getSimpleName());
    }

    @Override
    public void onDestroyView() {
        binder.unbind();
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {

        super.onDestroy();
    }

    protected abstract int getContentViewLayoutID();

    public void readyGo(Class<?> clazz) {
        if (null == getActivity()) {
            return;
        }
        Intent intent = new Intent(getActivity(), clazz);
        startActivity(intent);
    }

    public void readyGo(Class<?> clazz, Bundle bundle) {
        if (null == getActivity()) {
            return;
        }
        Intent intent = new Intent(getActivity(), clazz);
        if (null != bundle) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }

    @Override
    public void onDetach() {
        super.onDetach();

    }



}
