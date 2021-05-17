package com.longpc.interView

import android.app.IntentService
import android.os.HandlerThread
import com.longpc.interView.databinding.InterviewActivityMainBinding
import com.safmvvm.mvvm.view.BaseActivity

/**
 * 面试相关
 */
class InterViewIndexActivity: BaseActivity<InterviewActivityMainBinding, InterViewIndexViewModel>(
        R.layout.interview_activity_main,
        BR.viewModel
) {

        init{
        }
}