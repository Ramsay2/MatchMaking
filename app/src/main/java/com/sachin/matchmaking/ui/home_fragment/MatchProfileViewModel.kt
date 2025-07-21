package com.sachin.matchmaking.ui.home_fragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.sachin.matchmaking.data.db.MatchProfileDbEntity
import com.sachin.matchmaking.domain.usecase.GetMatchProfileUseCase
import com.sachin.matchmaking.domain.usecase.IsLocalDataEmptyUseCase
import com.sachin.matchmaking.domain.usecase.UpdateProfileUseCase
import com.sachin.matchmaking.ui.adapter.entity.MatchProfileEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MatchProfileViewModel @Inject constructor(
    private val useCase: GetMatchProfileUseCase,
    private val updateProfileUseCase: UpdateProfileUseCase,
    private val isLocalDataAvailable: IsLocalDataEmptyUseCase,
) : ViewModel() {

    private val data = MutableStateFlow<PagingData<MatchProfileDbEntity>?>(null)
    val _data: StateFlow<PagingData<MatchProfileDbEntity>?> = data

    var onNavigateToSelectedProfiles: (() -> Unit)? = null
    private val selectedButtonVisibility = MutableStateFlow(false)
    val _selectedButtonVisibility: StateFlow<Boolean> = selectedButtonVisibility

    val selectedProfiles = mutableMapOf<String, MatchProfileEntity>()

    init {
        isLocalDataEmpty()
    }

     fun getData(gender: String = "") {
        viewModelScope.launch {
            useCase.invoke(gender).cachedIn(this).collectLatest {
                data.value = it
            }
        }
    }

    fun updateProfile(profile: MatchProfileDbEntity) {
        viewModelScope.launch {
            updateProfileUseCase.invoke(profile)
        }
        selectedButtonVisibility.value = true
    }

    fun onSelectProfileClicked() {
        onNavigateToSelectedProfiles?.invoke()
    }

    private fun isLocalDataEmpty() {
        viewModelScope.launch(Dispatchers.IO) {
            isLocalDataAvailable.invoke().collectLatest {
                selectedButtonVisibility.value = it
            }
        }
    }
}