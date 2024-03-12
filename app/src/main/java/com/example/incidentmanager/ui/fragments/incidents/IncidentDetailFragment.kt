package com.example.incidentmanager.ui.fragments.incidents

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Base64
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.example.incidentmanager.R
import com.example.incidentmanager.databinding.FragmentIncidentDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class IncidentDetailFragment : Fragment() {
    lateinit var binding : FragmentIncidentDetailBinding
    private  val arguments : IncidentDetailFragmentArgs by navArgs()

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
        var title = arguments.title
        var description = arguments.description
        var place = arguments.place
        var image = arguments.imageUri
        if(image != "" && image != null) {
            var byteArray = Base64.decode(image, 2)
            var bitmap: Bitmap = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.size)
            binding.imgIncidentDetail.setImageBitmap(bitmap)
        }
        binding.descriptionIncident.text = description
        binding.titleIncident.text = title
        binding.ubicationIncident.text = place

    }
}