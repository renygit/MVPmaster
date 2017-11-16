package com.renygit.mvp_master.core;

import com.renygit.mvp_master.api.DoubanApiService;
import com.renygit.mvp_master.api.GankApiService;

/**
 * Created by reny on 2017/2/9.
 */

public class ServiceHelper {

    public static GankApiService getGankAS(){
        return (GankApiService) ServiceFactory.getInstance().getService(GankApiService.class);
    }


    public static DoubanApiService getDoubanAS(){
        return (DoubanApiService) ServiceFactory.getInstance().getService(DoubanApiService.class);
    }

}
