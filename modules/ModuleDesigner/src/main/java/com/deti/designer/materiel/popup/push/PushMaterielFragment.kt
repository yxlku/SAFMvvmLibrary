package com.deti.designer.materiel.popup.push

import android.content.Context
import android.graphics.Color
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.viewpager.widget.ViewPager
import com.deti.designer.R
import com.deti.designer.BR
import com.deti.designer.databinding.DesignerPopupPushMaterielBinding
import com.deti.designer.materiel.popup.push.order.DistributeOrderFragment
import com.safmvvm.ext.ui.tab.ITabTop
import com.safmvvm.ext.ui.viewpager.createViewPager
import com.safmvvm.mvvm.view.bottom.BaseBottomFragment
import com.safmvvm.ui.autosize.setTextSizeAuto
import net.lucode.hackware.magicindicator.MagicIndicator
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.SimplePagerTitleView

/**
 * 物料推送
 */
class PushMaterielFragment: BaseBottomFragment<DesignerPopupPushMaterielBinding, PushMaterielViewModel>(
    R.layout.designer_popup_push_materiel,
    BR.viewModel
), ITabTop {
    var titles = arrayListOf("派单", "抢单")
    var distributeOrderFragment = DistributeOrderFragment(DistributeOrderFragment.ORDER_DISPATCH) //派单
    var grabOrderFragment = DistributeOrderFragment(DistributeOrderFragment.ORDER_GRAB)    //抢单
    override fun initData() {
        super.initData()
        initTabTop(context, mBinding.miTab, null, titles)

        var transaction = childFragmentManager.beginTransaction()
        transaction.apply {
            add(R.id.fl_layout, distributeOrderFragment, "0")
            add(R.id.fl_layout, grabOrderFragment, "1")
            commit()
        }
        selectPage(0)
    }

    /**
     * 2、实现此方法，来自定义titleItemView
     *
     * 不写就是方法内默认样式
     */
    override fun createTitleItemView(
        context: Context,
        magicIndicator: MagicIndicator,
        viewPager: ViewPager?,
        index: Int,
        titles: ArrayList<String>,
        tab: Int,
    ): IPagerTitleView = SimplePagerTitleView(context).apply {
        //setTextSize默认使用的是SP单位的，如果再进行转换将被二次转换，
//        textSize = AutoSizeUtils.mm2px(context, 14F).toFloat()
        setTextSizeAuto(14F)
        text = titles[index]
        normalColor = Color.parseColor("#999999")
        selectedColor = Color.parseColor("#333333")
        setOnClickListener {
            selectPage(index)
        }
    }

    fun selectPage(index: Int){
        mBinding.miTab.apply {
            onPageScrolled(index, 0f, 0)
            onPageSelected(index)
        }
        childFragmentManager.beginTransaction().apply {
            if (index == 0) {
                hide(grabOrderFragment)
                show(distributeOrderFragment)
            }else{
                hide(distributeOrderFragment)
                show(grabOrderFragment)
            }
            commit()
        }
    }

}