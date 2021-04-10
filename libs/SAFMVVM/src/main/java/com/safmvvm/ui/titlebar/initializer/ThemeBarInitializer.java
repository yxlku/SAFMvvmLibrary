package com.safmvvm.ui.titlebar.initializer;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import com.safmvvm.R;
import com.safmvvm.app.globalconfig.GlobalConfig;
import com.safmvvm.ui.titlebar.SelectorDrawable;


/**
 *    author : Android 轮子哥
 *    github : https://github.com/getActivity/TitleBar
 *    time   : 2020/09/19
 *    desc   : 日间主题样式实现（布局属性：app:barStyle="light"）
 */
public class ThemeBarInitializer extends BaseBarInitializer {

    @Override
    public TextView getLeftView(Context context) {
        TextView leftView = super.getLeftView(context);
        leftView.setTextColor(ContextCompat.getColor(context, R.color.navigationMenuColor));
        setViewBackground(leftView, new SelectorDrawable.Builder()
                .setDefault(new ColorDrawable(0x00000000))
                .setFocused(new ColorDrawable(0x0C000000))
                .setPressed(new ColorDrawable(0x0C000000))
                .build());
        return leftView;
    }

    @Override
    public TextView getTitleView(Context context) {
        TextView titleView = super.getTitleView(context);
        titleView.setTextColor(ContextCompat.getColor(context, R.color.navigationMenuColor));
        return titleView;
    }

    @Override
    public TextView getRightView(Context context) {
        TextView rightView = super.getRightView(context);
        rightView.setTextColor(ContextCompat.getColor(context, R.color.navigationMenuColor));
        setViewBackground(rightView, new SelectorDrawable.Builder()
                .setDefault(new ColorDrawable(0x00000000))
                .setFocused(new ColorDrawable(0x0C000000))
                .setPressed(new ColorDrawable(0x0C000000))
                .build());
        return rightView;
    }

    @Override
    public View getLineView(Context context) {
        View lineView = super.getLineView(context);
        setViewBackground(lineView, new ColorDrawable(0xFFECECEC));
        return lineView;
    }

    @Override
    public Drawable getBackIcon(Context context) {
        return getDrawableResources(context, GlobalConfig.TitleBar.gTitleBarBackIcon);
    }

    @Override
    public Drawable getBackgroundDrawable(Context context) {
        if (GlobalConfig.TitleBar.gTitleBarBg != null) {
            return getDrawableResources(context, GlobalConfig.TitleBar.gTitleBarBg);
        }else{
            return new ColorDrawable(ContextCompat.getColor(context, R.color.navigationBg));
        }

    }
}