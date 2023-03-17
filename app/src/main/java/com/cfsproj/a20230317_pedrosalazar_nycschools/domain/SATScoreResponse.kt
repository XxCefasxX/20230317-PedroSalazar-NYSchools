package com.cfsproj.a20230317_pedrosalazar_nycschools.domain

import com.google.gson.annotations.SerializedName

data class SATScoreResponse(

    @SerializedName("dbn") var dbn: String? = null,
    @SerializedName("school_name") var schoolName: String? = null,
    @SerializedName("num_of_sat_test_takers") var numOfSatTestTakers: String? = null,
    @SerializedName("sat_critical_reading_avg_score") var satCriticalReadingAvgScore: String? = null,
    @SerializedName("sat_math_avg_score") var satMathAvgScore: String? = null,
    @SerializedName("sat_writing_avg_score") var satWritingAvgScore: String? = null

)