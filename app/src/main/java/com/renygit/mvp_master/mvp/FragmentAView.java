package com.renygit.mvp_master.mvp;

import com.renygit.mvp_master.core.IBaseView;
import com.renygit.mvp_master.entity.model.GankData;

/**
 * Created by reny on 2017/11/16.
 */

public interface FragmentAView extends IBaseView {

    void setData(GankData data, boolean isRefresh);

}
