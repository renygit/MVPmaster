package com.renygit.mvplib.utils;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.StringRes;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.Toast;

import com.kaopiz.kprogresshud.KProgressHUD;

/**
 * Created by reny on 2017/11/15.
 */

public class AppUtils {

    private Application mApplication;
    private Activity mActivity;
    private Toast toast = null;
    private KProgressHUD progressHUD;

    private AppUtils(){
    }

    private static class SingletonHolder {
        private static final AppUtils INSTANCE = new AppUtils();
    }

    public static AppUtils self() {
        return SingletonHolder.INSTANCE;
    }


    public void setApplication(Application mApplication) {
        this.mApplication = mApplication;
    }

    public void setActivity(Activity mActivity) {
        this.mActivity = mActivity;
        if(null == mApplication && null != mActivity){
            setApplication(mActivity.getApplication());
        }
    }

    public Context getContext(){
        return null == mActivity ? mApplication : mActivity;
    }


    public boolean isConnected() {
        NetworkInfo net = ((ConnectivityManager)(getContext().getSystemService(Context.CONNECTIVITY_SERVICE))).getActiveNetworkInfo();
        return net != null && net.isConnected();
    }


    public void showSnackbar(String message) {
        if (null == mActivity) {
            LogUtils.e("mActivity == null");
            return;
        }
        View view = mActivity.getWindow().getDecorView().findViewById(android.R.id.content);
        Snackbar.make(view, message, Snackbar.LENGTH_SHORT).show();
    }

    public void showSnackbarLong(String message) {
        if (null == mActivity) {
            LogUtils.e("mActivity == null");
            return;
        }
        View view = mActivity.getWindow().getDecorView().findViewById(android.R.id.content);
        Snackbar.make(view, message, Snackbar.LENGTH_LONG).show();
    }

    public void showToast(@StringRes int resId) {
        if(null == toast){
            toast = Toast.makeText(getContext(), resId, Toast.LENGTH_SHORT);
        }
        toast.setText(resId);
        toast.show();
    }


    public void showToast(String message) {
        if(null == toast){
            toast = Toast.makeText(getContext(), message, Toast.LENGTH_SHORT);
        }
        toast.setText(message);
        toast.show();
    }


    public void showToastLong(@StringRes int resId) {
        if(null == toast){
            toast = Toast.makeText(getContext(), resId, Toast.LENGTH_LONG);
        }
        toast.setText(resId);
        toast.show();
    }


    public void showToastLong(String message) {
        if(null == toast){
            toast = Toast.makeText(getContext(), message, Toast.LENGTH_LONG);
        }
        toast.setText(message);
        toast.show();
    }


    public void showLoading(boolean isCancel, String... tips) {
        if(null == progressHUD) {
            progressHUD = KProgressHUD.create(getContext())
                    .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                    .setAnimationSpeed(2)
                    .setDimAmount(0.5f);
        }
        progressHUD.setCancellable(isCancel);
        if(tips.length > 0){
            progressHUD.setLabel(tips[0]);
            if(tips.length > 1){
                progressHUD.setDetailsLabel(tips[1]);
            }
        }
        progressHUD.show();
    }

    public void hideLoading() {
        if(null != progressHUD){
            if(progressHUD.isShowing()){
                progressHUD.dismiss();
            }
        }
    }

    public void release(){
        this.mActivity = null;
        this.progressHUD = null;
    }

}
