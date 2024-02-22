package com.example.incidentmanager.ui.fragments.camera

import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.camera.core.CameraSelector
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.example.incidentmanager.databinding.FragmentCameraIncidentBinding


class CameraIncidentFragment : Fragment() {

    private lateinit var binding: FragmentCameraIncidentBinding
    private val sharedViewModel: CameraViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCameraIncidentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.imageView.visibility = View.GONE // Oculta el ImageView inicialmente

        sharedViewModel.imageCapture.observe(viewLifecycleOwner, Observer { imageData ->
            if (imageData != null) {
                binding.imageView.visibility = View.VISIBLE // Muestra el ImageView cuando hay una imagen
                val bitmap = BitmapFactory.decodeByteArray(imageData, 0, imageData.size)
                binding.imageView.setImageBitmap(bitmap)
            } else {
                binding.imageView.visibility = View.GONE // Oculta el ImageView si la imagen es nula
            }
        })
    }



}