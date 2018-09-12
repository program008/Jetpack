package com.mg.axe.palattesimple;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.graphics.Palette;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

public class PalettesBasicActivity extends AppCompatActivity {

    private Bitmap bitmap;

    private TextView tvDarkMutedColor;
    private TextView tvDarkVibrantColor;
    private TextView tvLightVibrantColor;
    private TextView tvLightMutedColor;
    private TextView tvVibrantColor;
    private TextView tvMutedColor;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_palettebasic);
        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.fj);
        tvDarkMutedColor = (TextView) findViewById(R.id.tvDarkMutedColor);
        tvDarkVibrantColor = (TextView) findViewById(R.id.tvDarkVibrantColor);
        tvLightVibrantColor = (TextView) findViewById(R.id.tvLightVibrantColor);
        tvLightMutedColor = (TextView) findViewById(R.id.tvLightMutedColor);
        tvVibrantColor = (TextView) findViewById(R.id.tvVibrantColor);
        tvMutedColor = (TextView) findViewById(R.id.tvMutedColor);
        imageView = (ImageView) findViewById(R.id.imageView);
        imageView.setImageBitmap(bitmap);
        getPaletteColor();
    }

    private void getPaletteColor() {
        Palette.from(bitmap).generate(new Palette.PaletteAsyncListener() {
            @Override
            public void onGenerated(Palette palette) {
                // 获取到柔和的深色的颜色（可传默认值）
                int darkMutedColor = palette.getDarkMutedColor(Color.BLUE);
                // 获取到活跃的深色的颜色（可传默认值）
                int darkVibrantColor = palette.getDarkVibrantColor(Color.BLUE);
                // 获取到活跃的明亮的颜色（可传默认值）
                int lightVibrantColor = palette.getLightVibrantColor(Color.BLUE);
                // 获取到柔和的明亮的颜色（可传默认值）
                int lightMutedColor = palette.getLightMutedColor(Color.BLUE);
                // 获取图片中最活跃的颜色（也可以说整个图片出现最多的颜色）（可传默认值）
                int vibrantColor = palette.getVibrantColor(Color.BLUE);
                // 获取图片中一个最柔和的颜色（可传默认值）
                int mutedColor = palette.getMutedColor(Color.BLUE);

                tvDarkMutedColor.setBackgroundColor(darkMutedColor);
                tvDarkVibrantColor.setBackgroundColor(darkVibrantColor);
                tvLightMutedColor.setBackgroundColor(lightMutedColor);
                tvLightVibrantColor.setBackgroundColor(lightVibrantColor);
                tvMutedColor.setBackgroundColor(mutedColor);
                tvVibrantColor.setBackgroundColor(vibrantColor);

                Palette.Swatch vibrantSwatch = palette.getVibrantSwatch();
                int rgb = vibrantSwatch.getRgb();
                if (Build.VERSION.SDK_INT > 21) {
                    Window window = getWindow();
                    //状态栏改变颜色。
                    int color = changeColor(rgb);
                    window.setStatusBarColor(color);
                }

            }
        });
    }

    private int changeColor(int rgb) {
        int red = rgb >> 16 & 0xFF;
        int green = rgb >> 8 & 0xFF;
        int blue = rgb & 0xFF;
        red = (int) Math.floor(red * (1 - 0.2));
        green = (int) Math.floor(green * (1 - 0.2));
        blue = (int) Math.floor(blue * (1 - 0.2));
        return Color.rgb(red, green, blue);
    }
}
