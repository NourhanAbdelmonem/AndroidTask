package com.example.nagwatask.framework.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nagwatask.business.entities.Content
import com.example.nagwatask.business.entities.DataState
import com.example.nagwatask.business.useCase.abstraction.ContentUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@ExperimentalCoroutinesApi
@HiltViewModel
class ContentsViewModel @Inject constructor(private val contentUseCase: ContentUseCase) :
    ViewModel() {

    private val _contentsDataState: MutableLiveData<DataState<List<Content>>> =
        MutableLiveData()
    val contentsDataState: LiveData<DataState<List<Content>>>
        get() = _contentsDataState

    init {
        getContents()
    }

    private fun getContents() {
        viewModelScope.launch {
            contentUseCase.getContents()
                .onStart {
                    _contentsDataState.value = DataState.Loading(true)
                }.catch {
                    _contentsDataState.value = DataState.Loading(false)
                    _contentsDataState.value = DataState.Failure(it)
                }.collect {
                    _contentsDataState.value = DataState.Loading(false)
                    _contentsDataState.value = DataState.Success(it)
                }
        }
    }

}