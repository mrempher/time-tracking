package com.example.timetracking.data.repository

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun providesEmployeeRepository(impl: EmployeeRepository): EmployeeRepository

    @Binds
    abstract fun provideTimeSessionRepository(impl: TimeSessionRepository): TimeSessionRepository
}