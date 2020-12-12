package com.safmvvm.ui.load.state
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.safmvvm.app.globalconfig.GlobalConfig
import com.zy.multistatepage.MultiState
import com.zy.multistatepage.MultiStateContainer
import com.zy.multistatepage.R

class DefaultErrorPageState : MultiState(), ILoadPageState {

    private lateinit var tvErrorMsg: TextView
    private lateinit var imgError: ImageView
    private lateinit var tvRetry: TextView

    override fun onCreateMultiStateView(
        context: Context,
        inflater: LayoutInflater,
        container: MultiStateContainer
    ): View {
        return inflater.inflate(R.layout.mult_state_error, container, false)
    }

    override fun onMultiStateViewCreate(view: View) {
        tvErrorMsg = view.findViewById(R.id.tv_error_msg)
        imgError = view.findViewById(R.id.img_error)
        tvRetry = view.findViewById(R.id.tv_retry)

        setMsg(GlobalConfig.Loading.LOADING_ERROR_MSG)
        setIcon(GlobalConfig.Loading.LOADING_ERROR_ICON)
        setSubMsg(null)
    }

    override fun enableReload(): Boolean = true

    override fun bindRetryView(): View? {
        return tvRetry
    }

    override fun setMsg(msg: String?) {
        msg?.let {
            tvErrorMsg.text = msg
        }
    }

    override fun setSubMsg(subMsg: String?) {
        subMsg?.let {
            tvRetry.text = subMsg
        }
    }

    override fun setIcon(icon: Int) {
        if (icon != 0) {
            imgError.setImageResource(icon)
        }
    }
}
