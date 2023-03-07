package com.example.timetracking.timekeeping

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.core.view.MenuProvider
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.timetracking.R
import com.example.timetracking.databinding.FragmentTimeTrackingBinding
import dagger.hilt.android.AndroidEntryPoint

const val TAG = "TimeTrackingFragment"
@AndroidEntryPoint
class TimeTrackingFragment : Fragment(), MenuProvider {
    private lateinit var binding: FragmentTimeTrackingBinding

    private val viewModel by viewModels<TimeTrackingViewModel>()
    private val args: TimeTrackingFragmentArgs by navArgs()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        args.employeeArgument?.let {
            viewModel.setEmployeeData(it)
        }

        requireActivity().addMenuProvider(this, viewLifecycleOwner, Lifecycle.State.RESUMED)

        binding = FragmentTimeTrackingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
        menuInflater.inflate(R.menu.menu_main, menu)
    }

    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
        return when (menuItem.itemId) {
            R.id.nav_logout -> {
                findNavController().popBackStack(R.id.loginFragment, false)
                true
            }
            else -> {
                false
            }
        }
    }

}