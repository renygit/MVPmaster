package com.renygit.mvp_master.presenter;

import com.renygit.mvp_master.R;
import com.renygit.mvp_master.api.GankApiService;
import com.renygit.mvp_master.core.BasePresenter;
import com.renygit.mvp_master.core.DisposableCall;
import com.renygit.mvp_master.core.ServiceHelper;
import com.renygit.mvp_master.entity.model.GankData;
import com.renygit.mvp_master.mvp.FragmentAView;
import com.renygit.mvp_master.utils.CommonUtils;
import com.renygit.mvp_master.utils.ResUtils;
import com.renygit.mvplib.base.IRBaseView;
import com.renygit.mvplib.base.RBasePresenter;
import com.renygit.mvplib.utils.AppUtils;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by reny on 2017/11/16.
 */

public class FAPresenter extends BasePresenter<FragmentAView> {

    private int count = ResUtils.getInteger(R.integer.load_count);
    private int page = 1;

    public FAPresenter(FragmentAView view) {
        super(view);
    }

    @Override
    public void loadData(boolean isRefresh) {
        addDisposable(ServiceHelper.getGankAS().getGankIoData(GankApiService.category_a, count, isRefresh ? 1 : page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableCall<GankData>(this){
                    @Override
                    public boolean isRefresh() {
                        return isRefresh;
                    }
                    @Override
                    public boolean isEmpty(GankData value) {
                        return CommonUtils.isEmpty(value) || CommonUtils.isEmpty(value.getResults());
                    }
                    @Override
                    public void onSuc(GankData data) {
                        page = isRefresh ? 2 : ++page;
                        if(!isEmpty(data)) {
                            getView().setData(data, isRefresh);
                        }
                    }
                    @Override
                    public void onErr(Throwable e) {
                        AppUtils.self().showSnackbar(e.getMessage());
                    }
                })
        );
    }
}
