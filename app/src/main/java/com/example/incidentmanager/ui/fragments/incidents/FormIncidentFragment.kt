package com.example.incidentmanager.ui.fragments.incidents

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.findNavController
import com.example.incidentmanager.databinding.FragmentFormIncidentBinding
import com.example.incidentmanager.ui.viewmodels.IncidentFormViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class FormIncidentFragment : Fragment() {

    private lateinit var binding: FragmentFormIncidentBinding
    private val viewModel: IncidentFormViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFormIncidentBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.buttonCreateIncident.setOnClickListener {
            val title = binding.title.text.toString()
            val description = binding.description.text.toString()
            val location = binding.location.text.toString()
            val imageUri = binding.photoPreview.toString()

            if (title.isNotBlank() && description.isNotBlank() && location.isNotBlank() && imageUri.isNotBlank()) {
                viewModel.viewModelScope.launch {
                    try {
                        // Insertar el incidente en la base de datos o donde sea que estés almacenando los datos
                        //viewModel.insertIncident(title, description, location, imageUri)
                        // Navegar de vuelta al fragmento anterior después de la inserción exitosa
                        findNavController().navigateUp()
                    } catch (e: Exception) {
                        // Manejar cualquier excepción que pueda ocurrir durante la inserción
                        Toast.makeText(requireContext(), "Error al crear el incidente: ${e.message}", Toast.LENGTH_SHORT).show()
                    }
                }
            } else {
                // Mostrar un mensaje si no se completan todos los campos
                Toast.makeText(requireContext(), "Por favor, complete todos los campos", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
