package com.example.incidentmanager.ui.fragments.incidents

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.incidentmanager.databinding.FragmentIncidentBinding
import com.example.incidentmanager.ui.fragments.incidents.IncidentFragmentDirections

class IncidentFragment : Fragment() {

    private lateinit var binding: FragmentIncidentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflar el diseño del fragmento y vincular la vista
        binding = FragmentIncidentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Configurar el listener de clics para el botón flotante
        binding.floatingActionButton.setOnClickListener {
            // Navegar desde com.example.incidentmanager.ui.fragments.incidents.IncidentFragment a FormIncidentFragment
            try {
                val action = IncidentFragmentDirections.actionIncidentFragmentToFormIncidentFragment()
                findNavController().navigate(action)
            } catch (e: Exception) {
                // Capturar y mostrar cualquier excepción que ocurra durante la navegación
                Log.e("Navigation", "Error durante la navegación: ${e.message}")
            }
        }
    }
}
