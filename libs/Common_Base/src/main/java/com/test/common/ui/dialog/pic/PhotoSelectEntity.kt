package com.test.common.ui.dialog.pic

import com.luck.picture.lib.entity.LocalMedia

data class PhotoSelectEntity(

    var localMedia: LocalMedia? = null,
    var isSelect: Boolean= false
)