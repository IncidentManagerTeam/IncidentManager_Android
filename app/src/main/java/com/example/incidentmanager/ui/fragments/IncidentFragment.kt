package com.example.incidentmanager.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.example.incidentmanager.R
import com.example.incidentmanager.databinding.FragmentIncidentBinding


class IncidentFragment : Fragment() {

    private lateinit var binding: FragmentIncidentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentIncidentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.floatingActionButton.setOnClickListener {
            val action = IncidentFragmentDirections.actionIncidentFragment2ToFormIncidentFragment()
            view.findNavController().navigate(action)
        }
    }

}