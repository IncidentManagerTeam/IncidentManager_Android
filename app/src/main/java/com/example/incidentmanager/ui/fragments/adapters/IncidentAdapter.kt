package com.example.incidentmanager.ui.fragments.adapters

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.fragment.app.findFragment
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.imageLoader
import coil.request.ImageRequest
import com.example.incidentmanager.R
import com.example.incidentmanager.data.db.repositories.models.Incidencia
import com.example.incidentmanager.databinding.IncidentItemBinding
import com.example.incidentmanager.ui.fragments.incidents.IncidentFragmentDirections

class IncidentAdapter(private val context : Context):ListAdapter<Incidencia,IncidentAdapter.IncidentViewHolder>(IncidentDiffCallback) {
    inner class IncidentViewHolder(private val binding:IncidentItemBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(incident:Incidencia){
            binding.incidentName.text = incident.titulo
            if(incident.img != "" && incident.img != null) {
                var byteArray = Base64.decode(incident.img, 2)
                var bitmap: Bitmap = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.size)
                binding.incidentImg.setImageBitmap(bitmap)
            }
            binding.constraintIncidentItem.setOnClickListener {
                NavHostFragment.findNavController(binding.constraintIncidentItem.findFragment()).navigate(IncidentFragmentDirections.actionIncidentFragmentToIncidentDetailFragment(
                    title = incident.titulo,
                    description = incident.descripcion,
                    place = incident.lugar,
                    imageUri = incident.img
                ))
            }
        }
    }
    private object IncidentDiffCallback: DiffUtil.ItemCallback<Incidencia>(){
        override fun areItemsTheSame(oldItem: Incidencia, newItem: Incidencia) = oldItem.id == newItem.id
        override fun areContentsTheSame(oldItem: Incidencia, newItem: Incidencia) = oldItem == newItem

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IncidentViewHolder =
        IncidentViewHolder(IncidentItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false))

    override fun onBindViewHolder(holder: IncidentViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}