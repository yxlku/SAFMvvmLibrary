package com.deti.basis.personal.subaccount

import androidx.recyclerview.widget.LinearLayoutManager
import com.chad.library.adapter.base.BaseBinderAdapter
import com.deti.basis.R
import com.deti.basis.BR
import com.deti.basis.databinding.BasisFragmentAddSubAccountBinding
import com.deti.basis.personal.adapter.ItemFormPersonalEntity
import com.deti.basis.personal.adapter.ItemFormPersonalPerfect
import com.deti.basis.personal.adapter.ItemTitlePersonalEntity
import com.deti.basis.personal.adapter.ItemTitlePersonalPerfect
import com.safmvvm.mvvm.view.BaseFragment
import com.test.common.ui.item.line.ItemGrayLine
import com.test.common.ui.item.line.ItemGrayLineEntity
import com.test.common.ui.item.line.ItemTransparentLine
import com.test.common.ui.item.line.ItemTransparentLineEntity

class AddSubAccountFragment: BaseFragment<BasisFragmentAddSubAccountBinding, AddSubAccountViewModel>(
    R.layout.basis_fragment_add_sub_account,
    BR.viewModel
){
    companion object{
        /** 添加子账号标题*/
        const val TITLE_ADD_SUB = 0
        /** 子账号用户名*/
        const val ITEM_ADD_SUB_USERNAME = 1
        /** 子账号手机号*/
        const val ITEM_ADD_SUB_PHONE = 2
        /** 初始密码*/
        const val ITEM_ADD_SUB_PWD = 3

    }

    var mAdapter = BaseBinderAdapter()

    override fun initData() {
        super.initData()
        mAdapter
            //表单
            .addItemBinder(ItemFormPersonalEntity::class.java, ItemFormPersonalPerfect())
            //标题
            .addItemBinder(ItemTitlePersonalEntity::class.java, ItemTitlePersonalPerfect())
            //分割线
            .addItemBinder(ItemGrayLineEntity::class.java, ItemGrayLine())
            .addItemBinder(ItemTransparentLineEntity::class.java, ItemTransparentLine())
        mBinding.rvContent.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = mAdapter
        }
        var listData = arrayListOf(
            ItemTitlePersonalEntity(TITLE_ADD_SUB, "添加子账号"),
            ItemFormPersonalEntity(ITEM_ADD_SUB_USERNAME, "子账号用户名", "","请输入子账号用户名"),
            ItemGrayLineEntity(context),
            ItemFormPersonalEntity(ITEM_ADD_SUB_PHONE, "子账号手机号", "","请输入手机号码"),
            ItemGrayLineEntity(context),
            ItemFormPersonalEntity(ITEM_ADD_SUB_PWD, "初始密码", "123456","请填写初始密码"),
            ItemTransparentLineEntity(context, 30F),

       )
        mAdapter.setList(listData)
    }

}