package com.sergio.marveltest.viewModel;

import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.sergio.marveltest.model.Result;

import java.util.Observable;

public class MarvelDetailObservable extends Observable {

    private Result result;

    public MarvelDetailObservable(Result result){
        this.result = result;
    }

    public String getDescription(){
        return result.getDescription();
    }

    public String getProfileThumb() {
        return result.getThumbnail().getPath() + "." + result.getThumbnail().getExtension();
    }

    @BindingAdapter({"imageUrl"})
    public static void loadImage(ImageView view, String imageUrl){
        Glide.with(view.getContext()).load(imageUrl).into(view);
    }
}
