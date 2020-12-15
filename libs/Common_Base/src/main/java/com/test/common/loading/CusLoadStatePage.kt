package com.test.common.loading

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import com.safmvvm.ui.load.state.ILoadPageState
import com.test.common.R
import com.zy.multistatepage.MultiState
import com.zy.multistatepage.MultiStateContainer

class CusLoadStatePage: MultiState(), ILoadPageState {
    override fun enableReload(): Boolean = false

    override fun onCreateMultiStateView(
        context: Context,
        inflater: LayoutInflater,
        container: MultiStateContainer
    ): View {
        return inflater.inflate(R.layout.commonbase_load_state_page_loading, container, false)
    }

    override fun onMultiStateViewCreate(view: View) {
    }

    override fun setMsg(msg: String?) {
    }

    override fun setSubMsg(subMsg: String?) {
    }

    override fun setIcon(icon: Int) {
    }
}