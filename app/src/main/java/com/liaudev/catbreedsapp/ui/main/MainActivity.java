package com.liaudev.catbreedsapp.ui.main;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.widget.SearchView;
import androidx.lifecycle.ViewModelProvider;
import androidx.paging.LoadState;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.liaudev.catbreedsapp.App;
import com.liaudev.catbreedsapp.R;
import com.liaudev.catbreedsapp.adapter.AdapterBreed;
import com.liaudev.catbreedsapp.adapter.AdapterLoadState;
import com.liaudev.catbreedsapp.databinding.ActivityMainBinding;
import com.liaudev.catbreedsapp.di.ViewModelFactory;
import com.liaudev.catbreedsapp.ui.BaseActivity;
import com.liaudev.catbreedsapp.utils.Utils;

/**
 * Created by Budiliauw87 on 2022-10-21.
 * budiliauw87.github.io
 * Budiliauw87@gmail.com
 */
public class MainActivity extends BaseActivity {
    private ActivityMainBinding binding;
    private String queryBreed = "";
    private AdapterBreed adapterBreed;

    @SuppressLint("UnsafeOptInUsageError")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.imgDarkMode.setOnClickListener((v) -> {
            final String themeMode = Utils.isNightMode(this) ? "light" : "dark";
            App.getInstance().saveThemeMode(themeMode);
        });
        ViewModelFactory factory = ViewModelFactory.getInstance(this);
        MainViewModel viewModel = new ViewModelProvider(this, (ViewModelProvider.Factory) factory).get(MainViewModel.class);
        binding.searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                hideAllLayout();
                viewModel.findBreeds(queryBreed);
                adapterBreed.refresh();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                queryBreed = newText;
                return false;
            }
        });
        setImageTheme();
        binding.swipeRefresh.setRefreshing(true);
        binding.btnRefresh.setOnClickListener((v)->{
            hideAllLayout();
            adapterBreed.refresh();
        });
        adapterBreed = new AdapterBreed();
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
        binding.recyclerView.setAdapter(adapterBreed.withLoadStateFooter(new AdapterLoadState((v) -> {
            adapterBreed.retry();
        })));
        adapterBreed.addLoadStateListener(combinedLoadStates -> {
            if (combinedLoadStates.getRefresh() instanceof LoadState.NotLoading &&
                    combinedLoadStates.getPrepend().getEndOfPaginationReached()) {
                if (adapterBreed.getItemCount() > 0) {
                    hideErrorLayout();
                } else {
                    showErrorLayout();
                }
            } else if (combinedLoadStates.getRefresh() instanceof LoadState.Error) {
                showErrorLayout();
            }

            return null;
        });

        binding.swipeRefresh.setOnRefreshListener(() -> {
            hideAllLayout();
            adapterBreed.refresh();
        });

        viewModel.getBreeds().observe(this, (pagingData -> {
            adapterBreed.submitData(getLifecycle(), pagingData);
        }));



    }

    private void hideAllLayout() {

        try {
            if (binding != null) {
                if (binding.layoutError.getVisibility() == View.VISIBLE) {
                    binding.layoutError.setVisibility(View.GONE);
                }
                if (binding.recyclerView.getVisibility() == View.VISIBLE) {
                    binding.recyclerView.setVisibility(View.GONE);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void showErrorLayout() {
        try {
            if (binding != null) {
                if (binding.recyclerView.getVisibility() == View.VISIBLE) {
                    binding.recyclerView.setVisibility(View.GONE);
                }
                if (binding.layoutError.getVisibility() == View.GONE) {
                    binding.layoutError.setVisibility(View.VISIBLE);
                }
                if (binding.swipeRefresh.isRefreshing()) {
                    binding.swipeRefresh.setRefreshing(false);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void hideErrorLayout() {
        try {
            if (binding != null) {
                if (binding.layoutError.getVisibility() == View.VISIBLE) {
                    binding.layoutError.setVisibility(View.GONE);
                }
                if (binding.recyclerView.getVisibility() == View.GONE) {
                    binding.recyclerView.setVisibility(View.VISIBLE);
                }
                if (binding.swipeRefresh.isRefreshing()) {
                    binding.swipeRefresh.setRefreshing(false);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void setImageTheme() {
        int resThemeId = Utils.isNightMode(this) ? R.drawable.ic_baseline_light_mode_24 : R.drawable.ic_baseline_dark_mode_24;
        binding.imgDarkMode.setImageResource(resThemeId);
    }
}
