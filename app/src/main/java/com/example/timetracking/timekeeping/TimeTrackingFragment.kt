package com.example.timetracking.timekeeping

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.timetracking.R

class TimeTrackingFragment : Fragment() {

    companion object {
        fun newInstance() = TimeTrackingFragment()
    }

    private lateinit var viewModel: TimeTrackingViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_time_tracking, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(TimeTrackingViewModel::class.java)
        // TODO: Use the ViewModel
    }

}