package com.example.businesscard.data

import com.example.businesscard.data.DaoBusinessCard
import com.example.businesscard.model.BusinessCard
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class RepositoryBusinessCard(private val dao: DaoBusinessCard) {

    fun insert(businessCard: BusinessCard) = runBlocking {
        launch(Dispatchers.IO) {
            dao.insert(businessCard)
        }
    }
    fun getAll() = dao.getAll()
}