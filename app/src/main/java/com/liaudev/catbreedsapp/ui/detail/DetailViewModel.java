package com.liaudev.catbreedsapp.ui.detail;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.liaudev.catbreedsapp.data.BreedRepository;
import com.liaudev.catbreedsapp.data.Resource;
import com.liaudev.catbreedsapp.ui.banner.BannerData;

import java.util.List;

/**
 * Created by Budiliauw87 on 2022-10-23.
 * budiliauw87.github.io
 * Budiliauw87@gmail.com
 */
public class DetailViewModel extends ViewModel {
    private final BreedRepository repository;
    public DetailViewModel(BreedRepository breedRepository) {
        this.repository = breedRepository;
    }

    public  LiveData<Resource<List<BannerData>>> getListImage(String idBreed){
        return repository.getListImage(idBreed);
    }
}
