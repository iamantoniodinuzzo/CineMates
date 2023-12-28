package com.indisparte.list_creation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.indisparte.media_list.MediaList
import com.indisparte.list.repository.MediaListRepository
import com.indisparte.network.util.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

/**
 *@author Antonio Di Nuzzo
 */
@HiltViewModel
class MediaListCreationViewModel
@Inject
constructor(
    private val mediaListRepository: MediaListRepository,
) : ViewModel() {
    private val LOG = Timber.tag(MediaListCreationViewModel::class.java.simpleName)

    private val _listCreationStatus = MutableStateFlow<Result<Boolean>?>(null)
    val listCreationStatus: StateFlow<Result<Boolean>?> get() = _listCreationStatus

    fun createList(mediaList: MediaList) {
        viewModelScope.launch {
            mediaListRepository.addList(mediaList).collectLatest {
                _listCreationStatus.emit(it)
            }
        }
    }


}