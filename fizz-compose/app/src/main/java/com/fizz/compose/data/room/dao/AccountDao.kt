package com.fizz.compose.data.room.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.fizz.compose.data.room.entity.AccountEntity

/**
 * 用户信息
 */
@Dao
interface AccountDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(accountEntity: AccountEntity)

    @Insert
    fun insertAll(vararg users: AccountEntity)

    @Query("SELECT * FROM account")
    fun subscribeAccount(): LiveData<List<AccountEntity>>

    @Delete
    fun delete(accountEntity: AccountEntity)

    @Query("DELETE FROM account")
    suspend fun deleteAll()

}