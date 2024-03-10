package com.etu.ridesharing.models

import androidx.lifecycle.ViewModel
import com.etu.ridesharing.data.UserState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class UserModel (userState: UserState = UserState()) : ViewModel() {
    private val _uiState = MutableStateFlow(userState)
    val uiState: StateFlow<UserState> = _uiState.asStateFlow()
}