package com.example.timetracking.login

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.timetracking.data.entity.Employee
import com.example.timetracking.data.repository.EmployeeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val employeeRepository: EmployeeRepository
) : ViewModel() {

    private var _employee: Flow<Employee?> = MutableStateFlow(null)
    var employee = _employee.asLiveData()

    val inputUsername = MutableStateFlow("")

    val inputPassword = MutableStateFlow("")

    fun getEmployeeByUserName() =
        viewModelScope.launch {
            _employee = employeeRepository.getEmployeeByUserName(inputUsername.value)
            Log.d(TAG, "getEmployeeByUserName: ${employee.value?.employeeName}")
        }

    fun setInputUserName(name: String) {
        inputUsername.value = name
    }

    fun setInputPassword(password: String) {
        inputPassword.value = password
    }
}