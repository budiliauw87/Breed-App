package com.liaudev.catbreedsapp.data;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.Pager;
import androidx.paging.PagingConfig;

import com.android.volley.Request;
import com.liaudev.catbreedsapp.data.response.BreedItem;
import com.liaudev.catbreedsapp.network.ApiRequest;
import com.liaudev.catbreedsapp.network.CustomRequest;
import com.liaudev.catbreedsapp.ui.banner.BannerData;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Budiliauw87 on 2022-10-23.
 * budiliauw87.github.io
 * Budiliauw87@gmail.com
 */
public class BreedRepository {
    private static BreedRepository INSTANCE;
    private final ApiRequest api;

    private BreedRepository(ApiRequest apiRequest) {
        this.api = apiRequest;
    }

    public static synchronized BreedRepository getInstance(ApiRequest apiRequest) {
        if (INSTANCE == null) {
            INSTANCE = new BreedRepository(apiRequest);
        }
        return INSTANCE;
    }

    public Pager<Integer, BreedItem> getBreeds(String query) {
        return new Pager<>(
                new PagingConfig(10),
                null,
                () -> new BreedPagingSource(query, api));
    }


    public LiveData<Resource<List<BannerData>>> getListImage(String idBreed) {
        MutableLiveData<Resource<List<BannerData>>> mutableLiveData = new MutableLiveData<>();
        mutableLiveData.setValue(Resource.loading(null));
        String urlFetchImage = "https://api.thecatapi.com/v1/images/search?limit=8&breed_ids=" + idBreed;
        api.addToRequestQueue(new CustomRequest(Request.Method.GET, urlFetchImage,
                null, response -> {

            List<BannerData> bannerDatas = new ArrayList<>();
            for (int i = 0; i < response.length(); i++) {
                try {
                    JSONObject responseObj = response.getJSONObject(i);
                    BannerData bannerData = new BannerData();
                    bannerData.setId(responseObj.getString("id"));
                    bannerData.setUrlimg(responseObj.getString("url"));
                    bannerDatas.add(bannerData);
                } catch (JSONException e) {
                    e.printStackTrace();

                }
            }
            mutableLiveData.setValue(Resource.success(bannerDatas));

        }, error -> {
            final String errorMsg = api.parseNetworkError(error);
            mutableLiveData.setValue(Resource.error(errorMsg, null));
        }));
        return mutableLiveData;
    }

}
