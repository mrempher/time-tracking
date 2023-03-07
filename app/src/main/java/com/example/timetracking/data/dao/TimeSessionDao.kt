package com.example.timetracking.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.timetracking.data.entity.TimeSession

@Dao
interface TimeSessionDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTimeSession(timeSession: TimeSession)
}