package com.fizz.compose.repository

import com.fizz.compose.data.room.dao.AccountDao
import com.fizz.compose.data.room.entity.AccountEntity
import com.fizz.compose.di.DefaultDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AccountRepository @Inject constructor(
    private val accountDao: AccountDao,
    @DefaultDispatcher private val defaultDispatcher: CoroutineDispatcher,
) {

    suspend fun insert(accountEntity: AccountEntity)  = withContext(defaultDispatcher) {
        accountDao.insert(accountEntity)
    }
}