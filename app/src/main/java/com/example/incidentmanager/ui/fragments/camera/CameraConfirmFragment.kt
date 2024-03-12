package com.example.incidentmanager.ui.fragments.camera

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.incidentmanager.databinding.FragmentCameraConfirmBinding
import com.example.incidentmanager.ui.fragments.incidents.IncidentFragmentDirections
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CameraConfirmFragment : Fragment() {
    private lateinit var binding: FragmentCameraConfirmBinding
    private val sharedViewModel: CameraViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCameraConfirmBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val imageUri = arguments?.getString("imageUri")
        if (!imageUri.isNullOrEmpty()) {
            // Convertir la URI a un objeto Uri
            val uri = Uri.parse(imageUri)

            // Obtener el bitmap de la URI
            val bitmap: Bitmap? = context?.contentResolver?.openInputStream(uri)?.use {
                BitmapFactory.decodeStream(it)
            }

            // Crear un ImageView programáticamente
            val imageView = ImageView(requireContext())

            // Establecer el bitmap en el ImageView
            bitmap?.let {
                imageView.setImageBitmap(it)
            }

            // Agregar el ImageView al contenedor del Fragmento
            (view as? ViewGroup)?.addView(imageView)
            binding.buttonPhotoConfirmed.setOnClickListener{
                val navigation = CameraConfirmFragmentDirections.actionCameraConfirmFragmentToFormIncidentFragment(uri.toString())
                findNavController().navigate(navigation);
            }
        }
    }
}