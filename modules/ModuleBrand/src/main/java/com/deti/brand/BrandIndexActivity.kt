package com.deti.brand

import android.graphics.Color
import android.util.TypedValue
import androidx.annotation.DrawableRes
import com.alibaba.android.arouter.facade.annotation.Route
import com.deti.brand.databinding.BrandActivityIndexBinding
import com.safmvvm.ext.ui.tab.bottom.NewNormalItemView
import com.safmvvm.mvvm.view.BaseActivity
import com.test.common.RouterActivityPath
import com.deti.brand.BR
import com.safmvvm.utils.DensityUtil
import com.scwang.smart.refresh.layout.util.DesignUtil
import me.jessyan.autosize.utils.AutoSizeUtils

@Route(path = RouterActivityPath.ModuleBrand.PAGE_INDEX)
class BrandIndexActivity: BaseActivity<BrandActivityIndexBinding, BrandIndexViewModel>(
    R.layout.brand_activity_index,
    BR.viewModel
) {

    override fun initData() {
        //取消侧滑返回
        cleanSwipeback()

        var tabMain = newTabItem(R.mipmap.brand_index_tab_main_checked, R.mipmap.brand_index_tab_msg_uncheck, "首页")
        var tabMsg = newTabItem(R.mipmap.brand_index_tab_msg_uncheck, R.mipmap.brand_index_tab_msg_uncheck, "消息")
        var tabMine = newTabItem(R.mipmap.brand_index_tab_msg_uncheck, R.mipmap.brand_index_tab_msg_uncheck, "我的")
        tabMsg.tintMessageBackground(Color.parseColor("#FCCE48"))
        tabMsg.setMessageNumberColor(Color.parseColor("#333333"))
        mBinding.pnvTab.custom().apply {
            addItem(tabMain)
            addItem(tabMsg)
            addItem(tabMine)
        }.build().run {
            setSelect(0)
        }
        tabMsg.setMessageNumber(100)
    }

    fun newTabItem(
        @DrawableRes uncheckIcon: Int,
        @DrawableRes checkedIcon: Int,
        text: String
    ): NewNormalItemView = NewNormalItemView(this).apply {
        initialize(uncheckIcon, checkedIcon, text)
        setTextDefaultColor(Color.parseColor("#999999"))
        setTextCheckedColor(Color.parseColor("#FCCE48"))
        setIconSize(24, 24)
        setTitleSize(12)
    }

}