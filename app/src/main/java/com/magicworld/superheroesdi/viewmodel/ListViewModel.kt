package com.magicworld.superheroesdi.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.magicworld.superheroesdi.model.SuperheroeItem
import com.magicworld.superheroesdi.repository.ListRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(private val listRepository: ListRepository) : ViewModel() {

    private var superheroesLoad: MutableLiveData<ArrayList<SuperheroeItem>> = MutableLiveData()
    val onSuperheroesLoaded: LiveData<ArrayList<SuperheroeItem>> = superheroesLoad

    fun getSuperheroesFromServer() {
        viewModelScope.launch(Dispatchers.IO) {
            superheroesLoad.postValue(listRepository.getSuperheroes())
        }
    }

}