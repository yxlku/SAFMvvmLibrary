package com.test.common.ext

import android.app.Activity
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultCallback
import androidx.appcompat.app.AppCompatActivity
import com.safmvvm.ext.ui.file.AppFileSelector
import com.zlylib.fileselectorlib.FileSelector
import com.zlylib.fileselectorlib.bean.EssFile
import com.zlylib.fileselectorlib.utils.Const

/**
 * 选择文件
 */
fun chooseFile(
    mActivity: AppCompatActivity?,
    /** 选择文件后的回调*/
    block: (filePath: String) -> Unit
){
    mActivity?.apply {

        /**
         *  设置 onlyShowFolder() 只显示文件夹 后 再设置setFileTypes（）不生效
         *  设置 onlyShowFolder() 只显示文件夹 后 默认设置了onlySelectFolder（）
         *  设置 onlySelectFolder() 只能选择文件夹 后 默认设置了isSingle（）
         *  设置 isSingle() 只能选择一个 后 再设置了setMaxCount（） 不生效
         *
         */
        AppFileSelector.from(this)
            // .onlyShowFolder()  //只显示文件夹
            //.onlySelectFolder()  //只能选择文件夹
            .isSingle // 只能选择一个
            .setMaxCount(1) //设置最大选择数
            .setFileTypes("png", "doc", "apk", "mp3", "gif", "txt", "mp4", "zip") //设置文件类型
            .setSortType(FileSelector.BY_NAME_ASC) //设置名字排序
            .start(object : ActivityResultCallback<ActivityResult> {
                override fun onActivityResult(result: ActivityResult?) {
                    result?.apply {
                        val fileList = data?.getParcelableArrayListExtra<EssFile>(Const.EXTRA_RESULT_SELECTION)
                        fileList?.apply {
                            val builder = StringBuilder()
                            forEach {
                                builder.append(it.absolutePath).append("\n")
                            }
                            block(builder.toString())
                        }
                    }
                }
            })
    }
}