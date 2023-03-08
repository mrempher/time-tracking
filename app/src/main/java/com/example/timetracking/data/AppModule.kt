package com.example.timetracking.data

import android.content.Context
import androidx.room.Room
import com.example.timetracking.data.dao.EmployeeDao
import com.example.timetracking.data.dao.TimeSessionDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    fun provideEmployeeDao(appDatabase: AppDatabase): EmployeeDao {
        return appDatabase.employeeDao()
    }

    @Provides
    fun provideTimeSessionDao(appDatabase: AppDatabase): TimeSessionDao {
        return appDatabase.timeSessionDao()
    }

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context): AppDatabase {
        return Room.databaseBuilder(
            appContext,
            AppDatabase::class.java,
            "app_database"
        ).build()
    }
}