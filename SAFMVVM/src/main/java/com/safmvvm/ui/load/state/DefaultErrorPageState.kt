package com.safmvvm.ui.load.state
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.zy.multistatepage.MultiState
import com.zy.multistatepage.MultiStateContainer
import com.zy.multistatepage.MultiStatePage
import com.zy.multistatepage.R

/**
 * @ProjectName: MultiStatePage
 * @Author: 赵岩
 * @Email: 17635289240@163.com
 * @Description: TODO
 * @CreateDate: 2020/9/17 14:15
 */
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

        setMsg(MultiStatePage.config.errorMsg)
        setIcon(MultiStatePage.config.errorIcon)
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
            tvRetry.setText(subMsg)
        }
    }

    override fun setIcon(icon: Int) {
        if (icon != 0) {
            imgError.setImageResource(icon)
        }
    }
}
