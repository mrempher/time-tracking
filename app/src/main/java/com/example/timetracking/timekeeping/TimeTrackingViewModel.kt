package com.example.timetracking.timekeeping

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.timetracking.data.entity.Employee
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TimeTrackingViewModel @Inject constructor(

) : ViewModel() {

    private var _employee = MutableLiveData<Employee?>()
    val employee: LiveData<Employee?>
        get() = _employee


    fun setEmployeeData(employee: Employee?) {
        _employee.postValue(employee)
    }
    // TODO: Implement the ViewModel
}