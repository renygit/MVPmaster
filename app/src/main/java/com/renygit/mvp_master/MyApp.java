package com.renygit.mvp_master;

import android.app.Application;
import android.content.Context;

import com.renygit.multistateview.MultiStateConfig;
import com.renygit.mvplib.utils.AppUtils;
import com.renygit.mvplib.utils.LogUtils;
import com.scwang.smartrefresh.header.MaterialHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;

/**
 * Created by reny on 2017/11/16.
 */

public class MyApp extends Application {

    public static MyApp instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        AppUtils.self().setApplication(instance);

        init();
    }

    private void init() {
        LogUtils.init(BuildConfig.DEBUG);


        //设置全局的多状态配置 局部支持xml设置 可以设置不同状态的图片提示
        // 还有。。。自己看源码，如果喜欢这个库 可以提建议给我  虽然我不一定会改└(^o^)┘
        MultiStateConfig.getInstance().setConfig(
                new MultiStateConfig.Build()
                        .setTipEmpty("没有相关数据，点击重试")
                        .setTipError("加载失败，点击重试")
                        .setTipNoNetwork("没有网络，点击重试")
                        .setIndicatorName("BallSpinFadeLoaderIndicator")
                        .setIndicatorColor(R.color.colorAccent)
        );

        //设置全局的Header构建器
        SmartRefreshLayout.setDefaultRefreshHeaderCreater((context, layout) -> {
            //指定Header
            return new MaterialHeader(context).setColorSchemeColors(0xff000000);
        });
        //设置全局的Footer构建器
        SmartRefreshLayout.setDefaultRefreshFooterCreater((context, layout) -> {
            //指定Footer
            return new ClassicsFooter(context);
        });
    }

    public static Context getContext(){
        return instance.getApplicationContext();
    }
}
