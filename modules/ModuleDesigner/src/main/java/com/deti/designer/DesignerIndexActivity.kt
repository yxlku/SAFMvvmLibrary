package com.deti.designer

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.deti.designer.databinding.DesignerActivityIndexBinding
import com.deti.designer.main.MainFragment
import com.deti.designer.mine.MineFragment
import com.deti.designer.msg.MsgFragment
import com.safmvvm.ext.ui.tab.ITabBottom
import com.safmvvm.ext.ui.tab.bottom.NewNormalItemView
import com.safmvvm.ext.ui.viewpager.createViewPager
import com.safmvvm.mvvm.view.BaseActivity
import me.majiajie.pagerbottomtabstrip.item.BaseTabItem

/**
 * 设计师主页
 */
class DesignerIndexActivity : BaseActivity<DesignerActivityIndexBinding, DesignerIndexViewModel>(
    R.layout.designer_activity_index,
    BR.viewModel
), ITabBottom{

    override fun initData() {
        //取消侧滑返回
        cleanSwipeback()

        initViewPager()
        initTab()
    }

    private fun initViewPager() {
        var fragments = arrayListOf<Fragment>().apply {
            add(MainFragment())
            add(MsgFragment())
            add(MineFragment())
        }
        fragments.createViewPager(supportFragmentManager, mBinding.vpContent)
    }

    private fun initTab() {
        var tabMain = newTabBottomItem(R.mipmap.base_index_tab_main_uncheck, R.mipmap.base_index_tab_main_checked, "首页")
        var tabMsg = newTabBottomItem(R.mipmap.base_index_tab_msg_uncheck, R.mipmap.base_index_tab_msg_checked, "消息")
        var tabMine = newTabBottomItem(R.mipmap.base_index_tab_mine_uncheck, R.mipmap.base_index_tab_mine_checked, "我的")
        //数字气泡
        tabMsg.tintMessageBackground(Color.parseColor("#FCCE48"))
        tabMsg.setMessageNumberColor(Color.parseColor("#333333"))
        var tabItems = arrayListOf<NewNormalItemView>().apply {
            add(tabMain)
            add(tabMsg)
            add(tabMine)
        }
        createBottomTab(mBinding.pnvTab, mBinding.vpContent,  tabItems)
        tabMsg.setMessageNumber(100)
    }

    override fun newTabBottomItem(
        uncheckIcon: Int,
        checkedIcon: Int,
        text: String,
    ): NewNormalItemView = NewNormalItemView(this).apply {
        initialize(uncheckIcon, checkedIcon, text)
        setTextDefaultColor(Color.parseColor("#999999"))
        setTextCheckedColor(Color.parseColor("#FCCE48"))
        setIconSize(24, 24)
        setTitleSize(12)
    }


}