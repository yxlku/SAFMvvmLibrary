package com.safmvvm.ui.load.state
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import com.zy.multistatepage.MultiState
import com.zy.multistatepage.MultiStateContainer
import com.zy.multistatepage.MultiStatePage
import com.zy.multistatepage.R

class DefaultLoadingPageState : MultiState(), ILoadPageState {
    lateinit var tvLoadingMsg: TextView
    override fun onCreateMultiStateView(
        context: Context,
        inflater: LayoutInflater,
        container: MultiStateContainer
    ): View {
        return inflater.inflate(R.layout.mult_state_loading, container, false)
    }

    override fun onMultiStateViewCreate(view: View) {
        tvLoadingMsg = view.findViewById(R.id.tv_loading_msg)
        setMsg(MultiStatePage.config.loadingMsg)
    }

    override fun enableReload(): Boolean = false

    override fun setMsg(msg: String?) {
        msg?.let {
            tvLoadingMsg.text = msg
        }
    }

    override fun setSubMsg(subMsg: String?) {
    }

    override fun setIcon(icon: Int) {
    }
}