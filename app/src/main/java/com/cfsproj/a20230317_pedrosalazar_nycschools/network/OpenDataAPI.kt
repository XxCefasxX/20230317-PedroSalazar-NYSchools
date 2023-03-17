package com.cfsproj.a20230317_pedrosalazar_nycschools.network

import com.cfsproj.a20230317_pedrosalazar_nycschools.domain.HighSchoolResponse
import com.cfsproj.a20230317_pedrosalazar_nycschools.domain.SATScoreResponse
import retrofit2.http.GET

interface OpenDataAPI {

    @GET("s3k6-pzi2.json")
    suspend fun highSchools(): List<HighSchoolResponse>

    @GET("f9bf-2cp4.json")
    suspend fun satScores(): List<SATScoreResponse>

}