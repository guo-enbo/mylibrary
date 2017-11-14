package com.guoenbo.library.ui.adapter.base;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;


import com.guoenbo.library.bean.TitleBean;

import java.util.List;

public class TabPagerAdapter extends FragmentAdapter {

    private List<TitleBean> titleList;

    public TabPagerAdapter(Context context, FragmentManager fragmentManager,
                           List<Fragment> list, List<TitleBean> titleList) {
        super(context, fragmentManager, list);
        this.titleList = titleList;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titleList.get(position%titleList.size()).getTitle();
    }

}
