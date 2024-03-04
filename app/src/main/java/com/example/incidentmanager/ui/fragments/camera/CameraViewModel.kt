package com.example.incidentmanager.ui.fragments.camera

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CameraViewModel:ViewModel() {
    val imageCapture = MutableLiveData<ByteArray>()

}