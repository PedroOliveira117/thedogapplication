package com.example.thedog.model.data

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.thedog.R
import com.example.thedog.TheDogApplication
import java.io.Serializable

@Entity(tableName = "dog_database")
data class Dog(
    val breed_group: String?,
    @PrimaryKey
    val id: Int,
    @Embedded(prefix = "image_")
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