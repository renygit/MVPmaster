package com.renygit.mvp_master.core;

import com.renygit.mvplib.base.RBasePresenter;

/**
 * Created by reny on 2017/11/16.
 */

public abstract class BasePresenter<V extends IBaseView> extends RBasePresenter<V> implements IBaseView{

    public BasePresenter(V view) {
        super(view);
    }

    @Override
    public void finishRefresh(boolean isRefresh, boolean isEmpty, boolean isError) {

    }
}
