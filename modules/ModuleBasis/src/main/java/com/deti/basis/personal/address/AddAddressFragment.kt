package com.deti.basis.personal.address

import androidx.recyclerview.widget.LinearLayoutManager
import com.chad.library.adapter.base.BaseBinderAdapter
import com.deti.basis.R
import com.deti.basis.BR
import com.deti.basis.databinding.BasisFragmentAddAddressBinding
import com.deti.basis.personal.adapter.*
import com.safmvvm.bus.LiveDataBus
import com.safmvvm.mvvm.view.BaseFragment
import com.safmvvm.utils.LogUtil
import com.test.common.ui.item.line.ItemGrayLine
import com.test.common.ui.item.line.ItemGrayLineEntity
import com.test.common.ui.item.line.ItemTransparentLine
import com.test.common.ui.item.line.ItemTransparentLineEntity

class AddAddressFragment: BaseFragment<BasisFragmentAddAddressBinding, AddAddressViewModel>(
    R.layout.basis_fragment_add_address,
    BR.viewModel
){
    companion object{
        /** 收货地址信息*/
        const val TITLE_ADD_ADDRESS = 0
        /** 联系人*/
        const val ITEM_ADD_ADDRESS_PERSON = 1
        /** 电话*/
        const val ITEM_ADD_ADDRESS_PHONE = 2
        /** 选择城市*/
        const val ITEM_ADD_ADDRESS_CITY = 3
        /** 输入城市*/
        const val ITEM_ADD_ADDRESS_CITY_FORM = 4
        /** 发货方式*/
        const val ITEM_ADD_ADDRESS_SHIP = 5
    }

    var mAdapter = BaseBinderAdapter()

    override fun initData() {
        super.initData()

        LiveDataBus.observe<Unit>(this, "submit", {
            //提交
           mAdapter.data.forEach {
               if (it is ItemChoosePersonalEntity) {
                   if (it.tagId == ITEM_ADD_ADDRESS_CITY) {
                       //选择区县
                       LogUtil.d("我提交了城市：${it.content}")
                   }else if (it.tagId == ITEM_ADD_ADDRESS_SHIP) {
                       LogUtil.d("我提交了发货方式：${it.content}")
                   }
               }else if(it is ItemFormPersonalEntity){
                   //需要在edittext增加监听才能获取到值
                   LogUtil.d("输入了啥？：${it.content}")
               }
           }
        }, false)

        mAdapter
            //表单
            .addItemBinder(ItemFormPersonalEntity::class.java, ItemFormPersonalPerfect())
            //标题
            .addItemBinder(ItemTitlePersonalEntity::class.java, ItemTitlePersonalPerfect())
            //选择
            .addItemBinder(ItemChoosePersonalEntity::class.java, ItemChoosePersonal(activity))
            //得体地址
            .addItemBinder(ItemPersonalDetiAddressEntity::class.java, ItemPersonalDetiAddress())
            //分割线
            .addItemBinder(ItemGrayLineEntity::class.java, ItemGrayLine())
            .addItemBinder(ItemTransparentLineEntity::class.java, ItemTransparentLine())
        mBinding.rvContent.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = mAdapter
        }
        //不要格式化
        var listData = arrayListOf(
            ItemTitlePersonalEntity(TITLE_ADD_ADDRESS, "收货地址信息"),
            ItemFormPersonalEntity(ITEM_ADD_ADDRESS_PERSON, "联系人", "","请输入子账号用户名"),
            ItemGrayLineEntity(context),
            ItemFormPersonalEntity(ITEM_ADD_ADDRESS_PHONE, "电话", "","请输入手机号码"),
            ItemGrayLineEntity(context),
            ItemChoosePersonalEntity(ITEM_ADD_ADDRESS_CITY, "选择 省/市/区", "","请选择 省/市/区"),
            ItemGrayLineEntity(context),
            ItemFormPersonalEntity(ITEM_ADD_ADDRESS_CITY_FORM, "地址详情", "","请输入地址详情"),
            ItemGrayLineEntity(context),
            ItemChoosePersonalEntity(ITEM_ADD_ADDRESS_SHIP, "发货方式", "","请选择发货方式"),
            ItemTransparentLineEntity(context, 10F),

            //得体地址
            ItemPersonalDetiAddressEntity("浙江省杭州市余杭区临平余杭商会大厦C座"),

        )
        mAdapter.setList(listData)
    }

}