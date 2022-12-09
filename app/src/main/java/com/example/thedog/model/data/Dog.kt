package com.example.thedog.model.data

import com.example.thedog.R
import com.example.thedog.TheDogApplication
import java.io.Serializable

data class Dog(
    val breed_group: String?,
    val id: Int,
    val image: Image?,
    val name: String?,
    val origin: String?,
    val temperament: String?,
) : Serializable

fun Dog.breedNameDisplay(): String {
    return if (name?.isNotEmpty() == true) {
        name
    } else {
        TheDogApplication.initialize.resources.getText(R.string.dog_property_none).toString()
    }
}

fun Dog.breedGroupDisplay(): String {
    return if (breed_group?.isNotEmpty() == true) {
        breed_group
    } else {
        TheDogApplication.initialize.resources.getText(R.string.dog_property_none).toString()
    }
}

fun Dog.originDisplay(): String {
    return if (origin?.isNotEmpty() == true) {
        origin
    } else {
        TheDogApplication.initialize.resources.getText(R.string.dog_property_none).toString()
    }
}

fun Dog.temperamentDisplay(): String {
    return if (temperament?.isNotEmpty() == true) {
        temperament
    } else {
        TheDogApplication.initialize.resources.getText(R.string.dog_property_none).toString()
    }
}