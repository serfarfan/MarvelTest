package com.sergio.marveltest.application;

import android.app.Application;
import android.content.Context;

import com.sergio.marveltest.network.ApiFactory;
import com.sergio.marveltest.network.MarvelService;

import io.reactivex.Scheduler;
import io.reactivex.schedulers.Schedulers;

public class AppController extends Application {

    private MarvelService marvelService;
    private Scheduler scheduler;

    private static AppController get(Context context) {
        return (AppController) context.getApplicationContext();
    }

    public static AppController create(Context context) {
        return AppController.get(context);
    }


    public MarvelService getMarvelService(){
        if (marvelService == null) marvelService = ApiFactory.create();
        return marvelService;
    }

    public Scheduler subscribeScheduler(){
        if (scheduler == null) scheduler = Schedulers.io();
        return scheduler;
    }

}
