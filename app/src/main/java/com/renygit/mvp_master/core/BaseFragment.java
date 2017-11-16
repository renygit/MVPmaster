package com.renygit.mvp_master.core;

import android.os.Bundle;

import com.renygit.mvplib.base.RBaseFragment;
import com.renygit.mvplib.base.RBasePresenter;

/**
 * Created by admin on 2017/6/7.
 */

public abstract class BaseFragment<P extends RBasePresenter> extends RBaseFragment<P> implements IBaseView{

    @Override
    protected void onCreateViewLazy(Bundle savedInstanceState) {
        super.onCreateViewLazy(savedInstanceState);
    }

}
