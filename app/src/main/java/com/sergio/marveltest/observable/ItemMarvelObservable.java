package com.sergio.marveltest.observable;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.BindingAdapter;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.sergio.marveltest.model.Result;
import com.sergio.marveltest.view.activity.DetailActivity;

public class ItemMarvelObservable extends BaseObservable {

    private Result result;
    private Context context;

    public ItemMarvelObservable(Result result, Context context){
        this.result = result;
        this.context = context;
    }

    public String getProfileThumb() {
        return result.getThumbnail().getPath() + "." + result.getThumbnail().getExtension();
    }

    // Loading Image using Glide Library.
    @BindingAdapter("imageUrl") public static void setImageUrl(ImageView imageView, String url){
        Glide.with(imageView.getContext()).load(url).into(imageView);
    }

    public String getName(){
        return result.getName();
    }

    public void onItemClick(View v){
        context.startActivity(DetailActivity.fillDetail(v.getContext(), result));
    }

    public void setResult(Result result){
        this.result = result;
        notifyChange();
    }

}
