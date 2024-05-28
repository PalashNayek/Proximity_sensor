package com.palash.proximity_sensor.repositories

import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import javax.inject.Inject

class SensorRepository @Inject constructor(private val sensorManager: SensorManager) {

    private val _proximityData = MutableLiveData<Float?>()
    val proximityData: LiveData<Float?> = _proximityData

    private val proximityListener = object : SensorEventListener {
        override fun onSensorChanged(event: SensorEvent?) {
            event?.let {
                if (it.sensor.type == Sensor.TYPE_PROXIMITY) {
                    _proximityData.postValue(it.values[0])
                }
            }
        }

        override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
            // No action needed
        }
    }

    fun startListening() {
        val sensor = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY)
        if (sensor != null) {
            sensorManager.registerListener(proximityListener, sensor, SensorManager.SENSOR_DELAY_NORMAL)
        } else {
            _proximityData.postValue(null)
        }
    }

    fun stopListening() {
        sensorManager.unregisterListener(proximityListener)
    }
}