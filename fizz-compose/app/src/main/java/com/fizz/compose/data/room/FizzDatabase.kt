package com.fizz.compose.data.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.fizz.compose.data.room.dao.AccountDao
import com.fizz.compose.data.room.entity.AccountEntity
import com.fizz.compose.utilities.SingletonHolder

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

    companion object : SingletonHolder<FizzDatabase, Context>({
        Room.databaseBuilder(it.applicationContext, FizzDatabase::class.java, "data.db")
            .fallbackToDestructiveMigration()
            .build()
    })

    abstract fun accountDao(): AccountDao
}