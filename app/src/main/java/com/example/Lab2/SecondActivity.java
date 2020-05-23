package com.example.Lab2;

import androidx.appcompat.app.AppCompatActivity;

import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.TextView;


public class SecondActivity extends AppCompatActivity implements ListFragment.OnListFragmentInteractionListener {

    TextView startText;
    ListFragment frag;
    FragmentViewPage frag2;
    FragmentTransaction fragTrans;
    MyViewModel model;
    FrameLayout firstFrame;
    boolean needExit;

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (needExit){
            finish();
        }
        else{
            fragTrans.remove(frag2);
            needExit = true;
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        needExit = true;
        // Получаем json файл из первого активити
        Bundle arguments = getIntent().getExtras();
        String jsonString = arguments.get("jsonString").toString();

        // Создаем хранилище
        model = new ViewModelProvider(this.getViewModelStore(), new ViewModelProvider.NewInstanceFactory()).get(MyViewModel.class);
        model.loadTechnologies(jsonString, this.getResources());

        // Создаем и добавляем на активити список
        frag = new ListFragment(model);
        fragTrans = getSupportFragmentManager().beginTransaction();
        fragTrans.add(R.id.fragCont, frag).addToBackStack("list_fragment");
        fragTrans.commit();
    }

    @Override
    public void onListFragmentInteraction(Technologies.Technology item, int num) {
        // Создаем ViewPager
        frag2 = new FragmentViewPage(model);
        frag2.setPage(num);
        fragTrans.remove(frag);
        fragTrans = getSupportFragmentManager().beginTransaction();
        fragTrans.replace(R.id.fragCont, frag2).addToBackStack("page_fragment");
        fragTrans.commit();
        needExit = false;
    }
}
