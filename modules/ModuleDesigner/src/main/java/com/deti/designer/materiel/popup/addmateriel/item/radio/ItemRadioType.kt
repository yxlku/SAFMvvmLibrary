package com.deti.designer.materiel.popup.addmateriel.item.radio

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.RadioGroup
import com.chad.library.adapter.base.binder.QuickDataBindingItemBinder
import com.deti.designer.R
import com.deti.designer.databinding.DesignerItemRadioTypeBinding
import com.deti.designer.materiel.MaterielListViewModel
import com.deti.designer.materiel.popup.addmateriel.PopupAddMaterielViewModel

/**
 * 类型选择
 */
class ItemRadioType(
    var mViewModel: PopupAddMaterielViewModel,
) : QuickDataBindingItemBinder<ItemRadioTypeEntity, DesignerItemRadioTypeBinding>() {

    override fun convert(
        holder: BinderDataBindingHolder<DesignerItemRadioTypeBinding>,
        data: ItemRadioTypeEntity,
    ) {
        var binding = holder.dataBinding
        binding.apply {
            viewModel = mViewModel
            rbType.setOnCheckedChangeListener(object : RadioGroup.OnCheckedChangeListener{
                override fun onCheckedChanged(group: RadioGroup?, checkedId: Int) {
                    when (checkedId) {
                        R.id.rb_z -> {
                            //主料
                        }
                        R.id.rb_f -> {
                            //辅料
                        }
                        R.id.rb_l -> {
                            //里布
                        }
                    }
                }
            })
            executePendingBindings()
        }
    }

    override fun onCreateDataBinding(
        layoutInflater: LayoutInflater,
        parent: ViewGroup,
        viewType: Int,
    ): DesignerItemRadioTypeBinding =
        DesignerItemRadioTypeBinding.inflate(layoutInflater, parent, false)


}