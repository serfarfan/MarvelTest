package com.sergio.marveltest.network;

import com.sergio.marveltest.model.Marvel;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface MarvelService {

    @GET
    Observable<Marvel> getMarvelList(@Url String url);
}
