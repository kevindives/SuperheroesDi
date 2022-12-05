package com.magicworld.superheroesdi.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.magicworld.superheroesdi.model.SuperheroeItem
import com.magicworld.superheroesdi.repository.ListRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(private val listRepository: ListRepository) : ViewModel() {

    private var lastScrollIndex = 0

    private val _scrollUp = MutableLiveData(false)
    val scrollUp: LiveData<Boolean>
        get() = _scrollUp

    fun updateScrollPosition(newScrollIndex: Int) {
        if (newScrollIndex == lastScrollIndex) return

        _scrollUp.value = newScrollIndex > lastScrollIndex
        lastScrollIndex = newScrollIndex
    }

    private val _isLoading = MutableStateFlow(false)
    val isRefreshing = _isLoading.asStateFlow()

    init {
        refresh()
    }

    fun refresh(){
        viewModelScope.launch {
            _isLoading.value = true
            delay(3000L)
            _isLoading.value = false
        }
    }


    private var superheroesLoad: MutableLiveData<ArrayList<SuperheroeItem>> = MutableLiveData()
    val onSuperheroesLoaded: LiveData<ArrayList<SuperheroeItem>> = superheroesLoad

    fun getSuperheroesFromServer() {
        viewModelScope.launch(Dispatchers.IO) {
            superheroesLoad.postValue(listRepository.getSuperheroes())
        }
    }

}