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
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.findNavController
import com.example.incidentmanager.R
import com.example.incidentmanager.data.db.repositories.models.Incidencia
import com.example.incidentmanager.databinding.FragmentIncidentBinding
import com.example.incidentmanager.databinding.FragmentLoginBinding
import com.example.incidentmanager.ui.fragments.adapters.IncidentAdapter
import com.example.incidentmanager.ui.fragments.user.LoginFragmentDirections
import com.example.incidentmanager.ui.fragments.user.LoginViewModel
import com.example.incidentmanager.ui.viewmodels.IncidentViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class IncidentFragment : Fragment() {

    lateinit var binding: FragmentIncidentBinding;
    private val viewModel:IncidentViewModel by viewModels();
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentIncidentBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = IncidentAdapter(requireContext())

        val recyclerView = binding.incidentList
        recyclerView.adapter = adapter

        viewLifecycleOwner.lifecycleScope.launch{
            repeatOnLifecycle(Lifecycle.State.STARTED){
                val list = viewModel.incidentList.collect{
                    adapter.submitList(it)
                }
            }
        }
        viewModel.viewModelScope.launch {
            binding.floatingActionButton.setOnClickListener{
                val navigation = IncidentFragmentDirections.actionIncidentFragmentToFormIncidentFragment("")
                findNavController().navigate(navigation)
            }
            binding.cerrarSesion.setOnClickListener{
                val navigation = IncidentFragmentDirections.actionIncidentFragmentToLoginFragment()
                findNavController().navigate(navigation)
                viewModel.logOff()
            }
        }
    }

    companion object { }
}