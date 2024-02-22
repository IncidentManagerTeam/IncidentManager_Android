package com.example.incidentmanager.ui.fragments.camera

import androidx.camera.core.ImageCapture
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CameraViewModel:ViewModel() {
    val imageCapture = MutableLiveData<ByteArray>()

}