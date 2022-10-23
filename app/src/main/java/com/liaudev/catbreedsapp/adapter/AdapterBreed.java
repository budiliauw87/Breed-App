package com.liaudev.catbreedsapp.adapter;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.paging.PagingDataAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.request.RequestOptions;
import com.liaudev.catbreedsapp.R;
import com.liaudev.catbreedsapp.data.response.BreedItem;
import com.liaudev.catbreedsapp.databinding.RowItemBreedBinding;
import com.liaudev.catbreedsapp.ui.detail.DetailActivity;

/**
 * Created by Budiliauw87 on 2022-06-22.
 * budiliauw87.github.io
 * Budiliauw87@gmail.com
 */
public class AdapterBreed extends PagingDataAdapter<BreedItem, AdapterBreed.ItemViewHolder> {

    public static final int LOADING_ITEM = 0;
    public static final int STORY_ITEM = 1;

    public AdapterBreed() {
        super(new ComparatorBreed());
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ItemViewHolder(RowItemBreedBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        holder.bind(getItem(position));
    }

    @Override
    public int getItemViewType(int position) {
        // set ViewType
        return position == getItemCount() ? STORY_ITEM : LOADING_ITEM;
    }

    public static class ItemViewHolder extends RecyclerView.ViewHolder {
        RowItemBreedBinding itemBreedBinding;

        public ItemViewHolder(@NonNull RowItemBreedBinding binding) {
            super(binding.getRoot());
            itemBreedBinding = binding;
        }

        public void bind(BreedItem breedItem) {
            if (breedItem != null) {
                itemBreedBinding.tvName.setText(breedItem.getName());
                itemBreedBinding.tvDes.setText(breedItem.getDescription());
                itemBreedBinding.tvOrigin.setText(breedItem.getOrigin());
                RequestOptions options = new RequestOptions();
                RequestBuilder<Drawable> requestBuilder = Glide.with(itemView.getContext())
                        .asDrawable().sizeMultiplier(0.25f);
                options.placeholder(R.drawable.ic_launcher_foreground);
                options.centerCrop();
                Glide.with(itemView.getContext())
                        .load(breedItem.getImage()) // Load the image
                        .thumbnail(requestBuilder)
                        .apply(options)
                        .into(itemBreedBinding.thumbnail);

                itemBreedBinding.getRoot().setOnClickListener((v) -> {
                    try {
                        Intent intent = new Intent(itemView.getContext(), DetailActivity.class);
                        intent.putExtra("breedObj", breedItem);
                        itemView.getContext().startActivity(intent);
                    } catch (Exception e) {
                        e.printStackTrace();
                        Toast.makeText(itemView.getContext(), R.string.something_error, Toast.LENGTH_SHORT).show();
                    }
                });

            }
        }
    }
}
