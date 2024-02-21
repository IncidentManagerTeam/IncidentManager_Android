package com.example.democamara

import androidx.camera.core.ImageCapture
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CameraViewModel:ViewModel() {
    val imageCapture = MutableLiveData<ByteArray>()

}