package com.deti.designer.msg

import com.deti.designer.R
import com.deti.designer.BR
import com.deti.designer.databinding.DesignerFragmentMsgBinding
import com.deti.designer.main.MainFragment
import com.safmvvm.mvvm.view.BaseLazyFragment
import com.safmvvm.utils.LogUtil

class MsgFragment: BaseLazyFragment<DesignerFragmentMsgBinding, MsgViewModel>(
    R.layout.designer_fragment_msg,
    BR.viewModel
) {

    override fun initData() {
        super.initData()
        LogUtil.d("初始化：msgFragment1")
    }

    override fun onFragmentFirstVisible() {
        super.onFragmentFirstVisible()

        LogUtil.d("初始化：msgFragment")

        mBinding.tv.setOnClickListener{
            activity?.apply {
//                createDialogBase(FragmentPopupView(activity as AppCompatActivity, R.layout.designer_test))
//                    .show()
            }

//            MainFragment().show(childFragmentManager, "1")
        }
    }
}