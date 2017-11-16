package com.renygit.mvp_master.utils;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;

/**
 * Created by reny on 2017/6/14.
 */

public class CommonUtils {

    public static boolean isEmpty(Object datas){
        boolean isEmpty = (null == datas);
        if (datas instanceof List) {
            isEmpty = (((List) datas).size() == 0);
        }
        return isEmpty;
    }

    public static void initRecyclerView(RecyclerView rv, RecyclerView.LayoutManager layoutManager){
        if(null == layoutManager)layoutManager = new LinearLayoutManager(rv.getContext());
        rv.setLayoutManager(layoutManager);
        rv.setHasFixedSize(true);
    }

}
