package com.example.timetracking.timekeeping

import android.app.TimePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.timetracking.R
import com.example.timetracking.databinding.FragmentTimeTrackingBinding
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

const val TAG = "TimeTrackingFragment"

@AndroidEntryPoint
class TimeTrackingFragment : Fragment() {
    private lateinit var binding: FragmentTimeTrackingBinding

    private val viewModel by viewModels<TimeTrackingViewModel>()
    private val args: TimeTrackingFragmentArgs by navArgs()

    private var isAllFabsVisible: Boolean = false


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
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
                else -> {
                    false
                }
            }
        }

        binding.timeFab.shrink()
        binding.timeFab.setOnClickListener { handleFabVisibility() }

        binding.startTimeFab.setOnClickListener {
            val timePicker: TimePickerDialog
            val currentTime = Calendar.getInstance()

            timePicker = TimePickerDialog(
                requireContext(), { view, hourOfDay, minute ->
                    binding.startTimeText.setText(
                        String.format("%d : %d", hourOfDay, minute)
                    )
                },
                currentTime.get(Calendar.HOUR_OF_DAY),
                currentTime.get(Calendar.MINUTE),
                false
            )
            timePicker.show()
        }

        binding.endTimeFab.setOnClickListener {
            val timePicker: TimePickerDialog
            val currentTime = Calendar.getInstance()

            timePicker = TimePickerDialog(
                requireContext(), { view, hourOfDay, minute ->
                    binding.endTimeText.setText(
                        String.format("%d : %d", hourOfDay, minute)
                    )
                },
                currentTime.get(Calendar.HOUR_OF_DAY),
                currentTime.get(Calendar.MINUTE),
                false
            )
            timePicker.show()
        }

        return binding.root
    }

    private fun handleFabVisibility() {
        if (!isAllFabsVisible) {
            binding.startTimeFab.visibility = View.VISIBLE
            binding.startTimeFabText.visibility = View.VISIBLE
            binding.endTimeFab.visibility = View.VISIBLE
            binding.endTimeFabText.visibility = View.VISIBLE

            binding.timeFab.extend()

            isAllFabsVisible = true
        } else {
            binding.startTimeFab.visibility = View.GONE
            binding.startTimeFabText.visibility = View.GONE
            binding.endTimeFab.visibility = View.GONE
            binding.endTimeFabText.visibility = View.GONE

            binding.timeFab.shrink()
            isAllFabsVisible = false
        }
    }
}