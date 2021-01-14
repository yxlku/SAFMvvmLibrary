package com.safmvvm.ext.ui.file;

import android.app.Activity;
import android.app.Fragment;

import androidx.appcompat.app.AppCompatActivity;

import java.lang.ref.WeakReference;

public class AppFileSelector {

    public static final int BY_NAME_ASC = 0;
    public static final int BY_NAME_DESC = 1;
    public static final int BY_TIME_ASC = 2;
    public static final int BY_TIME_DESC = 3;
    public static final int BY_SIZE_ASC = 4;
    public static final int BY_SIZE_DESC = 5;
    public static final int BY_EXTENSION_ASC = 6;

    private final WeakReference<AppCompatActivity> mContext;
    private final WeakReference<Fragment> mFragment;

    private AppFileSelector(AppCompatActivity activity) {
        this(activity,null);
    }

    private AppFileSelector(Fragment fragment){
        this((AppCompatActivity) fragment.getActivity(),fragment);
    }

    private AppFileSelector(AppCompatActivity mContext, Fragment mFragment) {
        this.mContext = new WeakReference<>(mContext);
        this.mFragment = new WeakReference<>(mFragment);
    }

    public static AppSelectCreator from(AppCompatActivity activity){
        return new AppFileSelector(activity).initFile();
    }

    public static AppSelectCreator from(Fragment fragment){
        return new AppFileSelector(fragment).initFile();
    }

    private AppSelectCreator initFile(){
        return new AppSelectCreator(this);
    }


    public AppCompatActivity getActivity() {
        return mContext.get();
    }

    public Fragment getFragment() {
        return mFragment != null ? mFragment.get() : null;
    }



}
