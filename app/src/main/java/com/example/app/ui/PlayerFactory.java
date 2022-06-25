package com.example.app.ui;

import android.content.Context;

/**
 * 此接口使用方法：
 * 1.继承{@link AbstractPlayer}扩展自己的播放器。
 * 2.继承此接口并实现{@link #createPlayer(Context)}，返回步骤1中的播放器。
 */
public abstract class PlayerFactory<P extends AbstractPlayer> {

    public abstract P createPlayer(Context context);
}
