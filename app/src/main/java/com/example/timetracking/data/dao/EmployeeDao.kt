package com.example.timetracking.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.timetracking.data.entity.Employee
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

@Dao
interface EmployeeDao {
    @Query("SELECT * FROM employee")
    fun getAllEmployees(): Flow<List<Employee>>

    @Query("SELECT * FROM employee WHERE id = :id")
    fun getEmployee(id: String): Flow<Employee>

    @Query("SELECT * FROM employee WHERE login_name = :userName")
    fun getEmployeeByUserName(userName: String): Employee

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertEmployees(employees: List<Employee>)
}