package com.example.cinemates.repository

import com.example.cinemates.model.Filter
import com.example.cinemates.local.dao.FilterDao
import com.example.cinemates.local.db.AppDatabase
import javax.inject.Inject

class DbFilterRepository
@Inject
constructor(
    private val filterDao: FilterDao
) {

    fun getFilters() = filterDao.getAll()
    fun insertFilter(filter: Filter) = filterDao.insert(filter)
    fun deleteFilter(filter: Filter) = filterDao.delete(filter)
}