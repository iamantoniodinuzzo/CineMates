package com.example.cinemates.model.repository

import com.example.cinemates.model.entities.Filter
import com.example.cinemates.model.local.dao.FilterDao
import com.example.cinemates.model.local.db.AppDatabase
import javax.inject.Inject

class DbFilterRepository
@Inject
constructor(appDatabase: AppDatabase) {
    companion object {
        private lateinit var filterDao: FilterDao
    }

    init {
        filterDao = appDatabase.filterDao()
    }

    fun getFilters() = filterDao.getAll()
    fun insertFilter(filter: Filter) = filterDao.insert(filter)
    fun deleteFilter(filter: Filter) = filterDao.delete(filter)
}