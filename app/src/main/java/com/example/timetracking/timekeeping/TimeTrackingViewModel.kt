package com.example.timetracking.timekeeping

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.timetracking.data.entity.Employee
import com.example.timetracking.data.entity.TimeSession
import com.example.timetracking.data.repository.TimeSessionRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TimeTrackingViewModel @Inject constructor(
        private val timeSessionRepository: TimeSessionRepository
) : ViewModel() {

    private var _employee = MutableLiveData<Employee?>()
    val employee: LiveData<Employee?>
        get() = _employee


    fun setEmployeeData(employee: Employee?) {
        _employee.postValue(employee)
    }

    fun saveTimeSession(startTime: String, endTime: String) {
        viewModelScope.launch(Dispatchers.IO) {
            timeSessionRepository.insertTimeSession(
                    TimeSession(
                        employeeId = _employee.value?.id ?: 0,
                        startTime = startTime,
                        endTime = endTime
                    )
            )
        }
    }
}