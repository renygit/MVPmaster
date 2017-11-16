package com.renygit.mvp_master.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.renygit.multistateview.MultiStateView;
import com.renygit.mvp_master.R;
import com.renygit.mvp_master.core.BaseActivity;
import com.renygit.mvp_master.ui.fragment.FragmentA;
import com.renygit.mvplib.base.RBasePresenter;
import com.renygit.mvplib.utils.AppUtils;
import com.renygit.mvplib.utils.LogUtils;
import com.scwang.smartrefresh.layout.api.RefreshLayout;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindArray;
import butterknife.BindView;
import eu.long1.spacetablayout.SpaceTabLayout;

public class MainActivity extends BaseActivity {

    @BindArray(R.array.tabTitles)String[] tabTitles;

    @BindView(R.id.toolbar)Toolbar toolbar;
    @BindView(R.id.vp)ViewPager vp;
    @BindView(R.id.tabLayout)SpaceTabLayout tabLayout;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected RBasePresenter obtainPresenter() {
        return null;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        toolbar.setTitle(tabTitles[0]);
        setSupportActionBar(toolbar);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.menu_about:
                        AppUtils.self().showLoading(true);
                        /*Intent intent = new Intent(MainActivity.this, WebActivity.class);
                        intent.putExtra("url", APIConfig.ABOUT_URL);
                        startActivity(intent);*/
                        break;
                }
                return true;
            }
        });

        List<Fragment> fragmentList = new ArrayList<>();
        fragmentList.add(new FragmentA());
        fragmentList.add(new FragmentA());
        fragmentList.add(new FragmentA());
        fragmentList.add(new FragmentA());
        vp.setOffscreenPageLimit(fragmentList.size());
        //we need the savedInstanceState to retrieve the position
        tabLayout.initialize(vp, getSupportFragmentManager(), fragmentList, savedInstanceState);

        //演示“发送事件” （功能可以用FragmentA的实例调用内部方法实现滑动到顶部，eg: fragmentA.scrollToTop(); 在FragmentA中实现滚动方法即可）
        /*toolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //list回到顶部
                int tabIndex = binding.vp.getCurrentItem();
                EventBus.getDefault().post(new RvScrollEvent(tabIndex, 0));
            }
        });*/

        vp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {}
            @Override
            public void onPageSelected(int position) {
                toolbar.setTitle(tabTitles[position]);
            }
            @Override
            public void onPageScrollStateChanged(int state) {}
        });
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        tabLayout.saveState(outState);
        super.onSaveInstanceState(outState);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    protected boolean isEnableSwipeBack() {
        return false;
    }
}
