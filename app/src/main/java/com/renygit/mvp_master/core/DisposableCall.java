package com.renygit.mvp_master.core;

import com.renygit.mvplib.utils.AppUtils;

import io.reactivex.observers.DisposableObserver;

/**
 * Created by reny on 2017/11/16.
 */

public abstract class DisposableCall<T> extends DisposableObserver<T> {

    private BasePresenter presenter;

    protected DisposableCall(BasePresenter presenter){
        this.presenter = presenter;
    }

    @Override
    public void onNext(T value) {
        onSuc(value);
        if(null != presenter){
            AppUtils.self().hideLoading();
            presenter.getView().finishRefresh(isRefresh(), isEmpty(value), false);
        }
    }

    @Override
    public void onError(Throwable e) {
        if(null != presenter){
            AppUtils.self().hideLoading();
            presenter.getView().finishRefresh(isRefresh(), true, true);
        }
        onErr(e);
    }

    @Override
    public void onComplete() {
        if(null != presenter){
            AppUtils.self().hideLoading();
        }
    }

    public abstract boolean isRefresh();
    public abstract boolean isEmpty(T value);
    public abstract void onSuc(T data);
    public abstract void onErr(Throwable e);

}
