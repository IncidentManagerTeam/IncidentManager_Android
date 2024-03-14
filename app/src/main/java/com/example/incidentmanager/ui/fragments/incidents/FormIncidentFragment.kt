package com.example.incidentmanager.ui.fragments.incidents

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.findNavController
import com.example.incidentmanager.R
import com.example.incidentmanager.data.db.repositories.models.IncidenciaRequest
import com.example.incidentmanager.databinding.FragmentFormIncidentBinding
import com.example.incidentmanager.ui.viewmodels.IncidentFormViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.MediaType.Companion.parse
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.util.Calendar

@AndroidEntryPoint
class FormIncidentFragment : Fragment() {
    lateinit var binding: FragmentFormIncidentBinding
    val viewModel: IncidentFormViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFormIncidentBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val idNav = 1;
        val result="";
        binding.photoPreview.setOnClickListener{
            val navigation = FormIncidentFragmentDirections.actionFormIncidentFragmentToPreviewIncidentFragment()
            findNavController().navigate(navigation);
        }
        var arguments = arguments?.getString("imageUri")
        var bitmap: Bitmap?=null
        if(arguments != null && arguments != "") {
            val uri = arguments.toUri()
            bitmap = context?.contentResolver?.openInputStream(uri)?.use {
                BitmapFactory.decodeStream(it)
            }
            binding.photoPreview.visibility = View.GONE
            binding.imagePreview.visibility = View.VISIBLE
            binding.imagePreview.setImageBitmap(bitmap)
        }
        else{
            binding.photoPreview.visibility = View.VISIBLE
            binding.imagePreview.visibility = View.GONE
        }
        binding.backButton.setOnClickListener{
            findNavController().navigate(R.id.incidentFragment)
        }
        binding.buttomCreateIncident.setOnClickListener {
            val titulo = binding.title.text.toString()
            val descripcion = binding.description.text.toString()
            val ubicacion = binding.ubication.text.toString()
            val imagen = binding.imagePreview.toString()


            if (titulo.isNotBlank() && descripcion.isNotBlank() && ubicacion.isNotBlank() && imagen.isNotBlank()) {
                viewModel.viewModelScope.launch {
                    val currentDate = Calendar.getInstance()
                    val dateFormat = SimpleDateFormat("yyyy-MM-dd")
                    val formattedDate = dateFormat.format(currentDate.time)
                    val f = withContext(Dispatchers.IO) {
                        File(context?.cacheDir, "$titulo _$formattedDate").apply {
                            createNewFile()
                        }
                    }

                    val outputStream = ByteArrayOutputStream()
                    bitmap?.compress(Bitmap.CompressFormat.JPEG, 75, outputStream)
                    val byteArray = outputStream.toByteArray()

                    withContext(Dispatchers.IO) {
                        FileOutputStream(f).use { fos ->
                            fos.write(byteArray)
                            fos.flush()
                        }
                    }

                    val reqFile: RequestBody = RequestBody.create("image/*".toMediaTypeOrNull(), f)
                    val body: MultipartBody.Part = MultipartBody.Part.createFormData("image", f.name, reqFile)

                    val incident = IncidenciaRequest(
                        titulo,
                        descripcion,
                        ubicacion,
                        "Pending",
                        -1
                    )

                    viewModel.createOneIncident(incident, body)
                    findNavController().navigate(R.id.incidentFragment)

                }
            } else {
                val toastMessage = "Rellene todos los datos"
                Toast.makeText(requireContext(), toastMessage, Toast.LENGTH_SHORT).show()
            }
        }

    }
    //CameraXConfiguration
    companion object{

    }

}