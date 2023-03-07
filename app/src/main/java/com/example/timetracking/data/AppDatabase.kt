package com.example.timetracking.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.timetracking.data.dao.EmployeeDao
import com.example.timetracking.data.entity.Employee

@Database(
    entities = [
        Employee::class
    ],
    version = 1,
    exportSchema = true
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun employeeDao(): EmployeeDao
}