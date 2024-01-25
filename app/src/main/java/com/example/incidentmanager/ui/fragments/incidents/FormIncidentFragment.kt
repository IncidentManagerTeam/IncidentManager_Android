package com.example.incidentmanager.ui.fragments
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.findNavController
import com.example.incidentmanager.R
import com.example.incidentmanager.data.db.repositories.models.Incidencia

import kotlinx.coroutines.launch

class FormIncidentFragment : Fragment() {
    lateinit var binding: FragmentFormIncidentBinding
    val viewModel: IncidentFormViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFormIncidentBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val idNav = 1;
        binding.buttomCreateIncident.setOnClickListener {
            var titulo = binding.title.text.toString();
            var descripcion = binding.description.text.toString();
            var ubicacion = binding.ubication.text.toString();
            var imagen = binding.photoPreview.toString();
            if (titulo.isNotBlank() && descripcion.isNotBlank() && ubicacion.isNotBlank()) {
                viewModel.viewModelScope.launch {
                    viewModel.insertIncident(
                        Incidencia(
                            idNav,titulo,descripcion,imagen,ubicacion
                        )
                    )
                }
            } else {
                val toastMessage = "Rellene todos los datos"
                Toast.makeText(requireContext(), toastMessage, Toast.LENGTH_SHORT).show()
            }
        }
    }

}
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.findNavController
import com.example.incidentmanager.R
import com.example.incidentmanager.databinding.FragmentFormIncidentBinding
import com.example.incidentmanager.ui.viewmodels.IncidentFormViewModel
import kotlinx.coroutines.launch

class FormIncidentFragment : Fragment() {
    lateinit var binding: FragmentFormIncidentBinding
    val viewModel: IncidentFormViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFormIncidentBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.buttomCreateIncident.setOnClickListener {
            var titulo = binding.title.text;
            var descripcion = binding.description.text;
            var ubicacion = binding.ubication.text;
            if (titulo.isNotBlank() && descripcion.isNotBlank() && ubicacion.isNotBlank()) {
                viewModel.viewModelScope.launch {
                    viewModel.insertIncident(
                        Incidencia(
                            titulo,descripcion,ubicacion
                        )
                    )
                }
            } else {
                val toastMessage = "Rellene todos los datos"
                Toast.makeText(requireContext(), toastMessage, Toast.LENGTH_SHORT).show()
            }
        }
    }

}