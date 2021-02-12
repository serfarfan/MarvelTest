package com.sergio.marveltest.view.activity;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.sergio.marveltest.R;
import com.sergio.marveltest.databinding.ActivityDetailBinding;
import com.sergio.marveltest.model.Result;
import com.sergio.marveltest.viewModel.MarvelDetailObservable;

public class DetailActivity extends AppCompatActivity {

    private static final String EXTRA_RESULT = "EXTRA_RESULT";
    private ActivityDetailBinding activityDetailBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityDetailBinding = DataBindingUtil.setContentView(this, R.layout.activity_detail);
        displayHomeAsUpEnabled();
        getExtrasFromIntent();
    }

    private void displayHomeAsUpEnabled() {

        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    public static Intent fillDetail(Context context, Result result) {
        Intent intent = new Intent(context, DetailActivity.class);
        intent.putExtra(EXTRA_RESULT, result);
        return intent;
    }

    private void getExtrasFromIntent(){
        Result result = (Result) getIntent().getSerializableExtra(EXTRA_RESULT);
        MarvelDetailObservable detailViewModel = new MarvelDetailObservable(result);
        activityDetailBinding.setDetailViewModel(detailViewModel);
    }
}
