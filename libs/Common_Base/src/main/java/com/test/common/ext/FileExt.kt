package com.test.common.ext

import android.R.attr.data
import android.content.ContentResolver
import android.content.Intent
import android.database.Cursor
import android.net.Uri
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.safmvvm.utils.FileUtil


fun toFileBlock(
    activity: AppCompatActivity,
    title: String,
    isMultiple: Boolean = false,
    block: (activityResult: ActivityResult, intent: Intent?, resultCode: Int, filePath: String?) -> Unit,
) {
    activity.registerForActivityResult(ActivityResultContracts.StartActivityForResult()
    ) {
        var uri = it.data?.data
        val filePath: String? = FileUtil.getFilePathFromUri(activity, uri)
        block(
            it,
            it.data,
            it.resultCode,
            filePath
        )
    }.launch(Intent.createChooser(toFileIntent(isMultiple), title))
}

/**
 * //文件限制
 * https://blog.csdn.net/shexiaoxiaoniu/article/details/103069517
 * https://blog.csdn.net/qq_15827013/article/details/97932357
 */
fun toFileIntent(isMultiple: Boolean = false): Intent {
    val intent = Intent(Intent.ACTION_GET_CONTENT)
    intent.addCategory(Intent.CATEGORY_OPENABLE)
    intent.type = "*/*"
    intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, isMultiple);
    return intent
}