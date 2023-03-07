package com.example.timetracking.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "employee")
data class Employee(
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,
    var isAdmin: Int,
    var loginName: String,
    var loginPassword: String,
    var employeeName: String
)
