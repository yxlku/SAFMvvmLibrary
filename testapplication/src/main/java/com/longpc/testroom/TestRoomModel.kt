package com.longpc.testroom

import androidx.room.Room
import com.longpc.testapplication.base.AppApplication
import com.longpc.testroom.db.DataBase
import com.longpc.testroom.db.TableEntity
import com.safmvvm.app.BaseApp
import com.safmvvm.db.RoomUtil
import com.safmvvm.mvvm.model.BaseModel
import com.safmvvm.utils.coroutines.flowOnIO
import kotlinx.coroutines.flow.Flow

class TestRoomModel: BaseModel() {

    //数据源
    var db = generateDBDataSource(DataBase::class.java)
    /**
     * 插入 使用Flow，可以在VM中进行插值监听
     */
    suspend fun insertDataFlow(): Flow<Unit?>{
        return flowOnIO {
            var te = TableEntity(name = "longPc", age = 18, sex = "男", other = "??")
            return@flowOnIO db.tabEntityDao().insertAll(te)
        }
    }

    /**
     * 插入
     */
    suspend fun insertData(){
         flowOnIO {
            var te = TableEntity(name = "longPc", age = 18, sex = "男", other = "??")
            db.tabEntityDao().insertAll(te)
            return@flowOnIO
        }
    }

    /**
     * 获取所有数据
     */
    fun getAllData(): Flow<List<TableEntity>?> {
        return flowOnIO {
            return@flowOnIO db.tabEntityDao().getAll()
        }
    }


}