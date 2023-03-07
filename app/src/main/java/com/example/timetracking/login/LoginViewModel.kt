package com.example.timetracking.login

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.timetracking.data.entity.Employee
import com.example.timetracking.data.repository.EmployeeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val employeeRepository: EmployeeRepository
) : ViewModel() {

    private var _employee = MutableLiveData<Employee>()
    val employee: LiveData<Employee>
        get() = _employee

    val inputUsername = MutableStateFlow("")
    val inputPassword = MutableStateFlow("")

    fun getEmployeeByUserName() {
        viewModelScope.launch(Dispatchers.IO) {
            employeeRepository.getEmployeeByUserName(inputUsername.value).collect {
                _employee.postValue(it)
            }
            Log.d(TAG, "getEmployeeByUserName: ${employee.value?.employeeName}")
        }
    }

    fun setInputUserName(name: String) {
        inputUsername.value = name
    }

    fun setInputPassword(password: String) {
        inputPassword.value = password
    }
}