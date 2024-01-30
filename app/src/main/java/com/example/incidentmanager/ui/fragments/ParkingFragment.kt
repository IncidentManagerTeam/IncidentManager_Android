package com.example.incidentmanager.ui.fragments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.example.incidentmanager.R
import com.example.incidentmanager.databinding.FragmentIncidentBinding
import com.example.incidentmanager.databinding.FragmentParkingBinding
import com.example.incidentmanager.ui.viewmodels.ParkingViewModel

class ParkingFragment : Fragment() {

    private lateinit var binding: FragmentParkingBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentParkingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.floatingActionButton.setOnClickListener {
            val action = ParkingFragmentDirections.actionParkingFragmentToParkingFormFragment()
            view.findNavController().navigate(action)
        }
    }
}