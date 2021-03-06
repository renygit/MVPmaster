package com.renygit.mvp_master.core;

import android.support.annotation.Nullable;


import com.renygit.mvp_master.api.APIConfig;
import com.renygit.mvp_master.api.MyNetInterceptor;
import com.renygit.mvp_master.utils.SingletonUtils;
import com.renygit.mvplib.http.SimpleServiceFactory;

import java.io.File;

import okhttp3.Cache;
import okhttp3.OkHttpClient;

/**
 * Created by reny on 2017/2/9.
 */

public class ServiceFactory<S> extends SimpleServiceFactory<S> {

    private ServiceFactory(){}

    private static class SingletonHolder {
        private static final ServiceFactory INSTANCE = new ServiceFactory();
    }

    public static ServiceFactory getInstance() {
        return SingletonHolder.INSTANCE;
    }

    @Nullable
    @Override
    public Long getDefaultTimeOut() {
        return null;
    }

    @Override
    public void setClientBuilder(OkHttpClient.Builder clientBuilder) {
        clientBuilder.cookieJar(SingletonUtils.cookieJar);
        File cacheFile = new File(SingletonUtils.cacheDir, "HttpCache"); // 指定缓存路径
        Cache cache = new Cache(cacheFile, 1024 * 1024 * 100); // 指定缓存大小100Mb
        clientBuilder.cache(cache);

        clientBuilder.addNetworkInterceptor(new MyNetInterceptor());
    }

    @Override
    public String getDefaultBaseUrl() {
        //默认BaseUrl
        return APIConfig.BASE_URL_DEFAULT;
    }
}
