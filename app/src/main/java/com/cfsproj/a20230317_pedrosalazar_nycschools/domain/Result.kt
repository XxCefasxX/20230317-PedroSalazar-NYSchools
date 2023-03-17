package com.cfsproj.a20230317_pedrosalazar_nycschools.domain


enum class Result{
    SUCCESS,
    ERROR,
    LOADING,
    Ready
}

sealed class State(val message : Result){
    class Ready: State(Result.Ready)
    class Loading: State(Result.LOADING)
    class Success(val schools: List<SchoolModel>): State(Result.SUCCESS)
    class Failure(val throwable: Throwable): State(Result.ERROR)
}