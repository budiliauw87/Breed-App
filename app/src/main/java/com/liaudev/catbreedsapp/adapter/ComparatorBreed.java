package com.liaudev.catbreedsapp.adapter;

import android.annotation.SuppressLint;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;

import com.liaudev.catbreedsapp.data.response.BreedItem;


/**
 * Created by Budiliauw87 on 2022-06-22.
 * budiliauw87.github.io
 * Budiliauw87@gmail.com
 */
public class ComparatorBreed extends DiffUtil.ItemCallback<BreedItem> {
    @Override
    public boolean areItemsTheSame(@NonNull BreedItem oldItem, @NonNull BreedItem newItem) {
        return oldItem.getId().equals(newItem.getId());
    }

    @SuppressLint("DiffUtilEquals")
    @Override
    public boolean areContentsTheSame(@NonNull BreedItem oldItem, @NonNull BreedItem newItem) {
        return oldItem.equals(newItem);
    }
}
