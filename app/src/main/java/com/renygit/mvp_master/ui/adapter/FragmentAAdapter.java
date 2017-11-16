package com.renygit.mvp_master.ui.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.renygit.mvp_master.R;
import com.renygit.mvp_master.entity.model.GankData;
import com.renygit.mvp_master.utils.glide.GlideHelper;

import java.util.List;

/**
 * Created by reny on 2017/11/16.
 */

public class FragmentAAdapter extends BaseQuickAdapter<GankData.ResultsBean, BaseViewHolder> {

    public FragmentAAdapter(@Nullable List<GankData.ResultsBean> data) {
        super(R.layout.item_fragment_a, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, GankData.ResultsBean item) {
        helper.setText(R.id.tv_date, item.getCreatedAt().substring(0,10));
        GlideHelper.display(helper.getView(R.id.riv), item.getUrl());
    }
}
