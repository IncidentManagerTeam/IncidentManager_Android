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
        // Inflate the layout for this fragment
        binding = FragmentIncidentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.floatingActionButton.setOnClickListener {
            Log.d("Navigation", "Floating button clicked")
            try {
                val action = IncidentFragmentDirections.actionIncidentFragmentToFormIncidentFragment()
                findNavController().navigate(action)
            } catch (e: Exception) {
                Log.e("Navigation", "Error during navigation: ${e.message}")
            }
        }
    }
}