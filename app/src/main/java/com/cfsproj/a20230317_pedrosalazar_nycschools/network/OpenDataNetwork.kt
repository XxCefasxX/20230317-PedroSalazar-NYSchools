package com.cfsproj.a20230317_pedrosalazar_nycschools.network

import com.cfsproj.a20230317_pedrosalazar_nycschools.util.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


@InstallIn(ViewModelComponent::class)
@Module
object OpenDataNetwork {
    @Provides
    fun providesRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    fun providesNYCOpenDataAPI(retrofit: Retrofit): OpenDataAPI {
        return retrofit.create(OpenDataAPI::class.java)
    }


}