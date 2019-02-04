package com.sergio.marveltest.view.activity;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.sergio.marveltest.R;
import com.sergio.marveltest.databinding.ActivityMainBinding;
import com.sergio.marveltest.view.adapter.ListAdapter;
import com.sergio.marveltest.viewModel.MarvelViewModel;

import java.util.Observable;
import java.util.Observer;

public class MainActivity extends AppCompatActivity implements Observer {

    private ActivityMainBinding activityMainBinding;
    private MarvelViewModel marvelViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initDataBinding();
        setUpListOfResults(activityMainBinding.recyclerview);
        setUpObserver(marvelViewModel);
    }

    private void initDataBinding() {

        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        marvelViewModel = new MarvelViewModel(this);
        activityMainBinding.setViewModel(marvelViewModel);
    }

    // set up the list of results with recycler view
    private void setUpListOfResults(RecyclerView recyclerView){
        ListAdapter listAdapter = new ListAdapter();
        recyclerView.setAdapter(listAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    public void setUpObserver(Observable observable) {
        observable.addObserver(this);
    }

    @Override
    public void update(Observable observable, Object o) {

        if (observable instanceof  MarvelViewModel){
            ListAdapter listAdapter = (ListAdapter) activityMainBinding.recyclerview.getAdapter();
            MarvelViewModel marvelViewModel = (MarvelViewModel) observable;
            if (listAdapter != null)listAdapter.setResultList(marvelViewModel.getMarvelResultList());
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        marvelViewModel.reset();
    }
}
