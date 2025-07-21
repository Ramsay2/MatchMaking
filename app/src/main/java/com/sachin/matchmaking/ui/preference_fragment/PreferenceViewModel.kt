package com.sachin.matchmaking.ui.preference_fragment

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sachin.matchmaking.domain.usecase.GetSelectedProfiles
import com.sachin.matchmaking.ui.adapter.entity.MatchProfileEntity
import com.sachin.matchmaking.ui.adapter.mapper.toMatchProfileEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PreferenceViewModel @Inject constructor(
    private val getMatchPersonUseCase: GetSelectedProfiles,
) : ViewModel() {

    private val _gender = MutableLiveData<String>()
    val gender: LiveData<String> = _gender

    private val _data = MutableLiveData<List<MatchProfileEntity>>()
    val data: LiveData<List<MatchProfileEntity>> = _data

    fun getSelectedProfiles() {
        viewModelScope.launch {
            getMatchPersonUseCase.invoke().collectLatest {
                _data.value = it.map { it.toMatchProfileEntity() }
            }
        }
    }
}