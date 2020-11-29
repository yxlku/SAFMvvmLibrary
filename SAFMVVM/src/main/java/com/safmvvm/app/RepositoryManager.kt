package com.safmvvm.app

import androidx.collection.ArrayMap
import androidx.collection.arrayMapOf
import com.safmvvm.mvvm.model.BaseModel
import com.safmvvm.utils.ReflectUtil

/**
 * 仓库管理类
 */
object RepositoryManager {

    /**
     *
     *  仓库管理集合
     *
     *  使用子类泛型的对象赋值给使用父类泛型的对象，那么可以用 out
     *  父类泛型的对象赋值给使用子类泛型的对象，那么可以使用in
     *  https://www.jianshu.com/p/c5ef8b30d768
     */
    private lateinit var mRepoMap: ArrayMap<Class<out BaseModel>, BaseModel>

    /**
     * 获取仓库实例，[isCache] 默认是 true，即缓存仓库实例
     */
    fun <M: BaseModel> getRepo(clz: Class<out M>, isCache: Boolean = true): M{
        //不缓存直接创建
        if (!isCache) {
            return ReflectUtil.instance(clz)
        }
        //缓存，则保存到 map 中，下次复用
        if (!this::mRepoMap.isInitialized) {
            mRepoMap = arrayMapOf()
        }
        var repo = mRepoMap[clz]
        if(repo == null) {
//            repo = ReflectUtil.instance(clz)
            repo = clz.newInstance()
            mRepoMap[clz] = repo
        }
        return repo as M
    }
}