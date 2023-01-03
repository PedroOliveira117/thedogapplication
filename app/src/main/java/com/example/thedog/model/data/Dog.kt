package com.example.thedog.model.data

import android.content.Context
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.thedog.R
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

fun Dog.breedNameDisplay(context: Context): String {
    return if (name?.isNotEmpty() == true) {
        name
    } else {
        context.resources.getText(R.string.dog_property_none).toString()
    }
}

fun Dog.breedGroupDisplay(context: Context): String {
    return if (breed_group?.isNotEmpty() == true) {
        breed_group
    } else {
        context.getText(R.string.dog_property_none).toString()
    }
}

fun Dog.originDisplay(context: Context): String {
    return if (origin?.isNotEmpty() == true) {
        origin
    } else {
        context.getText(R.string.dog_property_none).toString()
    }
}

fun Dog.temperamentDisplay(context: Context): String {
    return if (temperament?.isNotEmpty() == true) {
        temperament
    } else {
        context.getText(R.string.dog_property_none).toString()
    }
}