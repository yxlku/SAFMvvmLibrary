package com.safmvvm.ext.ui.viewpager2.transformations;

import android.view.View;

import androidx.viewpager2.widget.ViewPager2;

/**
 * 渐变
 */
public class FadeOutTransformation implements ViewPager2.PageTransformer{
    @Override
    public void transformPage(View page, float position) {

        page.setTranslationX(-position*page.getWidth());

        page.setAlpha(1-Math.abs(position));

    }
}