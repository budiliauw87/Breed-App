package com.liaudev.catbreedsapp.ui.detail;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.Toast;

import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.chip.Chip;
import com.google.android.material.shape.ShapeAppearanceModel;
import com.liaudev.catbreedsapp.R;
import com.liaudev.catbreedsapp.data.response.BreedItem;
import com.liaudev.catbreedsapp.databinding.ActivityDetailBinding;
import com.liaudev.catbreedsapp.di.ViewModelFactory;
import com.liaudev.catbreedsapp.ui.BaseActivity;
import com.liaudev.catbreedsapp.ui.banner.DetailBannerAdapter;
import com.zhpan.bannerview.BannerViewPager;
import com.zhpan.bannerview.constants.IndicatorGravity;
import com.zhpan.indicator.enums.IndicatorSlideMode;
import com.zhpan.indicator.enums.IndicatorStyle;

/**
 * Created by Budiliauw87 on 2022-10-23.
 * budiliauw87.github.io
 * Budiliauw87@gmail.com
 */
public class DetailActivity extends BaseActivity {
    private ActivityDetailBinding binding;
    private DetailBannerAdapter bannerAdapter;
    private boolean isExpand = false;

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
        if (breedItem != null) {
            binding.content.tvName.setText(breedItem.getName());
            binding.content.tvOrigin.setText(breedItem.getOrigin());
            binding.content.tvAltName.setText(breedItem.getId());
            binding.content.tvTitleDes.setText(breedItem.getDescription());
            binding.content.tvLifeSpan.setText(breedItem.getLifeSpan());
            if(breedItem.getAdaptability()!=0){
                binding.content.ratingAdaptability.setRating(breedItem.getAdaptability());
            }
            if(breedItem.getAffectionLevel()!=0){
                binding.content.ratingAffection.setRating(breedItem.getAffectionLevel());
            }
            if(breedItem.getEnergyLevel()!=0){
                binding.content.ratingEnergy.setRating(breedItem.getEnergyLevel());
            }
            if(breedItem.getIntelligence()!=0){
                binding.content.ratingIntelligence.setRating(breedItem.getIntelligence());
            }
            if(breedItem.getHealthIssues()!=0){
                binding.content.ratingHealth.setRating(breedItem.getHealthIssues());
            }

            if (breedItem.getTemperament() != null && !breedItem.getTemperament().isEmpty()) {
                String[] arrTemprament = breedItem.getTemperament().split(",");
                ShapeAppearanceModel shapeAppearanceModel = new ShapeAppearanceModel();
                shapeAppearanceModel.toBuilder().build();

                for (int i = 0; i < arrTemprament.length; i++) {
                    Chip chip = new Chip(this);
                    chip.setId(i);
                    chip.setText(arrTemprament[i]);
                    chip.setClickable(false);
                    chip.setShapeAppearanceModel(shapeAppearanceModel);
                    binding.content.chipGroup.addView(chip);
                }
            }
            binding.content.toggleExpandTitle.setOnClickListener((v) -> {
                if (isExpand) {
                    createRotateAnimator(v, 180f, 0f).start();
                    binding.content.layoutDetail.setVisibility(View.GONE);
                    isExpand = false;
                } else {
                    createRotateAnimator(v, 0f, 180f).start();
                    binding.content.layoutDetail.setVisibility(View.VISIBLE);
                    isExpand = true;
                }
            });

            //open url wiki
            binding.content.btnWiki.setOnClickListener((v)->{
                try {
                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(breedItem.getWikipediaUrl()));
                    startActivity(browserIntent);
                }catch (Exception e){
                    Toast.makeText(getApplicationContext(), getString(R.string.something_error), Toast.LENGTH_SHORT).show();
                }
            });

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

            viewModel.getListImage(breedItem.getId()).observe(this, banners -> {
                switch (banners.status) {
                    case ERROR:
                        Toast.makeText(getApplicationContext(), banners.message, Toast.LENGTH_SHORT).show();
                        break;
                    case LOADING:
                        break;
                    case SUCCESS:
                        if (banners.data != null) {
                            binding.bannerView.refreshData(banners.data);
                        }
                        break;
                }
            });
        }
    }

    //animation rotate vector
    private ObjectAnimator createRotateAnimator(final View target, final float from, final float to) {
        ObjectAnimator animator = ObjectAnimator.ofFloat(target, "rotation", from, to);
        animator.setDuration(300);
        animator.setInterpolator(new LinearInterpolator());
        return animator;
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
