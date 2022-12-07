package com.example.thedog.common

/**
 * Created by pedrooliveira on 07/12/2022
 * All rights reserved GoodBarber
 */
sealed class Resource<T>(val data: T? = null, val message: String? = null) {
    class Success<T>(data: T?): Resource<T>(data)
    class Error<T>(message: String, data: T? = null): Resource<T>(data, message)
}
