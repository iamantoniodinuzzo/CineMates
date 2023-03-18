package com.example.cinemates.repository

import com.example.cinemates.model.Filter
import com.example.cinemates.local.dao.FilterDao
import javax.inject.Inject

class FilterRepository
@Inject
constructor(
    private val filterDao: FilterDao
) {

    fun getFilters() = filterDao.getAll()
   suspend fun insertFilter(filter: Filter) = filterDao.insert(filter)
   suspend fun deleteFilter(filter: Filter) = filterDao.delete(filter)
}