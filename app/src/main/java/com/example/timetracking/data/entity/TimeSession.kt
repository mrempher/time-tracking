package com.example.timetracking.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    foreignKeys = [ForeignKey(
        entity = Employee::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("employee_id"),
        onDelete = ForeignKey.CASCADE
    )]
)
data class TimeSession(
    @PrimaryKey(autoGenerate = true) var id: Int? = null,
    @ColumnInfo(name = "employee_id") var employeeId: Int = 0,
    @ColumnInfo(name = "start_time") var startTime: String = "",
    @ColumnInfo(name = "end_time") var endTime: String = ""
)