package com.deti.designer.version.popup

import com.deti.designer.R
import com.deti.designer.BR
import com.deti.designer.databinding.DesignerPopupVersionListFilterBinding
import com.safmvvm.mvvm.view.bottom.BaseBottomFragment

/**
 * 版单筛选
 */
class VersionListFilterDialogFragment: BaseBottomFragment<DesignerPopupVersionListFilterBinding, VersionListFilterDialogViewModel>(
    R.layout.designer_popup_version_list_filter,
    BR.viewModel
) {
}