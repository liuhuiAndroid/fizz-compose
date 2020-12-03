package com.fizz.compose.viewmodels

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fizz.compose.data.*
import com.fizz.compose.data.room.entity.AccountEntity
import com.fizz.compose.repository.AccountRepository
import com.fizz.compose.repository.ApiRepository
import kotlinx.coroutines.launch

class MainViewModel @ViewModelInject constructor(
    private val accountRepository: AccountRepository,
    private val apiRepository: ApiRepository,
) : ViewModel() {

    private val _suggestedDestinations = MutableLiveData<List<MessageModel>>()
    val suggestedDestinations: LiveData<List<MessageModel>>
        get() = _suggestedDestinations

    init {
        _suggestedDestinations.value = craneDestinations
    }

    fun insert() {
        viewModelScope.launch {
            accountRepository.insert(AccountEntity("1", ""))
        }
    }
}


private const val DEFAULT_IMAGE_WIDTH = "250"

val craneDestinations = listOf(
    MessageModel(
        city = KHUMBUVALLEY,
        description = "Nonstop - 5h 16m+",
        imageUrl = "https://images.unsplash.com/photo-1544735716-392fe2489ffa?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=$DEFAULT_IMAGE_WIDTH"
    ),
    MessageModel(
        city = MADRID,
        description = "Nonstop - 2h 12m+",
        imageUrl = "https://images.unsplash.com/photo-1539037116277-4db20889f2d4?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=$DEFAULT_IMAGE_WIDTH"
    ),
    MessageModel(
        city = BALI,
        description = "Nonstop - 6h 20m+",
        imageUrl = "https://images.unsplash.com/photo-1518548419970-58e3b4079ab2?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=$DEFAULT_IMAGE_WIDTH"
    ),
    MessageModel(
        city = ROME,
        description = "Nonstop - 2h 38m+",
        imageUrl = "https://images.unsplash.com/photo-1515542622106-78bda8ba0e5b?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=$DEFAULT_IMAGE_WIDTH"
    ),
    MessageModel(
        city = GRANADA,
        description = "Nonstop - 2h 12m+",
        imageUrl = "https://images.unsplash.com/photo-1534423839368-1796a4dd1845?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=$DEFAULT_IMAGE_WIDTH"
    ),
    MessageModel(
        city = MALDIVAS,
        description = "Nonstop - 9h 24m+",
        imageUrl = "https://images.unsplash.com/photo-1544550581-5f7ceaf7f992?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=$DEFAULT_IMAGE_WIDTH"
    ),
    MessageModel(
        city = WASHINGTONDC,
        description = "Nonstop - 7h 30m+",
        imageUrl = "https://images.unsplash.com/photo-1557160854-e1e89fdd3286?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=$DEFAULT_IMAGE_WIDTH"
    ),
    MessageModel(
        city = BARCELONA,
        description = "Nonstop - 2h 12m+",
        imageUrl = "https://images.unsplash.com/photo-1562883676-8c7feb83f09b?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=$DEFAULT_IMAGE_WIDTH"
    ),
    MessageModel(
        city = CRETE,
        description = "Nonstop - 1h 50m+",
        imageUrl = "https://images.unsplash.com/photo-1486575008575-27670acb58db?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=$DEFAULT_IMAGE_WIDTH"
    )
)