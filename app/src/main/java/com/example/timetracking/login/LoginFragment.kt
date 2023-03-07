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
import androidx.navigation.fragment.findNavController
import com.example.timetracking.databinding.FragmentLoginBinding
import dagger.hilt.android.AndroidEntryPoint

const val TAG = "LoginFragment"

@AndroidEntryPoint
class LoginFragment : Fragment() {
    private lateinit var binding: FragmentLoginBinding
    private val viewModel by viewModels<LoginViewModel>()

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

        binding.userName.addTextChangedListener { viewModel.setInputUserName(it.toString()) }
        binding.password.addTextChangedListener { viewModel.setInputPassword(it.toString()) }

        binding.loginButton.setOnClickListener {
            //TODO add some sort of spinner while checking userData for login
            viewModel.getEmployeeByUserName()
            checkLoginCredentials(
                viewModel.employee.value?.loginName,
                viewModel.employee.value?.loginPassword
            )
        }
        viewModel.employee.observe(viewLifecycleOwner) { /**no-op**/ }
    }

    private fun checkLoginCredentials(loginName: String?, loginPass: String?) {
        val isNameMatch = loginName.contentEquals(viewModel.inputUsername.value)
        val isPassMatch = loginPass.contentEquals(viewModel.inputPassword.value)
        when {
            binding.userName.text.isNullOrEmpty() || binding.password.text.isNullOrEmpty() -> {
                Toast.makeText(
                    requireContext(),
                    "Please enter a UserName and Password",
                    LENGTH_SHORT
                ).show()
            }
            viewModel.employee.value == null -> {
                Toast.makeText(
                    requireContext(),
                    "No Matching User found, please try again.",
                    LENGTH_SHORT
                ).show()
            }
            isNameMatch && isPassMatch -> {
                val action =
                    LoginFragmentDirections
                        .actionLoginFragmentToTimeTrackingFragment(viewModel.employee.value)
                findNavController().navigate(action)
            }
        }
    }
}
