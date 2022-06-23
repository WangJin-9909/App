package com.example.app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import butterknife.BindView;

import android.os.Bundle;
import android.os.Parcelable;

import com.example.core_ui.tabs.OnTabChangedListner;
import com.example.core_ui.tabs.TabsIndicator;
import com.example.core_ui.toast.AppToast;
import com.example.core_ui.viewpager.CustomerViewPager;
import com.example.framework.BaseActivity;

import java.util.ArrayList;

public class MainActivity extends BaseActivity {
    @BindView(R.id.tab_indicators)
    TabsIndicator tab_indicators;
    @BindView(R.id.viewpager_main)
    CustomerViewPager main_viewpager;

    private ArrayList<Fragment> fragments;

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initData() {

    }

    private void initViewPager() {
        //设置是否运训滑动切换
        main_viewpager.setCanScroll(false);
        main_viewpager.setAdapter(new MyPagerAdapter(getSupportFragmentManager()));//ViewPager的Adaper
        main_viewpager.setOffscreenPageLimit(2);
    }

    @Override
    protected void initUi() {
        tab_indicators.setOnTabChangedListner(new OnTabChangedListner() {
            @Override
            public void onTabSelected(int tabNum, boolean isSensor) {
                AppToast.showToast(getContext(), "tabNum = " + tabNum);
                main_viewpager.setCurrentItem(tabNum);

            }
        });

        fragments = new ArrayList<>();
        fragments.add(new FragmentDemo("页面1"));
        fragments.add(new FragmentDemo("页面2"));
        fragments.add(new FragmentDemo("页面3"));
        fragments.add(new FragmentDemo("页面4"));

        initViewPager();
    }


    class MyPagerAdapter extends FragmentPagerAdapter {
        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int i) {
            return fragments.get(i);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }
    }


}