package com.example.timetracking.data.repository

import com.example.timetracking.data.entity.TimeSession
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
//Repositories will live same as the activity that requires them
@InstallIn(ActivityComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun providesEmployeeRepository(impl: EmployeeRepository): EmployeeRepository

    @Binds
    abstract fun provideTimeSessionRepository(impl: TimeSessionRepository): TimeSessionRepository
}