package com.example.timetracking.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.timetracking.R
import com.example.timetracking.databinding.FragmentLoginBinding
import dagger.hilt.android.AndroidEntryPoint

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

        binding.loginButton.setOnClickListener {
            viewModel.setInputUserName(binding.userName.text.toString())
            viewModel.setInputPassword(binding.password.text.toString())
            viewModel.getEmployeeByUserName(binding.userName.text.toString())
        }

        viewModel.employee.observe(viewLifecycleOwner) { }
        viewModel.isLoading.observe(viewLifecycleOwner) {
            //TODO handle spinner for Loading
        }
        viewModel.isDone.observe(viewLifecycleOwner) {
            //TODO not loving this approach
            if (it == true) {
                checkLoginCredentials(
                    viewModel.employee.value?.loginName,
                    viewModel.employee.value?.loginPassword
                )
            }
        }
    }

    private fun checkLoginCredentials(loginName: String?, loginPass: String?) {
        val isNameMatch = loginName.contentEquals(viewModel.inputUsername.value)
        val isPassMatch = loginPass.contentEquals(viewModel.inputPassword.value)
        when {
            binding.userName.text.isNullOrEmpty() || binding.password.text.isNullOrEmpty() -> {
                Toast.makeText(
                    requireContext(),
                    getString(R.string.username_password_empty),
                    LENGTH_SHORT
                ).show()
            }
            viewModel.employee.value == null -> {
                Toast.makeText(
                    requireContext(),
                    getString(R.string.no_valid_employee_found),
                    LENGTH_SHORT
                ).show()
            }
            !isPassMatch -> {
                Toast.makeText(
                    requireContext(),
                    getString(R.string.password_is_incorrect),
                    LENGTH_SHORT
                ).show()
            }
            !isNameMatch -> {
                Toast.makeText(
                    requireContext(),
                    getString(R.string.username_is_incorrect),
                    LENGTH_SHORT
                ).show()
            }
            isNameMatch && isPassMatch -> {
                binding.userName.text?.clear()
                binding.password.text?.clear()
                viewModel.setInputUserName("")
                viewModel.setInputPassword("")
                val action =
                    LoginFragmentDirections
                        .actionLoginFragmentToTimeTrackingFragment(viewModel.employee.value)
                viewModel.clearEmployeeData()
                findNavController().navigate(action)
            }
        }
    }
}
