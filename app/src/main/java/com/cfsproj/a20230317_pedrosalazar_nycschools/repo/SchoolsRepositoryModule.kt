package com.cfsproj.a20230317_pedrosalazar_nycschools.repo

import com.cfsproj.a20230317_pedrosalazar_nycschools.network.OpenDataAPI
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@InstallIn(ViewModelComponent::class)
@Module
object SchoolsRepositoryModule {
    @Provides
    fun providesSchoolsRepository(api: OpenDataAPI): SchoolsRepository {
        return SchoolsRepository(api)
    }
}