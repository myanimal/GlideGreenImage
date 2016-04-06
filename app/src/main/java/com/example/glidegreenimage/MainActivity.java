package com.example.glidegreenimage;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;

import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.cache.DiskCache;
import com.bumptech.glide.module.GlideModule;

public class MainActivity extends AppCompatActivity {

    String URL = "https://www.bitx.co/static/images/cards/world-languages.jpg";
    private ImageView image;
    private RadioButton radio_ARGB_8888;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        image = (ImageView) findViewById(R.id.image);
        radio_ARGB_8888 = (RadioButton) findViewById(R.id.radio2);
        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoadImage();
            }
        });

        new GlideBuilder(this).setDiskCache(new DiskCache.Factory() {
            @Override
            public DiskCache build() {
                return null;
            }
        });
    }

    private void LoadImage() {
        DecodeFormat format = DecodeFormat.PREFER_RGB_565;
        if (radio_ARGB_8888.isChecked()) {
            format = DecodeFormat.PREFER_ARGB_8888;
            Log.i("Glide", "Using format: PREFER_ARGB_8888");
        } else {
            Log.i("Glide", "Using format: PREFER_RGB_565");
        }
        Glide.clear(image);
        Glide.with(this)
                .load(URL).asBitmap().format(format)
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .skipMemoryCache(true)
                .into(image);
    }
}
