package com.example.incidentmanager.ui.fragments.user

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.findNavController
import com.example.incidentmanager.R
import com.example.incidentmanager.data.api.apimodels.UserModel
import com.example.incidentmanager.data.db.repositories.models.User
import com.example.incidentmanager.databinding.FragmentRegisterBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class RegisterFragment : Fragment() {

    lateinit var binding : FragmentRegisterBinding
    private val viewModel: LoginViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRegisterBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.buttomRegister.setOnClickListener{
            var email = binding.emailClass.text.toString()
            var password = binding.password.text.toString()
            var passwordAuthentication = binding.repitePassword.text.toString()
            var name = binding.name.text.toString()
            var surname = binding.surname.text.toString()
            var classroom = binding.selectClass.text.toString()
            var user = UserModel(
                name,
                surname,
                email,
                classroom,
                "admin",
                password
            )
            if(password == passwordAuthentication) {
                viewModel.viewModelScope.launch {
                    var validator = viewModel.register(user)
                    if (validator != null)
                        findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToIncidentFragment())
                    else
                        Toast.makeText(
                            requireContext(),
                            "Rellene todos los campos",
                            Toast.LENGTH_SHORT
                        )
                            .show()
                }
            }
            else{
                Toast.makeText(requireContext(), "Ambas contraseñas deben ser iguales", Toast.LENGTH_SHORT)
                    .show()
            }


        }

    }
}