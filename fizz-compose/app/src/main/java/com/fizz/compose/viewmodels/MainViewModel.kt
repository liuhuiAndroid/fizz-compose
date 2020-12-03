package com.fizz.compose.viewmodels

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fizz.compose.data.room.entity.AccountEntity
import com.fizz.compose.repository.AccountRepository
import kotlinx.coroutines.launch

class MainViewModel @ViewModelInject constructor(
    private val accountRepository: AccountRepository
) : ViewModel() {

    fun insert() {
        viewModelScope.launch {
            accountRepository.insert(AccountEntity("1", ""))
        }
    }
}
