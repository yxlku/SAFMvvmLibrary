package com.safmvvm.ext.ui.file;

import android.app.Activity;
import android.app.Application;
import android.app.Fragment;
import android.content.Intent;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import com.zlylib.fileselectorlib.SelectOptions;
import com.zlylib.fileselectorlib.ui.FileSelectorActivity;

/**
 * SelectCreator
 * Created by zhangliyang on 2020/6/20.
 */

public final class AppSelectCreator {

    private final AppFileSelector filePicker;
    private final SelectOptions selectOptions;

    public AppSelectCreator(AppFileSelector filePicker ) {
        selectOptions = SelectOptions.getCleanInstance();
        this.filePicker = filePicker;
    }

    public AppSelectCreator setMaxCount(int maxCount) {
        selectOptions.maxCount = maxCount;
        if (maxCount <= 1) {
            selectOptions.maxCount = 1;
            selectOptions.isSingle = true;
        } else {
            selectOptions.isSingle = false;
        }
        return this;
    }


    public AppSelectCreator setTargetPath(String path){
        selectOptions.targetPath = path;
        return this;
    }



    public AppSelectCreator setFileTypes(String... fileTypes) {
        selectOptions.mFileTypes = fileTypes;
        return this;
    }

    public AppSelectCreator setSortType(int sortType) {
        selectOptions.setSortType(sortType);
        return this;
    }

    public AppSelectCreator isSingle() {
        selectOptions.isSingle = true;
        selectOptions.maxCount = 1;
        return this;
    }

    public AppSelectCreator onlyShowFolder() {
        selectOptions.setOnlyShowFolder(true);
        selectOptions.setOnlySelectFolder(true);
        return this;
    }
    public AppSelectCreator onlySelectFolder() {
        selectOptions.setOnlySelectFolder(true);
        return this;
    }

   /* public SelectCreator onlyShowImages() {
        selectOptions.onlyShowImages = true;
        return this;
    }

    public SelectCreator onlyShowVideos() {
        selectOptions.onlyShowVideos = true;
        return this;
    }
*/

    public AppSelectCreator requestCode(int requestCode) {
        selectOptions.request_code = requestCode;
        return this;
    }

    public void start(ActivityResultCallback callbacks) {
        final AppCompatActivity activity = filePicker.getActivity();
        if (activity == null) {
            return;
        }
        Intent intent = new Intent();
        intent.setClass(activity, FileSelectorActivity.class);
//        Fragment fragment = filePicker.getFragment();

        activity.registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), callbacks)
                .launch(intent);
    }

}
