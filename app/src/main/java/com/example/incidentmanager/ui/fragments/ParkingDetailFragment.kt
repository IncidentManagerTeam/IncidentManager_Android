package com.example.incidentmanager.ui.fragments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.incidentmanager.R
import com.example.incidentmanager.ui.viewmodels.ParkingDetailViewModel

class ParkingDetailFragment : Fragment() {

    companion object {
        fun newInstance() = ParkingDetailFragment()
    }

    private lateinit var viewModel: ParkingDetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_parking_detail, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ParkingDetailViewModel::class.java)
        // TODO: Use the ViewModel
    }

}