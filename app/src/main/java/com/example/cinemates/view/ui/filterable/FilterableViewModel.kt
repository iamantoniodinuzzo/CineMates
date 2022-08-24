package com.example.cinemates.view.ui.filterable

import androidx.lifecycle.ViewModel
import com.example.cinemates.model.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


private const val TAG = "FilterableViewModel"
/**
 * @author Antonio Di Nuzzo
 * Created 24/08/2022
 */
@HiltViewModel
class FilterableViewModel
@Inject
constructor(private val movieRepository: MovieRepository) : ViewModel() {



}