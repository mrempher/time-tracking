package com.example.timetracking.host

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.timetracking.data.entity.Employee
import com.example.timetracking.data.repository.EmployeeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val employeeRepository: EmployeeRepository
) : ViewModel() {

    fun insertDBData() {
        viewModelScope.launch(Dispatchers.IO) {
            employeeRepository.insertEmployees(
                listOf(
                    Employee(
                        id = 1,
                        isAdmin = 1,
                        loginName = "admin",
                        loginPassword = "password",
                        employeeName = "adminName"
                    ),
                    Employee(
                        id = 2,
                        isAdmin = 0,
                        loginName = "testUserOne",
                        loginPassword = "password",
                        employeeName = "testUserOne"
                    ),
                    Employee(
                        id = 3,
                        isAdmin = 0,
                        loginName = "testUserTwo",
                        loginPassword = "password",
                        employeeName = "testUserTwo"
                    )
                )
            )
        }
    }
}