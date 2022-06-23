package com.example.core_ui.tabs;

import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

public class TabsIndicator extends LinearLayout {
    private OnTabChangedListner mListner;
    private List<TabView> mTabViews;
    private static final String STATE_INSTANCE = "instance_state";
    private static final String STATE_ITEM = "state_item";
    /**
     * 子View的数量
     */
    private int mChildCounts;
    /**
     * 当前的条目索引
     */
    public int mCurrentItem = 0;

    public TabsIndicator(Context context) {
        this(context, null);
    }

    public TabsIndicator(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TabsIndicator(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        post(new Runnable() {
            @Override
            public void run() {
                init();
            }
        });
    }

    public TabView getCurrentItemView() {
        return mTabViews.get(mCurrentItem);
    }

    public TabView getTabView(int p) {
        return mTabViews.get(p);
    }

    public void removeAllBadge() {
        for (TabView TabView : mTabViews) {
            TabView.removeShow();
        }
    }

    private class ItemOnClickListener implements OnClickListener {
        private int currentIndex;

        public ItemOnClickListener(int i) {
            this.currentIndex = i;
        }

        @Override
        public void onClick(View v) {
            setOnTabSelect(currentIndex, true);
        }
    }

    private void init() {
        mTabViews = new ArrayList<>();
        mChildCounts = getChildCount();

        for (int i = 0; i < mChildCounts; i++) {
            if (getChildAt(i) instanceof TabView) {
                TabView tabView = (TabView) getChildAt(i);
                mTabViews.add(tabView);
                //设置点击监听
                tabView.setOnClickListener(new ItemOnClickListener(i));
            } else {
                throw new IllegalArgumentException("TabIndicator的子View必须是TabView");
            }
        }

        mTabViews.get(mCurrentItem).setIconAlpha(1.0f);
    }


    public void setOnTabChangedListner(OnTabChangedListner listner) {
        this.mListner = listner;

    }

    public void setOnTabSelect(int currentIndex, boolean isSensor) {
        //点击前先重置所有按钮的状态
        resetState();
        mTabViews.get(currentIndex).setIconAlpha(1.0f);
        if (null != mListner) {
            mListner.onTabSelected(currentIndex, isSensor);
        }
        //点击是保存当前按钮索引
        mCurrentItem = currentIndex;
    }

    /**
     * 重置所有按钮的状态
     */
    private void resetState() {
        for (int i = 0; i < mChildCounts; i++) {
            mTabViews.get(i).setIconAlpha(0);
        }
    }

    /**
     * @return 当View被销毁的时候，保存数据
     */
    @Override
    protected Parcelable onSaveInstanceState() {
        Bundle bundle = new Bundle();
        bundle.putParcelable(STATE_INSTANCE, super.onSaveInstanceState());
        bundle.putInt(STATE_ITEM, mCurrentItem);
        return bundle;
    }

    /**
     * @param state 用于恢复数据使用
     */
    @Override
    protected void onRestoreInstanceState(Parcelable state) {
        if (state instanceof Bundle) {
            Bundle bundle = (Bundle) state;
            mCurrentItem = bundle.getInt(STATE_ITEM);
            //重置所有按钮状态
            resetState();
            //恢复点击的条目颜色
            mTabViews.get(mCurrentItem).setIconAlpha(1.0f);
            super.onRestoreInstanceState(bundle.getParcelable(STATE_INSTANCE));
        } else {
            super.onRestoreInstanceState(state);
        }
    }
}
