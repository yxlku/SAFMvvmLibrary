package com.deti.basis.personal.perfect

import androidx.recyclerview.widget.LinearLayoutManager
import com.chad.library.adapter.base.BaseBinderAdapter
import com.deti.basis.R
import com.deti.basis.BR
import com.deti.basis.databinding.BasisFragmentPerfectPersonalBinding
import com.deti.basis.personal.adapter.ItemFormPersonalPerfect
import com.deti.basis.personal.adapter.ItemFormPersonalEntity
import com.deti.basis.personal.adapter.ItemTitlePersonalEntity
import com.deti.basis.personal.adapter.ItemTitlePersonalPerfect
import com.safmvvm.mvvm.view.BaseFragment
import com.test.common.ui.item.line.ItemGrayLine
import com.test.common.ui.item.line.ItemGrayLineEntity
import com.test.common.ui.item.line.ItemTransparentLine
import com.test.common.ui.item.line.ItemTransparentLineEntity

class PerfectPersonalFragment: BaseFragment<BasisFragmentPerfectPersonalBinding, PerfectPersonalViewModel>(
    R.layout.basis_fragment_perfect_personal,
    BR.viewModel
){

    companion object{
        /** 个人*/
        const val TITLE_PERSONAL = 0
        /** 身份*/
        const val ITEM_PERSONAL_IDENTITY = 1
        /** 品牌名称*/
        const val ITEM_PERSONAL_BRAND_NAME = 2
        /** 手机号*/
        const val ITEM_PERSONAL_PHONE = 3
        /** 用户名*/
        const val ITEM_PERSONAL_USERNAME = 4
        /** 密码*/
        const val ITEM_PERSONAL_PWD = 5
        /** 确认密码*/
        const val ITEM_PERSONAL_PWD_RE = 6


        /** 企业*/
        const val TITLE_COMPANY = 7
        /** 公司 */
        const val ITEM_COMPANY = 8
        /** 法人*/
        const val ITEM_COMPANY_LEGAL_PERSON = 9
        /** 税号*/
        const val ITEM_COMPANY_TAX_NUMBER = 10
        /** 法人身份证*/
        const val ITEM_COMPANY_LEGAL_PERSON_ID = 11

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
            ItemTitlePersonalEntity(TITLE_PERSONAL, "个人信息"),
            ItemFormPersonalEntity(ITEM_PERSONAL_IDENTITY, "身份", "品牌商"),
            ItemGrayLineEntity(),
            ItemFormPersonalEntity(ITEM_PERSONAL_BRAND_NAME, "品牌名称", "","请填写品牌名称"),
            ItemGrayLineEntity(),
            ItemFormPersonalEntity(ITEM_PERSONAL_PHONE, "手机号", "","请填写手机号"),
            ItemGrayLineEntity(),
            ItemFormPersonalEntity(ITEM_PERSONAL_USERNAME, "用户名：", "","请输入用户名"),
            ItemGrayLineEntity(),
            ItemFormPersonalEntity(ITEM_PERSONAL_PWD, "密码：", "","请输入密码"),
            ItemGrayLineEntity(),
            ItemFormPersonalEntity(ITEM_PERSONAL_PWD_RE, "确认密码：", "","请输入密码"),

            //分割线
            ItemTransparentLineEntity(10F),
            ItemTitlePersonalEntity(TITLE_COMPANY, "企业信息"),
            ItemFormPersonalEntity(ITEM_COMPANY, "公司", "","请输入公司名"),
            ItemGrayLineEntity(),
            ItemFormPersonalEntity(ITEM_COMPANY_LEGAL_PERSON, "法人", "","请输入公司法人"),
            ItemGrayLineEntity(),
            ItemFormPersonalEntity(ITEM_COMPANY_TAX_NUMBER, "税号", "","请输入税号"),
            ItemGrayLineEntity(),
            ItemFormPersonalEntity(ITEM_COMPANY_LEGAL_PERSON_ID, "法人身份证：", "","请输入法人身份证"),

            ItemTransparentLineEntity(30F),
        )
        mAdapter.setList(listData)
    }
}