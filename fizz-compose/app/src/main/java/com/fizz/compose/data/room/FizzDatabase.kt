package com.fizz.compose.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.fizz.compose.data.room.dao.AccountDao
import com.fizz.compose.data.room.entity.AccountEntity

/**
 * The [RoomDatabase] we use in this app.
 */
@Database(
    entities = [
        AccountEntity::class
    ],
    version = 1,
    exportSchema = false
)
abstract class FizzDatabase : RoomDatabase() {

    // 不建议：UI控制层可以绕过ViewModel层，直接和仓库层进行通讯。
//    companion object : SingletonHolder<FizzDatabase, Context>({
//        Room.databaseBuilder(it.applicationContext, FizzDatabase::class.java, "data.db")
//            .fallbackToDestructiveMigration()
//            .build()
//    })

    abstract fun accountDao(): AccountDao
}