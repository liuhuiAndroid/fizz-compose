package com.fizz.compose.viewmodels

import androidx.lifecycle.ViewModel
import com.fizz.compose.data.RoomRepository
import com.fizz.compose.di.DefaultDispatcher
import kotlinx.coroutines.CoroutineDispatcher

class MainViewModel constructor(
    @DefaultDispatcher private val defaultDispatcher: CoroutineDispatcher,
    private val roomRepository: RoomRepository
) : ViewModel() {

}