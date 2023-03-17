package com.cfsproj.a20230317_pedrosalazar_nycschools.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cfsproj.a20230317_pedrosalazar_nycschools.domain.Result
import com.cfsproj.a20230317_pedrosalazar_nycschools.domain.SchoolModel
import com.cfsproj.a20230317_pedrosalazar_nycschools.domain.State
import com.cfsproj.a20230317_pedrosalazar_nycschools.repo.SchoolsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject


@HiltViewModel
class SchoolViewModel @Inject constructor(
    val repository: SchoolsRepository
) : ViewModel() {
    private val mState = MutableStateFlow<State>(State.Ready())
    private val mSearchResult = MutableStateFlow<List<SchoolModel>>(listOf())
    private val mCurrentSelection = MutableStateFlow(SchoolModel())
    val lState: StateFlow<State>
        get() = mState
    val lSearchResult: StateFlow<List<SchoolModel>>
        get() = mSearchResult

    val lCurrentSelection: StateFlow<SchoolModel>
        get() = mCurrentSelection

    @OptIn(DelicateCoroutinesApi::class)
    fun fetchSchools() {

        if (mState.value.message != Result.Ready) {
            return
        }
        mState.value = State.Loading()
        viewModelScope.launch {
            repository.getSchoolModel(
                object : SchoolsRepository.SchoolsCallback {
                    override fun onSuccess(schools: List<SchoolModel>) {
                        mState.value = State.Success(schools.sortedBy { it.schoolName })
                        search("")
                    }

                    override fun onFailure(e: Throwable) {
                        mState.value = State.Failure(e)
                    }
                }
            )
        }


    }

    fun select(schoolModel: SchoolModel) {
        mCurrentSelection.value = schoolModel
    }


    fun search(query: String) {
        val schools = (mState.value as? State.Success)?.schools
        if (schools != null) {
            mSearchResult.value = schools.filter {
                it.schoolName.contains(query, true)
            }
        }
    }

    private val _message = MutableStateFlow("")
    val message: StateFlow<String> get() = _message

    fun loadMessage() {
        viewModelScope.launch {
            _message.value = "Greetings!"
        }
    }

    init {
        fetchSchools()
        search("")
    }

}

