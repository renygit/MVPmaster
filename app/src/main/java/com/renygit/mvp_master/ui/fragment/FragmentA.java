package com.renygit.mvp_master.ui.fragment;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import com.renygit.multistateview.MultiStateView;
import com.renygit.mvp_master.R;
import com.renygit.mvp_master.core.BaseFragment;
import com.renygit.mvp_master.entity.model.GankData;
import com.renygit.mvp_master.mvp.FragmentAView;
import com.renygit.mvp_master.presenter.FAPresenter;
import com.renygit.mvp_master.ui.adapter.FragmentAAdapter;
import com.renygit.mvp_master.utils.CommonUtils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;

import butterknife.BindView;

/**
 * Created by reny on 2017/11/15.
 */

public class FragmentA extends BaseFragment<FAPresenter> implements FragmentAView {

    @BindView(R.id.rv)
    RecyclerView rv;
    @BindView(R.id.srl)
    SmartRefreshLayout srl;
    @BindView(R.id.msv)
    MultiStateView msv;

    private FragmentAAdapter adapter;

    @Override
    protected FAPresenter obtainPresenter() {
        return new FAPresenter(this);
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        CommonUtils.initRecyclerView(rv, new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
    }

    @Override
    protected void onCreateViewLazy(Bundle savedInstanceState) {
        presenter.loadData(true);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_a;
    }

    @Override
    protected MultiStateView getMultiStateView() {
        return msv;
    }

    @Override
    protected RefreshLayout getRefreshLayout() {
        return srl;
    }

    @Override
    public void setData(GankData data, boolean isRefresh) {
        if(null == adapter){
            adapter = new FragmentAAdapter(data.getResults());
            adapter.openLoadAnimation();
            rv.setAdapter(adapter);
        }else {
            if(isRefresh){
                adapter.setNewData(data.getResults());
            }else {
                adapter.addData(data.getResults());
            }
        }
    }
}
