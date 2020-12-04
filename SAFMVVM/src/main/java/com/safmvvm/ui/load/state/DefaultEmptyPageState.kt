package com.safmvvm.ui.load.state
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.DrawableRes
import com.zy.multistatepage.MultiState
import com.zy.multistatepage.MultiStateContainer
import com.zy.multistatepage.MultiStatePage
import com.zy.multistatepage.R

class DefaultEmptyPageState : MultiState(), ILoadPageState {

    private lateinit var tvEmptyMsg: TextView
    private lateinit var imgEmpty: ImageView

    override fun onCreateMultiStateView(
        context: Context,
        inflater: LayoutInflater,
        container: MultiStateContainer
    ): View {
        return inflater.inflate(R.layout.mult_state_empty, container, false)
    }

    override fun onMultiStateViewCreate(view: View) {
        tvEmptyMsg = view.findViewById(R.id.tv_empty_msg)
        imgEmpty = view.findViewById(R.id.img_empty)

        setMsg(MultiStatePage.config.emptyMsg)
        setIcon(MultiStatePage.config.emptyIcon)
    }

    override fun enableReload(): Boolean = false

    override fun setMsg(msg: String?) {
        msg?.let {
            tvEmptyMsg.text = msg
        }
    }

    override fun setSubMsg(subMsg: String?) {
    }

    override fun setIcon(@DrawableRes icon: Int) {
        if (icon != 0) {
            imgEmpty.setImageResource(icon)
        }
    }
}