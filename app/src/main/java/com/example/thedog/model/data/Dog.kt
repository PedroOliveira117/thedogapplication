package com.example.thedog.model.data

data class Dog(
    val breed_group: String,
    val id: Int,
    val image: Image,
    val name: String,
    val origin: String,
    val temperament: String,
)