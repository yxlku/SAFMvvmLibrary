package com.test.common.ui.popup.type

import android.app.Activity
import android.view.View
import com.lxj.xpopup.core.BottomPopupView
import com.safmvvm.ext.ui.typesview.OnClickResultListener
import com.safmvvm.ext.ui.typesview.TypesThreeView
import com.safmvvm.ext.ui.typesview.TypesTreeViewEntity
import com.safmvvm.ext.ui.typesview.TypesViewDataBean
import com.safmvvm.ui.titlebar.OnTitleBarListener
import com.safmvvm.ui.titlebar.TitleBar
import com.safmvvm.ui.toast.ToastUtil
import com.test.common.R
import kotlin.collections.ArrayList

class TypesPopupView(
    var mActivit: Activity,
    var mTitle: String = "",
    var datas: TypesTreeViewEntity,
    var levelCount: Int = 2,
    /** 选择的结果*/
    var selectResultBlock: (result: ArrayList<TypesViewDataBean?>) -> Unit = {}
) : BottomPopupView(mActivit){
    override fun getImplLayoutId(): Int = R.layout.base_dialog_types

    override fun onCreate() {
        super.onCreate()
        var tb_title: TitleBar = findViewById(R.id.tb_title)
        var ttv_types: TypesThreeView = findViewById(R.id.ttv_types)

        ttv_types.apply {
            setTypesDatas(datas, levelCount)
            onClickResultListener = object : OnClickResultListener{
                override fun onClickCompleteResult(resultData: ArrayList<TypesViewDataBean?>) {
                    //点击最后一条回调
                    selectResultBlock(resultData)
                }
            }
        }

        tb_title.title = mTitle
        tb_title.setOnTitleBarListener(object : OnTitleBarListener{
            override fun onLeftClick(v: View?) {
                dismiss()
            }

            override fun onTitleClick(v: View?) {
            }

            override fun onRightClick(v: View?) {
                var s = ""
                ttv_types.resultData.forEach {
                    it?.apply {
                        s+= text
                    }
                }

                ToastUtil.showShortToast("选中了:${s}")
            }

        })
    }



}