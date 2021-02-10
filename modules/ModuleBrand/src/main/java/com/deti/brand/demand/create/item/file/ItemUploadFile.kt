package com.deti.brand.demand.create.item.file

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.chad.library.adapter.base.binder.QuickDataBindingItemBinder
import com.deti.brand.databinding.BrandItemUploadFileBinding
import com.deti.brand.demand.create.CreateDemandViewModel
import com.deti.brand.demand.create.item.file.ItemUploadFileEnum.FILE_FABRIC
import com.deti.brand.demand.create.item.file.ItemUploadFileEnum.FILE_PLATE
import com.safmvvm.ext.rvIsShow
import com.test.common.ext.chooseFile

/*



 */
/**
 * 上传文件
 */
class ItemUploadFile(
    var activity: AppCompatActivity?,
    var mViewModel: CreateDemandViewModel? = null
): QuickDataBindingItemBinder<ItemUploadFileEntity, BrandItemUploadFileBinding>() {

    override fun convert(
        holder: BinderDataBindingHolder<BrandItemUploadFileBinding>,
        data: ItemUploadFileEntity,
    ) {
        var binding = holder.dataBinding
        binding.apply {
            entity = data
            viewModel = mViewModel
            //是否显示当前item
            holder.itemView.rvIsShow(data.isShow)

            //上传跳转
            tvUploadBtn.setOnClickListener {
                chooseFile(activity){ filePath ->
                    data.filePath.set(filePath)
    //                    when (it.tag) {
    //                        //TODO 这里选择后应该是请求接口
    //                        FILE_FABRIC -> { //面料信息
    //                            data.mFilePathFabric.set(filePath)
    //                        }
    //                        FILE_PLATE -> { //制版文件
    //                            data.mFilePathPlate.set(filePath)
    //                        }
    //                    }
                }
            }
            executePendingBindings()
        }
    }

    override fun onCreateDataBinding(
        layoutInflater: LayoutInflater,
        parent: ViewGroup,
        viewType: Int,
    ): BrandItemUploadFileBinding = BrandItemUploadFileBinding.inflate(layoutInflater, parent, false)
}
