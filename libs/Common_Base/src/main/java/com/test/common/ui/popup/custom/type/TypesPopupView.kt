package com.test.common.ui.popup.custom.type

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

/**
 * 类型
 */
class TypesPopupView(
    var mActivit: Activity,
    var mTitle: String = "",
    var datas: TypesTreeViewEntity,
    var defSelectedCodes: ArrayList<TypesViewDataBean?> = arrayListOf(),
    var levelCount: Int = 2,
    var autoDismiss: Boolean = true,
    /** 选择的结果*/
    var selectResultBlock: (result: ArrayList<TypesViewDataBean?>, resultTextList: ArrayList<String>) -> Unit = {result: ArrayList<TypesViewDataBean?>, resultTextList: ArrayList<String>->}
) : BottomPopupView(mActivit){
    override fun getImplLayoutId(): Int = R.layout.base_dialog_types

    override fun onCreate() {
        super.onCreate()
        var tb_title: TitleBar = findViewById(R.id.tb_title)
        var ttv_types: TypesThreeView = findViewById(R.id.ttv_types)

        ttv_types.apply {
            setTypesDatas(datas, levelCount, defSelectedCodes = defSelectedCodes)
            onClickResultListener = object : OnClickResultListener{
                override fun onClickCompleteResult(resultData: ArrayList<TypesViewDataBean?>) {
                    //点击最后一条回调
                    var sbList = ArrayList<String>()
                    resultData.forEach {
                        it?.apply {
                            sbList.add(it.text)
                        }
                    }
                    selectResultBlock(resultData, sbList)
                    if(autoDismiss){
                        dismiss()
                    }
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

            }

        })
    }



}