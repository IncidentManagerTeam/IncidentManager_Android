package com.example.incidentmanager.ui.fragments.incidents

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.findNavController
import com.example.incidentmanager.R
import com.example.incidentmanager.databinding.FragmentIncidentBinding
import com.example.incidentmanager.databinding.FragmentLoginBinding
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
        viewModel.viewModelScope.launch {
            binding.floatingActionButton.setOnClickListener{
                var navigation = IncidentFragmentDirections.actionIncidentFragmentToFormIncidentFragment()
                findNavController().navigate(navigation);
            }
        }
    }

    companion object { }
}