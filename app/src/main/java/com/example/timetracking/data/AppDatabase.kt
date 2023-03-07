package com.example.timetracking.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.timetracking.data.dao.EmployeeDao
import com.example.timetracking.data.dao.TimeSessionDao
import com.example.timetracking.data.entity.Employee
import com.example.timetracking.data.entity.TimeSession

@Database(
    entities = [
        Employee::class,
        TimeSession::class
    ],
    version = 1,
    exportSchema = true
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun employeeDao(): EmployeeDao
    abstract fun timeSessionDao(): TimeSessionDao
}