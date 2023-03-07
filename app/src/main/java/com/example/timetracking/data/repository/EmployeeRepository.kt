package com.example.timetracking.data.repository

import com.example.timetracking.data.dao.EmployeeDao
import com.example.timetracking.data.entity.Employee
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class EmployeeRepository @Inject constructor(
    private val employeeDao: EmployeeDao
) {

    fun getAllEmployees(): Flow<List<Employee>> {
        return employeeDao.getAllEmployees()
    }

    suspend fun insertEmployees(employees: List<Employee>) {
        return withContext(Dispatchers.IO) {
            employeeDao.insertEmployees(employees)
        }
    }

    suspend fun getEmployeeByUserName(userName: String): Flow<Employee> {
        return withContext(Dispatchers.IO) {
            employeeDao.getEmployeeByUserName(userName)
        }
    }
}

