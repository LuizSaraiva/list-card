package com.example.businesscard.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity()
data class BusinessCard(
    val name: String,
    val phone: String,
    val email: String,
    val company: String,
    val backgroundCustom: String?,
    @PrimaryKey(autoGenerate = true) val id: Int = 0
)
