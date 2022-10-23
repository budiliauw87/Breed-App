package com.liaudev.catbreedsapp.ui.detail;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager2.widget.ViewPager2;

import com.liaudev.catbreedsapp.App;
import com.liaudev.catbreedsapp.R;
import com.liaudev.catbreedsapp.data.response.BreedItem;
import com.liaudev.catbreedsapp.databinding.ActivityDetailBinding;
import com.liaudev.catbreedsapp.di.ViewModelFactory;
import com.liaudev.catbreedsapp.ui.BaseActivity;
import com.liaudev.catbreedsapp.ui.banner.BannerData;
import com.liaudev.catbreedsapp.ui.banner.DetailBannerAdapter;
import com.liaudev.catbreedsapp.ui.main.MainActivity;
import com.ms.square.android.expandabletextview.ExpandableTextView;
import com.zhpan.bannerview.BannerViewPager;
import com.zhpan.bannerview.BaseViewHolder;
import com.zhpan.bannerview.constants.IndicatorGravity;
import com.zhpan.indicator.enums.IndicatorSlideMode;
import com.zhpan.indicator.enums.IndicatorStyle;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Budiliauw87 on 2022-10-23.
 * budiliauw87.github.io
 * Budiliauw87@gmail.com
 */
public class DetailActivity extends BaseActivity {
    private ActivityDetailBinding binding;
    private DetailBannerAdapter bannerAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setSupportActionBar(binding.toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        ViewModelFactory factory = ViewModelFactory.getInstance(this);
        DetailViewModel viewModel = new ViewModelProvider(this, (ViewModelProvider.Factory) factory).get(DetailViewModel.class);
        BreedItem breedItem = getIntent().getParcelableExtra("breedObj");
        if(breedItem!=null){
            binding.tvName.setText(breedItem.getName());
            binding.tvOrigin.setText(breedItem.getOrigin());
            // getting reference of  ExpandableTextView
            ExpandableTextView expTv = (ExpandableTextView) findViewById(R.id.expand_text_view).findViewById(R.id.expand_text_view);
            expTv.setText(breedItem.getDescription());

            bannerAdapter = new DetailBannerAdapter();
            binding.bannerView
                    .setAutoPlay(true)
                    .setScrollDuration(800)
                    .setLifecycleRegistry(getLifecycle())
                    .setIndicatorStyle(IndicatorStyle.ROUND_RECT)
                    .setIndicatorGravity(IndicatorGravity.START)
                    .setIndicatorSliderGap(getResources().getDimensionPixelOffset(R.dimen.spacing_medium))
                    .setIndicatorMargin(50, 0, 0, 50)
                    .setIndicatorSlideMode(IndicatorSlideMode.SCALE)
                    .setIndicatorSliderWidth(getResources().getDimensionPixelOffset(R.dimen.spacing_medium), getResources().getDimensionPixelOffset(R.dimen.spacing_middle))
                    .setIndicatorSliderColor(ContextCompat.getColor(getApplicationContext(), R.color.purple_500), ContextCompat.getColor(getApplicationContext(), R.color.purple_700))
                    .setInterval(3000)
                    .setAdapter(bannerAdapter)
                    .registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
                        @Override
                        public void onPageSelected(int position) {
                            super.onPageSelected(position);
                        }
                    })
                    .setOnPageClickListener(new BannerViewPager.OnPageClickListener() {
                        @Override
                        public void onPageClick(int position) {

                        }
                    }).create();

            viewModel.getListImage(breedItem.getId()).observe(this,banners -> {
                switch (banners.status) {
                    case ERROR:
                        Log.e("Detail","ERROR");
                        Toast.makeText(getApplicationContext(), banners.message, Toast.LENGTH_SHORT).show();
                        break;
                    case LOADING:
                        Log.e("Detail","LOADING");
                        break;
                    case SUCCESS:
                        Log.e("Detail","SUCCESS");
                        if (banners.data != null) {
                            binding.bannerView.refreshData(banners.data);
                        }
                        break;
                }
            });
        }


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onResume() {
        super.onResume();
        if (binding.bannerView != null) {
            binding.bannerView.startLoop();
        }

    }


    @Override
    public void onPause() {
        if (binding.bannerView != null)
            binding.bannerView.stopLoop();
        super.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
        if (binding.bannerView != null) {
            binding.bannerView.stopLoop();
        }
    }
}
