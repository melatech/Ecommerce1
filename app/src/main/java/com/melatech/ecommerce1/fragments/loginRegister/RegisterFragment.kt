package com.melatech.ecommerce1.fragments.loginRegister

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.melatech.ecommerce1.R
import com.melatech.ecommerce1.data.User
import com.melatech.ecommerce1.databinding.FragmentRegisterBinding
import com.melatech.ecommerce1.util.RegisterValidation
import com.melatech.ecommerce1.util.Resource
import com.melatech.ecommerce1.viewmodel.RegisterViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

const val TAG = "RegisterFragment"

@AndroidEntryPoint
class RegisterFragment : Fragment(R.layout.fragment_register) {

    private lateinit var binding: FragmentRegisterBinding
    private val viewModel by viewModels<RegisterViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRegisterBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.tvDoYouHaveAccount.setOnClickListener {
            findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
        }

        binding.apply {
            buttonRegisterRegister.setOnClickListener {
                Log.d("jason", "buttonRegisterRegister")
                val user = User(
                    edFirstNameRegister.text.toString().trim(),
                    edLastNameRegister.text.toString().trim(),
                    edEmailRegister.text.toString().trim()
                )
                val password = edPasswordRegister.text.toString()

                viewModel.createAccountWithEmailAndPassword(user, password)
            }
        }

        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
//            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    viewModel.register.collect {
                        when (it) {
                            is Resource.Loading -> {
                                binding.buttonRegisterRegister.startAnimation()
                            }

                            is Resource.Success -> {
                                Log.d("test", it.data.toString())
                                binding.buttonRegisterRegister.revertAnimation()
                            }

                            is Resource.Error -> {
                                Log.e(TAG, it.message.toString())
                                binding.buttonRegisterRegister.revertAnimation()
                            }

                            else -> Unit
                        }
                    }
                }
 //           }
        }
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
           // viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    viewModel.validation.collect { validation ->
                        if (validation.email is RegisterValidation.Failed){
                            withContext(Main){
                                binding.edEmailRegister.apply {
                                    requestFocus()
                                    error = validation.email.message
                                }
                            }
                        }
                        if (validation.password is RegisterValidation.Failed){
                            withContext(Main){
                                binding.edPasswordRegister.apply {
                                    requestFocus()
                                    error = validation.password.message
                                }
                            }

                        }
                    }

                }

            }

        }


 //   }
}