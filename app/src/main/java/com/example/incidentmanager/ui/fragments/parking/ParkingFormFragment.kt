package com.example.incidentmanager.ui.fragments.parking

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.incidentmanager.R
import com.example.incidentmanager.ui.viewmodels.ParkingFormViewModel

class ParkingFormFragment : Fragment() {

    companion object {
        fun newInstance() = ParkingFormFragment()
    }

    private lateinit var viewModel: ParkingFormViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_parking_form, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ParkingFormViewModel::class.java)
        // TODO: Use the ViewModel
    }

}