package com.example.timetracking.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.timetracking.data.entity.Employee
import com.example.timetracking.data.repository.EmployeeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val employeeRepository: EmployeeRepository
) : ViewModel() {

    private var _employee = MutableLiveData<Employee?>()
    val employee: LiveData<Employee?>
        get() = _employee

    private var _isLoading = MutableLiveData<Boolean?>(null)
    val isLoading: LiveData<Boolean?>
        get() = _isLoading

    private var _isDone = MutableLiveData(false)
    val isDone: LiveData<Boolean>
        get() = _isDone

    private var _inputUserName = MutableLiveData<String>()
    val inputUsername: LiveData<String>
        get() = _inputUserName

    private var _inputPassword = MutableLiveData<String>()
    val inputPassword: LiveData<String>
        get() = _inputPassword

    fun getEmployeeByUserName(userName: String) {
        _isLoading.postValue(true)
        viewModelScope.launch(Dispatchers.IO) {
            employeeRepository.getEmployeeByUserName(userName).collectLatest {
                _employee.postValue(it)
                _isLoading.postValue(false)
                _isDone.postValue(true)
            }
        }
    }

    fun setInputUserName(name: String) {
        _inputUserName.postValue(name)
    }

    fun setInputPassword(password: String) {
        _inputPassword.postValue(password)
    }

    fun setIsLoading(isLoading: Boolean) {
        _isLoading.postValue(isLoading)
    }

    fun clearEmployeeData() {
        _employee.postValue(null)
        _isLoading.postValue(null)
        _isDone.postValue(false)
    }
}