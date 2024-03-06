package com.example.incidentmanager.ui.fragments.user

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.example.incidentmanager.R
import com.example.incidentmanager.data.api.apimodels.UserLogin
import com.example.incidentmanager.data.db.repositories.IncidentManagerRepository
import com.example.incidentmanager.databinding.FragmentLoginBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import android.util.Base64

@AndroidEntryPoint
class LoginFragment : Fragment() {
    private lateinit var binding: FragmentLoginBinding
    private val viewModel: LoginViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.viewModelScope.launch {
            if(viewModel.checkLoginStatus()){
                findNavController().navigate(R.id.incidentFragment)
            }
            else{
                binding.bottomLogin.setOnClickListener{
                    val email = binding.editTextEmail.text.toString()
                    val password = binding.editTextPassword.text.toString()
                    val user = UserLogin(email,password)
                    viewModel.viewModelScope.launch {
                        val validator = viewModel.logIn(user)
                        if(validator != null)
                            findNavController().navigate(R.id.incidentFragment)
                        else
                            Toast.makeText(requireContext(), "El usuario no existe o la contraseña no es correcta", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }

    }


}