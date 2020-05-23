package com.example.Lab2;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

public class FragmentViewPage extends Fragment {

    private MyViewModel model;
    private ViewPager pager;
    private int page;

    public FragmentViewPage() {
    }

    public FragmentViewPage(MyViewModel model) {
        this.model = model;
    }

    public void  setPage(int page){
            this.page = page;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View result=inflater.inflate(R.layout.fragment_view_pager, container, false);
        pager=(ViewPager)result.findViewById(R.id.frag_pager);
        pager.setAdapter(new MyAdapterViewPage(getFragmentManager(), model));
        pager.setCurrentItem(page);

        return result;
    }
}
