package com.deti.brand.demand.update

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import com.deti.brand.BR
import com.deti.brand.R
import com.deti.brand.databinding.BrandActivityUpdateDemandBinding
import com.deti.brand.demand.create.CreateDemandFragment
import com.safmvvm.mvvm.view.BaseActivity

/**
 * 直接打开一个Fragment
 */
class UpdateDemandActivity: BaseActivity<BrandActivityUpdateDemandBinding, UpdateDemandViewModel>(
    R.layout.brand_activity_update_demand,
    BR.viewModel
) {

    companion object{
        fun startAction(context: Activity?){
            context?.apply {
                var intent = Intent(this, UpdateDemandActivity::class.java)
                this.startActivity(intent)
            }
        }
    }

    override fun initData() {
        super.initData()
        supportFragmentManager
            .beginTransaction()
            .replace(mBinding.flLayout.id, CreateDemandFragment())
            .commitAllowingStateLoss()
    }


}