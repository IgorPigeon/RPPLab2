package com.example.Lab2;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class MyAdapterViewPage extends FragmentPagerAdapter {

    private MyViewModel model;

    public MyAdapterViewPage(FragmentManager mgr, MyViewModel model) {
        super(mgr);
        this.model = model;
    }

    @Override
    public int getCount() {
        return model.getSizeTechnologies();
    }

    @Override
    public Fragment getItem(int position) {
        return(PageFragment.newInstance(position, model.getTechnologyIndex(position)));
    }

}
