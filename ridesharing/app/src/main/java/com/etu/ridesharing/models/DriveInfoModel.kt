package com.etu.ridesharing.models

import androidx.lifecycle.ViewModel
import com.etu.ridesharing.data.DriveInfoState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class DriveInfoModel(driveInfoState: DriveInfoState = DriveInfoState()) : ViewModel() {
    private val _uiState = MutableStateFlow(driveInfoState)
    val uiState: StateFlow<DriveInfoState> = _uiState.asStateFlow()

    fun updateDriveInfo(driveInfo: DriveInfoState) {
        _uiState.update { currentState ->
            currentState.copy(
                driveDate = driveInfo.driveDate,
                driveTime = driveInfo.driveTime,
                from = driveInfo.from,
                to = driveInfo.to,
                price = driveInfo.price
            )
        }
    }
}