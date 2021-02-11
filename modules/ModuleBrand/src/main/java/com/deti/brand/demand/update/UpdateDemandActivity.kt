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
 * 修改需求订单
 */
class UpdateDemandActivity: BaseActivity<BrandActivityUpdateDemandBinding, UpdateDemandViewModel>(
    R.layout.brand_activity_update_demand,
    BR.viewModel
) {

    companion object{
        /**
         * @param pDemandId 要修改需求单的Id
         */
        fun startAction(context: Activity?, pDemandId: String){
            context?.apply {
                var intent = Intent(this, UpdateDemandActivity::class.java)
                intent.putExtra("pDemandId", pDemandId)
                this.startActivity(intent)
            }
        }
    }

    override fun initData() {
        super.initData()
        var pDemandId = intent.getStringExtra("pDemandId")
        supportFragmentManager
            .beginTransaction()
            .replace(mBinding.flLayout.id, CreateDemandFragment(pDemandId))
            .commitAllowingStateLoss()
    }


}