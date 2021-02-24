package com.sergio.marveltest.view.adapter;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.sergio.marveltest.R;
import com.sergio.marveltest.databinding.ItemMarvelBinding;
import com.sergio.marveltest.model.Result;
import com.sergio.marveltest.observable.ItemMarvelObservable;

import java.util.Collections;
import java.util.List;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.MarvelHolder>{

    private List<Result> resultList;

    public ListAdapter() {
        this.resultList = Collections.emptyList();
    }

    @NonNull
    @Override
    public MarvelHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        ItemMarvelBinding itemMarvelBinding = DataBindingUtil.inflate(LayoutInflater.from(viewGroup.getContext()),
                R.layout.item_marvel, viewGroup, false);
        return new MarvelHolder(itemMarvelBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull MarvelHolder marvelHolder, int i) {
        marvelHolder.bindResult(resultList.get(i));
    }

    @Override
    public int getItemCount() {
        return resultList.size();
    }

    public void setResultList(List<Result> resultList){
        this.resultList = resultList;
        notifyDataSetChanged();
    }

    static class MarvelHolder extends RecyclerView.ViewHolder {

        ItemMarvelBinding itemMarvelBinding;

        public MarvelHolder(ItemMarvelBinding itemViewMarvel) {
            super(itemViewMarvel.getRoot());
            this.itemMarvelBinding = itemViewMarvel;
        }

        void bindResult(Result result){
            if( itemMarvelBinding.getItemViewModel() == null){
                itemMarvelBinding.setItemViewModel(new ItemMarvelObservable(result, itemView.getContext()));
            } else {
                itemMarvelBinding.getItemViewModel().setResult(result);
            }
        }

    }

}
