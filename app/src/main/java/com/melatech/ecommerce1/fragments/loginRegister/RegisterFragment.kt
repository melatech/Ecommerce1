package com.melatech.ecommerce1.fragments.loginRegister

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.melatech.ecommerce1.R
import com.melatech.ecommerce1.data.User
import com.melatech.ecommerce1.databinding.FragmentRegisterBinding
import com.melatech.ecommerce1.util.Resource
import com.melatech.ecommerce1.viewmodel.RegisterViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

const val TAG = "RegisterFragment"

@AndroidEntryPoint
class RegisterFragment : Fragment(R.layout.fragment_login) {

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
        binding.apply {
            buttonRegisterRegister.setOnClickListener {
                val user = User(
                    edFirstNameRegister.text.toString().trim(),
                    edLastNameRegister.text.toString().trim(),
                    edEmailRegister.text.toString().trim()
                )
                val password = edPasswordRegister.text.toString()
                viewModel.createAccountWithEmailAndPassword(user, password)
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
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
                        }
                    }
                }
            }
        }
    }
}