package com.palash.proximity_sensor.view_models

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.palash.proximity_sensor.repositories.SensorRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SensorViewModel @Inject constructor(private val sensorRepository: SensorRepository) : ViewModel() {

    val proximityData: LiveData<Float?> = sensorRepository.proximityData

    fun startListening() {
        sensorRepository.startListening()
    }

    fun stopListening() {
        sensorRepository.stopListening()
    }
}