package com.renygit.mvp_master.core;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;

import com.jaeger.library.StatusBarUtil;
import com.jude.swipbackhelper.SwipeBackHelper;
import com.renygit.multistateview.MultiStateView;
import com.renygit.mvp_master.utils.SwipeBackUtils;
import com.renygit.mvplib.base.RBaseActivity;
import com.scwang.smartrefresh.layout.api.RefreshLayout;

/**
 * Created by reny on 2017/11/15.
 */

public abstract class BaseActivity extends RBaseActivity implements IBaseView{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        if (isEnableSwipeBack()) {
            SwipeBackHelper.onCreate(this);
            SwipeBackUtils.EnableSwipeActivity(this, 0.1f);
        }

        if (isTranslucentStatusBar()) {
            StatusBarUtil.setTranslucentForImageViewInFragment(this, null);
        }

        super.onCreate(savedInstanceState);

        if (null != getToolbar()) {
            getToolbar().setTitle("");
            setSupportActionBar(getToolbar());
            //给左上角图标的左边加上一个返回的图标
            if(null != getSupportActionBar()) {
                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            }
            getToolbar().setNavigationOnClickListener(v -> onBackPressed());
        }
    }

    @Override
    protected MultiStateView getMultiStateView() {
        return null;
    }

    @Override
    protected RefreshLayout getRefreshLayout() {
        return null;
    }

    protected Toolbar getToolbar(){
        return null;
    }

    //是否启动滑动退出
    protected boolean isEnableSwipeBack() {
        return true;
    }

    //是否设置状态栏为透明
    protected boolean isTranslucentStatusBar() {
        return false;
    }

}
