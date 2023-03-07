package com.example.timetracking.timekeeping

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.timetracking.R
import com.example.timetracking.databinding.FragmentTimeTrackingBinding
import dagger.hilt.android.AndroidEntryPoint

const val TAG = "TimeTrackingFragment"
@AndroidEntryPoint
class TimeTrackingFragment : Fragment() {
    private lateinit var binding: FragmentTimeTrackingBinding

    private val viewModel by viewModels<TimeTrackingViewModel>()
    private val args: TimeTrackingFragmentArgs by navArgs()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTimeTrackingBinding.inflate(inflater, container, false)

        args.employeeArgument?.let {
            viewModel.setEmployeeData(it)
        }

        binding.toolbar.inflateMenu(R.menu.menu_main)
        binding.toolbar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.nav_logout -> {
                    findNavController().popBackStack(R.id.loginFragment, false)
                    true
                }
                else -> { false }
            }
        }
        return binding.root
    }
}