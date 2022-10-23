package com.liaudev.catbreedsapp.data;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.paging.PagingState;
import androidx.paging.rxjava2.RxPagingSource;

import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.liaudev.catbreedsapp.data.response.BreedItem;
import com.liaudev.catbreedsapp.network.ApiRequest;
import com.liaudev.catbreedsapp.network.CustomRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Single;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Budiliauw87 on 2022-10-23.
 * budiliauw87.github.io
 * Budiliauw87@gmail.com
 */
public class BreedPagingSource extends RxPagingSource<Integer, BreedItem> {
    private final ApiRequest api;
    private String mQuery;

    public BreedPagingSource(String query, ApiRequest apiRequest) {
        this.api = apiRequest;
        this.mQuery = query;
    }

    @Nullable
    @Override
    public Integer getRefreshKey(@NonNull PagingState<Integer, BreedItem> pagingState) {
        return null;
    }

    @NonNull
    @Override
    public Single<LoadResult<Integer, BreedItem>> loadSingle(@NonNull LoadParams<Integer> loadParams) {
        try {
            // If page number is already there then init page variable with it otherwise we are loading fist page
            int page = loadParams.getKey() != null ? loadParams.getKey() : 0;
            // Send request to server with page number
            return fetchBreeds(mQuery, page)
                    .subscribeOn(Schedulers.io())
                    .map((Function<List<BreedItem>, LoadResult<Integer, BreedItem>>) response -> {
                        final Integer prevPage = page == 0 ? null : page - 1;
                        Integer nextPage;
                        if(mQuery.isEmpty()){
                            nextPage = response.size() == 0 ? null : page+1;
                        }else{
                            nextPage = null;
                        }
                        return new LoadResult.Page(response, prevPage, nextPage);
                    }).onErrorReturn(LoadResult.Error::new);
        } catch (Exception e) {
            // Request ran into error return error
            return Single.just(new LoadResult.Error(e));
        }
    }

    private Single<List<BreedItem>> fetchBreeds(String query, int page) {
        return Single.create((emitter) -> {

            try {
                final String limit = "10";
                String urlFetchBreed = "https://api.thecatapi.com/v1/breeds";
                if(!query.isEmpty()){
                    urlFetchBreed+="/search?q="+query;
                }else{
                    urlFetchBreed+="?limit=10&page="+page;
                }
                Log.e("PagingSource",urlFetchBreed);
                api.addToRequestQueue(new CustomRequest(Request.Method.GET, urlFetchBreed,
                        null, response -> {
                    List<BreedItem> breedItemList = new ArrayList<>();
                    for (int i = 0; i < response.length(); i++) {
                        try {
                            JSONObject responseObj = response.getJSONObject(i);
                            BreedItem item = new BreedItem();
                            item.setId(responseObj.getString("id"));
                            item.setName(responseObj.getString("name"));
                            item.setTemperament(responseObj.getString("temperament"));
                            item.setDescription(responseObj.getString("description"));
                            item.setOrigin(responseObj.getString("origin"));
                            if(responseObj.has("image")){
                                JSONObject imageObj = responseObj.getJSONObject("image");
                                item.setImage(imageObj.getString("url"));
                            }
                            breedItemList.add(item);
                        } catch (JSONException e) {
                            e.printStackTrace();
                            emitter.onError(e);
                        }
                    }

                    emitter.onSuccess(breedItemList);
                }, error -> {
                    final String errorMsg = api.parseNetworkError(error);
                    emitter.onError(new VolleyError(errorMsg));
                }));

            } catch (Exception e) {
                e.printStackTrace();
                emitter.onError(e);
            }
        });
    }

}