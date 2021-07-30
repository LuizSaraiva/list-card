package com.example.businesscard

import android.app.Application
import com.example.businesscard.data.DatabaseApp
import com.example.businesscard.data.RepositoryBusinessCard

class App : Application() {

    val database by lazy { DatabaseApp.getDatabase(this) }
    val repository by lazy { RepositoryBusinessCard(database.businessDao()) }
}