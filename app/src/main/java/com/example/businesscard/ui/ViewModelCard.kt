package com.example.businesscard

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.businesscard.data.RepositoryBusinessCard
import com.example.businesscard.model.BusinessCard

class ViewModelCard(private val repository: RepositoryBusinessCard) : ViewModel() {

    fun insert(businessCard: BusinessCard) {
        repository.insert(businessCard)
    }

    fun getAll(): LiveData<List<BusinessCard>> {
        return repository.getAll()
    }
}

class ViewModelCardFactory(private val repository: RepositoryBusinessCard) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ViewModelCard::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ViewModelCard(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}