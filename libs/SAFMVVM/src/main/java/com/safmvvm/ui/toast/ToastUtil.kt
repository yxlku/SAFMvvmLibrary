package com.safmvvm.ui.toast

import android.R
import android.annotation.SuppressLint
import android.graphics.drawable.Drawable
import android.view.Gravity
import android.view.LayoutInflater
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.DrawableRes
import androidx.annotation.IdRes
import androidx.annotation.LayoutRes
import androidx.annotation.StringRes
import com.safmvvm.app.BaseApp
import com.safmvvm.app.globalconfig.GlobalConfig
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


/**
 * 吐司提示类，可设置位置，偏移量，默认是系统自带的位置和偏移量。
 * 可设置自定义布局和消息id。
 */
object ToastUtil {

    private var mCustomLayout = -1
    private var mCustomMsgId = -1
    private var mGravity = 0
    private var xOffset = 0
    private var yOffset = 0

    init {
        GlobalScope.launch(Dispatchers.Main) {
            @SuppressLint("ShowToast")
            val toast = Toast.makeText(BaseApp.getInstance(), "", Toast.LENGTH_SHORT)
            mGravity = GlobalConfig.Toast.gCustomToastGravity
            xOffset = toast.xOffset
            yOffset = toast.yOffset
        }
    }

    /**
     * 系统自带的短消息提示
     *
     * @param stringResID 消息内容
     */
    fun showShortToast(
        @StringRes
        stringResID: Int,
        isGlobalCustom: Boolean = true,
    ) {
        showToast(
            stringResID,
            Toast.LENGTH_SHORT,
            isGlobalCustom
        )
    }

    /**
     * 系统自带的短消息提示
     *
     * @param msg 消息内容
     */
    fun showShortToast(msg: String?) {
        showToast(msg, Toast.LENGTH_SHORT)
    }

    /**
     * 系统自带的长消息提示
     *
     * @param stringResID 消息内容
     */
    fun showLongToast(
        @StringRes
        stringResID: Int,
        isGlobalCustom: Boolean = true,
    ) {
        showToast(
            stringResID,
            Toast.LENGTH_LONG,
            isGlobalCustom
        )
    }

    /**
     * 系统自带的长消息提示
     *
     * @param msg 消息内容
     */
    fun showLongToast(
        msg: String,
        isGlobalCustom: Boolean = true,
    ) {
        showToast(msg, Toast.LENGTH_LONG, isGlobalCustom)
    }

    private fun showToast(
        @StringRes
        stringResID: Int,
        duration: Int,
        isGlobalCustom: Boolean = true,
    ) {
        showToast(
            BaseApp.getInstance().getString(stringResID), duration, isGlobalCustom
        )
    }
    fun setDrawableImg(tv: TextView, @DrawableRes drawableId: Int){
        val drawable: Drawable = BaseApp.getInstance().resources.getDrawable(drawableId)
        drawable.setBounds(0, 0, drawable.minimumWidth, drawable.minimumHeight) //设置边界
        tv.setCompoundDrawables(drawable, null, null, null) //画在右边

    }
    private fun showToast(msg: String?, duration: Int, isGlobalCustom: Boolean = true) {
        GlobalScope.launch(Dispatchers.Main) {
            if (GlobalConfig.Toast.gCustomLayoutId != 0 && isGlobalCustom) {
                val toast = Toast(BaseApp.getInstance())
                if (GlobalConfig.Toast.gCustomMsgId == 0) {
                    throw RuntimeException("自定义Toast样式了，文字赋值到哪里呢？请设置CustomMsgId!")
                }
                var customView = LayoutInflater.from(BaseApp.getInstance()).inflate(GlobalConfig.Toast.gCustomLayoutId, null)
                var tvMsg = customView.findViewById<TextView>(GlobalConfig.Toast.gCustomMsgId)
                tvMsg?.let {
                    tvMsg.text = msg
                    if (GlobalConfig.Toast.gCustomIconId != 0) {
                        setDrawableImg(tvMsg, GlobalConfig.Toast.gCustomIconId)
                    }
                }
                //自定义样式
                toast.view = customView
                //显示时间
                toast.duration = duration
                //显示位置
                toast.setGravity(
                    GlobalConfig.Toast.gCustomToastGravity,
                    0,
                    0
                )
                toast.show()
            }else{
                //系统Toast
                val toast = Toast.makeText(BaseApp.getInstance(), msg, duration)
                //位置
                toast.setGravity(
                    mGravity,
                    xOffset,
                    yOffset
                )
                toast.show()
            }
        }
    }

    fun setCustomLayout(mCustomLayout: Int) {
        ToastUtil.mCustomLayout = mCustomLayout
    }

    fun setCustomMsgId(mCustomMsgId: Int) {
        ToastUtil.mCustomMsgId = mCustomMsgId
    }

    fun setGravity(mGravity: Int) {
        ToastUtil.mGravity = mGravity
    }

    fun setYOffset(yOffset: Int) {
        ToastUtil.yOffset = yOffset
    }

    fun setXOffset(xOffset: Int) {
        ToastUtil.xOffset = xOffset
    }

    fun showCustomLongToast(msg: String) {
        showCustomToast(msg, Toast.LENGTH_LONG)
    }


    fun showCustomToast(msg: String, length: Int) {
        if (mCustomLayout == -1 || mCustomMsgId == -1) {
            throw RuntimeException("必须初始化mCustomLayout和mCustomMsgId")
        }
        showCustomToast(
            msg,
            mCustomLayout,
            mCustomMsgId,
            length,
            mGravity,
            xOffset,
            yOffset
        )
    }

    fun showCustomShortToast(msgRes: Int) {
        showCustomToast(
            BaseApp.getInstance().getString(msgRes), Toast.LENGTH_SHORT
        )
    }

    fun showCustomShortToast(msg: String) {
        showCustomToast(msg, Toast.LENGTH_SHORT)
    }

    fun showCustomLongToast(
        @LayoutRes
        layout: Int,
    ) {
        showCustomToast(
            null,
            layout,
            0,
            Toast.LENGTH_LONG,
            Gravity.CENTER,
            0,
            0
        )
    }

    fun showCustomLongToast(
        msg: String,
        @LayoutRes
        layout: Int,
        @IdRes
        msgId: Int,
    ) {
        showCustomToast(
            msg,
            layout,
            msgId,
            Toast.LENGTH_LONG,
            Gravity.CENTER,
            0,
            0
        )
    }

    fun showCustomToast(
        msg: String?,
        @LayoutRes
        layout: Int,
        @IdRes
        msgId: Int,
        duration: Int, gravity: Int, xOffset: Int,
        yOffset: Int,
    ) {
        GlobalScope.launch(Dispatchers.Main) {
            val toast = Toast(BaseApp.getInstance())
            val view = LayoutInflater.from(BaseApp.getInstance()).inflate(layout, null)
            if (msgId != 0) {
                val tv = view.findViewById<TextView>(msgId)
                tv.text = msg
            }
            toast.view = view
            toast.duration = duration
            toast.setGravity(gravity, xOffset, yOffset)
            toast.show()
        }
    }
}
