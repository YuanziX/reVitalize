package dev.yuanzix.revitalize.util

sealed class RequestState<out T> {
    data object Idle : RequestState<Nothing>()
    data object Loading : RequestState<Nothing>()

    data class Success<out T>(val data: T) : RequestState<T>()
    data class Error(val exception: Exception) : RequestState<Nothing>()
    data object Empty : RequestState<Nothing>()
}