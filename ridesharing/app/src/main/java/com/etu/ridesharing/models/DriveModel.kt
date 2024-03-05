package com.etu.ridesharing.models

import androidx.lifecycle.ViewModel
import com.etu.ridesharing.data.DriveState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class DriveModel(driveState: DriveState = DriveState()) : ViewModel() {
    private val _uiState = MutableStateFlow(driveState)
    val uiState: StateFlow<DriveState> = _uiState.asStateFlow()
}