package com.example.timetracking.data.repository

import com.example.timetracking.data.dao.TimeSessionDao
import com.example.timetracking.data.entity.TimeSession
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject


class TimeSessionRepository @Inject constructor(
    private val timeSessionDao: TimeSessionDao
) {

    suspend fun insertTimeSession(timeSession: TimeSession) {
        return withContext(Dispatchers.IO) {
            timeSessionDao.insertTimeSession(timeSession)
        }
    }
}