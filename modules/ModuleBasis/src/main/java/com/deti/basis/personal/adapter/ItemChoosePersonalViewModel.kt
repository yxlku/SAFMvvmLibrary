package com.deti.basis.personal.adapter

import android.app.Activity
import android.view.View
import com.chad.library.adapter.base.BaseBinderAdapter
import com.deti.basis.personal.address.AddAddressFragment
import com.lxj.xpopup.XPopup
import com.lxj.xpopupext.listener.CityPickerListener
import com.lxj.xpopupext.popup.CityPickerPopup
import com.safmvvm.ui.toast.ToastUtil

class ItemChoosePersonalViewModel(
    var mActivity: Activity?,
    var mAdapter: BaseBinderAdapter,
    var position: Int
) {

    fun clickItem(view: View, entity: ItemChoosePersonalEntity){
        //判断选择以下方法
        when (entity.tagId) {
            //选择城市
            AddAddressFragment.ITEM_ADD_ADDRESS_CITY -> clickChooseCity(view, entity)
            //发货方式
            AddAddressFragment.ITEM_ADD_ADDRESS_SHIP -> clickChooseShip(view, entity)
        }
    }

    fun clickChooseCity(view: View, entity: ItemChoosePersonalEntity){
        mActivity?.apply {
            var popup = CityPickerPopup(this).setCityPickerListener(object : CityPickerListener{
                override fun onCityConfirm(
                    province: String?,
                    city: String?,
                    area: String?,
                    v: View?,
                ) {
                    entity.content = "$province $city $area"
                    mAdapter.notifyItemChanged(position)
//                    mAdapter.notifyDataSetChanged()
                }

                override fun onCityChange(province: String?, city: String?, area: String?) {
                }
            })
            XPopup.Builder(this)
                .isThreeDrag(false)
                .asCustom(popup)
                .show()
        }
    }

    fun clickChooseShip(view: View, entity: ItemChoosePersonalEntity){
        ToastUtil.showShortToast("发货方式")
    }

}