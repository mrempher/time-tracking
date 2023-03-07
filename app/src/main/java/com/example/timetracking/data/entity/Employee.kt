package com.example.timetracking.data.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity
data class Employee(
    @PrimaryKey(autoGenerate = true) var id: Int? = null,
    @ColumnInfo(name = "is_admin") var isAdmin: Int = 0,
    @ColumnInfo(name = "login_name") var loginName: String = "",
    @ColumnInfo(name = "login_password") var loginPassword: String = "",
    @ColumnInfo(name = "employee_name") var employeeName: String = ""
): Parcelable {

}
