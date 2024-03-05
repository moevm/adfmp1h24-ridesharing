package com.etu.ridesharing.models

import androidx.lifecycle.ViewModel
import com.etu.ridesharing.data.CarInfoState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class CarInfoModel(carInfoState: CarInfoState = CarInfoState()) : ViewModel() {
    private val _uiState = MutableStateFlow(carInfoState)
    val uiState: StateFlow<CarInfoState> = _uiState.asStateFlow()

    fun updateCarInfo(carInfo: CarInfoState) {
        _uiState.update { currentState ->
            currentState.copy(
                mark = carInfo.mark,
                color = carInfo.color,
                number = carInfo.number
            )
        }
    }
}
