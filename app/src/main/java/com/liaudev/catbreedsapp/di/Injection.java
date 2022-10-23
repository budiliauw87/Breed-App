package com.liaudev.catbreedsapp.di;

import android.content.Context;

import com.liaudev.catbreedsapp.data.BreedRepository;
import com.liaudev.catbreedsapp.network.ApiRequest;

/**
 * Created by Budiliauw87 on 2022-10-23.
 * budiliauw87.github.io
 * Budiliauw87@gmail.com
 */
public class Injection {
    public static BreedRepository provideRepository(Context context) {
        return BreedRepository.getInstance(new ApiRequest(context));
    }
}
