package com.liaudev.catbreedsapp.ui;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.liaudev.catbreedsapp.R;
import com.liaudev.catbreedsapp.utils.Utils;

/**
 * Created by Budiliauw87 on 2022-05-14.
 * budiliauw87.github.io
 * Budiliauw87@gmail.com
 */
public class BaseActivity extends AppCompatActivity {
    Window window;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        window = getWindow();
    }

    public void updateStatusBarColor(int color) {
        try {
            if (window == null) window = getWindow();
            //check night mode if yes, set systembar color to black
            if (Utils.isNightMode(getApplicationContext()))
                color = ContextCompat.getColor(getApplicationContext(), R.color.black);

            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(color);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
