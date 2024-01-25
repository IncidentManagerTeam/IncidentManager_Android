package com.example.incidentmanager.ui.fragments.parking

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.viewModelScope
import com.example.incidentmanager.data.db.repositories.models.Parking
import com.example.incidentmanager.databinding.FragmentParkingFormBinding
import com.example.incidentmanager.ui.viewmodels.ParkingFormViewModel
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date

class ParkingFormFragment : Fragment() {
    lateinit var binding:FragmentParkingFormBinding
    val viewModel:ParkingFormViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentParkingFormBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        //Este valor se cambia a una funcion futura que hara una peticion al servidor para que genere una clave y la devuelva
        val numeroSolicitud:Long = 2

        binding.buttomCreateRequest.setOnClickListener {
            var matricula = binding.licensePlate.text.toString();
            var nAlumnos = binding.students.text.toString();
            var fecha = getDatePhone()
            binding.Date.text = fecha

            if (nAlumnos.isNotBlank() && matricula.isNotBlank() && fecha.isNotBlank()) {
                viewModel.viewModelScope.launch {
                    viewModel.insertParking(
                        Parking(
                            matricula,numeroSolicitud,nAlumnos.toInt(),fecha,"pendiente"
                    )
                }
            } else {
                val toastMessage = "Rellene todos los datos"
                Toast.makeText(requireContext(), toastMessage, Toast.LENGTH_SHORT).show()
            }
    }
    }
    private fun getDatePhone(): String {
        val dt = Date()
        val format = SimpleDateFormat("dd-MM-yyyy")
        val newDate = format.format(dt)
        return newDate;
    }
}