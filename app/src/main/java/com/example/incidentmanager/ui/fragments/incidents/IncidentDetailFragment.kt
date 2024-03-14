package com.example.incidentmanager.ui.fragments.incidents

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Base64
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.incidentmanager.R
import com.example.incidentmanager.databinding.FragmentIncidentDetailBinding
import com.example.incidentmanager.ui.viewmodels.IncidentDetailViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class IncidentDetailFragment : Fragment() {
    lateinit var binding : FragmentIncidentDetailBinding
    private  val arguments : IncidentDetailFragmentArgs by navArgs()
    private  val viewModel : IncidentDetailViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentIncidentDetailBinding.inflate(inflater,
            container,
            false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val id = arguments.id
        val title = arguments.title
        val description = arguments.description
        val place = arguments.place
        val image = arguments.imageUri
        if(image != "" && image != null) {
            var byteArray = Base64.decode(image, 2)
            var bitmap: Bitmap = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.size)
            binding.imgIncidentDetail.setImageBitmap(bitmap)
        }
        binding.descriptionIncident.text = description
        binding.titleIncident.text = title
        binding.ubicationIncident.text = place
        binding.backButton.setOnClickListener {
            findNavController().navigate(R.id.incidentFragment)
        }
        binding.deleteIncident.setOnClickListener{
            viewModel.viewModelScope.launch {
                viewModel.deleteIncident(id)
                findNavController().navigate(R.id.incidentFragment)
            }
        }
    }
}