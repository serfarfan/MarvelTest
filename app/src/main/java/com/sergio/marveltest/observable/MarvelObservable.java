package com.sergio.marveltest.observable;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import android.content.Context;
import android.databinding.ObservableInt;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Toast;

import com.sergio.marveltest.application.AppController;
import com.sergio.marveltest.model.Marvel;
import com.sergio.marveltest.model.Result;
import com.sergio.marveltest.network.MarvelService;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

import static com.sergio.marveltest.utils.Constants.URL;

public class MarvelObservable extends Observable {

    public ObservableInt progressBar;
    public ObservableInt marvelRecycler;
    private List<Result> marvelList;
    private Context context;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    public MarvelObservable(@NonNull Context context){
        this.context = context;
        this.marvelList = new ArrayList<>();
        progressBar = new ObservableInt(View.GONE);
        marvelRecycler = new ObservableInt(View.GONE);
    }

    public void onClickSearch(View v){
        initViews();
        validateOperation();
    }

    public void initViews(){

        marvelRecycler.set(View.GONE);
        progressBar.set(View.VISIBLE);
    }

    public void validateOperation(){

        getMarvelList();
    }

    private void getMarvelList(){

        AppController appController = AppController.create(context);
        MarvelService marvelService = appController.getMarvelService();

        Disposable disposable = marvelService.getMarvelList(URL)
                .subscribeOn(appController.subscribeScheduler())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Marvel>() {
                    @Override
                    public void accept(Marvel marvel) throws Exception {
                        updateMarvelList(marvel.getData().getResults());
                        progressBar.set(View.GONE);
                        marvelRecycler.set(View.VISIBLE);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        progressBar.set(View.GONE);
                        marvelRecycler.set(View.GONE);
                        Toast.makeText(context, "Error descargando la info", Toast.LENGTH_SHORT).show();
                    }
                });
        compositeDisposable.add(disposable);
    }

    private void updateMarvelList(List<Result> marvels){
        marvelList.addAll(marvels);
        setChanged();
        notifyObservers();
    }

    public List<Result> getMarvelResultList(){
        return marvelList;
    }

    private void unSubscribeFromObservable(){
        if (compositeDisposable != null && !compositeDisposable.isDisposed()) {
            compositeDisposable.dispose();
        }
    }

    public void reset(){
        unSubscribeFromObservable();
        compositeDisposable = null;
        context = null;
    }

}
