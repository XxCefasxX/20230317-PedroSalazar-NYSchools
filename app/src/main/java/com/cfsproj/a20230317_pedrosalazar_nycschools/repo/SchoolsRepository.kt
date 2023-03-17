package com.cfsproj.a20230317_pedrosalazar_nycschools.repo


import com.cfsproj.a20230317_pedrosalazar_nycschools.domain.HighSchoolResponse
import com.cfsproj.a20230317_pedrosalazar_nycschools.domain.SATScoreResponse
import com.cfsproj.a20230317_pedrosalazar_nycschools.domain.SchoolModel
import com.cfsproj.a20230317_pedrosalazar_nycschools.network.OpenDataAPI

class SchoolsRepository(private val api: OpenDataAPI) {

    private var highSchools: List<HighSchoolResponse>? = null
    private var satScores: List<SATScoreResponse>? = null

    private fun fetchSchools(highSchools: List<HighSchoolResponse>?, satScores: List<SATScoreResponse>?): List<SchoolModel> {
        if(highSchools == null || satScores == null) return listOf()
        val schoolsMap = mutableMapOf<String, SATScoreResponse>()
        for (satScore in satScores) {
            schoolsMap[satScore.dbn ?: ""] = satScore
        }
        return highSchools.map {
            SchoolModel(school = it, satScores = schoolsMap[it.dbn])
        }
    }

    suspend fun getSchoolModel(callback : SchoolsCallback){
        try {
            highSchools = api.highSchools()
            satScores = api.satScores()
            val result = fetchSchools(highSchools, satScores)
            if(result.isNotEmpty()){
                callback.onSuccess(result)
            }
        }
        catch (e: Exception){
            callback.onFailure(e)
        }
    }
    interface SchoolsCallback {
        fun onSuccess(schools: List<SchoolModel>)
        fun onFailure(e: Throwable)
    }
}
