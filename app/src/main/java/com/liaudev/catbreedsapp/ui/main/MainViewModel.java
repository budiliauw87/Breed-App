package com.liaudev.catbreedsapp.ui.main;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelKt;
import androidx.paging.ExperimentalPagingApi;
import androidx.paging.PagingData;
import androidx.paging.PagingLiveData;

import com.liaudev.catbreedsapp.data.BreedRepository;
import com.liaudev.catbreedsapp.data.response.BreedItem;

import kotlinx.coroutines.CoroutineScope;

/**
 * Created by Budiliauw87 on 2022-10-23.
 * budiliauw87.github.io
 * Budiliauw87@gmail.com
 */
public class MainViewModel extends ViewModel {
    private final BreedRepository repository;
    private final MutableLiveData<String> queryLiveData = new MutableLiveData<>();
    private final LiveData<PagingData<BreedItem>> pagingDataLiveData;

    public MainViewModel(BreedRepository breedRepository) {
        this.repository = breedRepository;
        queryLiveData.setValue("");
        CoroutineScope coroutineScope = ViewModelKt.getViewModelScope(this);
        pagingDataLiveData = Transformations.switchMap(queryLiveData, query ->
                PagingLiveData.cachedIn(PagingLiveData.getLiveData(repository.getBreeds(query)), coroutineScope)
        );
    }

    @ExperimentalPagingApi
    public LiveData<PagingData<BreedItem>> getBreeds() {
        return pagingDataLiveData;
    }

    public void findBreeds(String query) {
        final String querySearch = query == null ? "" : query;
        queryLiveData.setValue(querySearch);
    }

}
