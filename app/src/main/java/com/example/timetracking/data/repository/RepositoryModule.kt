package com.example.timetracking.data.repository

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
//Repositories will live same as the activity that requires them
@InstallIn(ActivityComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun providesChannelRepository(impl: EmployeeRepository): EmployeeRepository

}