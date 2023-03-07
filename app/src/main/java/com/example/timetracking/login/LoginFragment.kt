package com.example.timetracking.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.timetracking.databinding.FragmentLoginBinding
import dagger.hilt.android.AndroidEntryPoint

const val TAG = "LoginFragment"
@AndroidEntryPoint
class LoginFragment : Fragment() {
    private lateinit var binding: FragmentLoginBinding
    private val viewModel: LoginViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.userName.addTextChangedListener {
            viewModel.setInputUserName(it.toString())
        }

        binding.password.addTextChangedListener {
            viewModel.setInputPassword(it.toString())
        }

        binding.loginButton.setOnClickListener {
            viewModel.getEmployeeByUserName()
            when {
                binding.userName.text.isNullOrEmpty() || binding.password.text.isNullOrEmpty() -> {
                    Toast.makeText(requireContext(), "Please enter a UserName and Password", LENGTH_SHORT).show()
                }
                viewModel.employee.value == null -> {
                    Toast.makeText(requireContext(), "No Matching User found, please try again.", LENGTH_SHORT).show()
                }
            }
        }
    }
}
