package com.catching.pucks.database.ViewModel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import com.catching.pucks.database.App
import com.catching.pucks.database.DataBase.AppDatabase
import com.catching.pucks.database.DataBase.User
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch

class UserViewModel(private val database: AppDatabase) : ViewModel() {
    init {
        viewModelScope.launch {
            database.dao.insert(User(id = 1, name = "IPhone 13", time = "01.10.2021", tags = "Телефон", tags2 = "Новый", tags3 = "Распродажа", amount = 15))
            database.dao.insert(User(id = 2, name = "Samsung Galaxy S21", time = "02.10.2021", tags = "Телефон", tags2 = "Хит", tags3 = "Распродажа", amount = 30))
            database.dao.insert(User(id = 3, name = "PlayStation 5", time = "03.10.2021", tags = "Приставка", tags2 = "Акция", tags3 = "Распродажа", amount = 7))
            database.dao.insert(User(id = 4, name = "MacBook Air M1", time = "07.10.2021", tags = "Ноутбук", tags2 = "Акция", tags3 = "Тренд", amount = 12))
            database.dao.insert(User(id = 5, name = "Amazon Kindle Paperwhite", time = "08.10.2021", tags = "Книга", tags2 = "Возможность", tags3 = "Временный", amount = 18))
        }
    }

    val list = database.dao.getAllUsers()

    private val _searchText = MutableStateFlow("")
    val searchText = _searchText.asStateFlow()

    fun onSearchTextChange(text: String) {
        _searchText.value = text
    }

    companion object {
        val factiry: ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(
                modelClass: Class<T>,
                extras: CreationExtras
            ): T {
                val database = (checkNotNull(extras[APPLICATION_KEY]) as App).database
                return UserViewModel(database) as T
            }
        }
    }

    fun insertUser(user: User) {
        viewModelScope.launch {
            database.dao.insert(user)
        }
    }

    fun updateUser(user: User) {
        viewModelScope.launch {
            database.dao.update(user)
        }
    }

    fun deleteUser(user: User) {
        viewModelScope.launch {
            database.dao.delete(user)
        }
    }

}
