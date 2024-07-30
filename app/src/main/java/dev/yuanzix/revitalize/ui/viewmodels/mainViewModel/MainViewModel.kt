package dev.yuanzix.revitalize.ui.viewmodels.mainViewModel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.yuanzix.revitalize.data.models.TimetableDay
import dev.yuanzix.revitalize.data.repository.DataRepository
import dev.yuanzix.revitalize.data.repository.DataStoreRepository
import dev.yuanzix.revitalize.data.repository.UserCredentials
import dev.yuanzix.revitalize.util.RequestState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val dataRepository: DataRepository,
    private val dataStoreRepository: DataStoreRepository,
) : ViewModel() {

    private val _credentials: MutableStateFlow<UserCredentials> =
        MutableStateFlow(UserCredentials("", ""))

    var credentialsLoaded: MutableState<Boolean> = mutableStateOf(false)

    var todayTimetable: MutableState<RequestState<TimetableDay>> = mutableStateOf(RequestState.Idle)

    private var _attendance: Flow<RequestState<Unit>> = MutableStateFlow(RequestState.Idle)
    val attendance: Flow<RequestState<Unit>> = _attendance

    var exceptionMessage: MutableState<String?> = mutableStateOf(null)

    init {
        fetchAndSetCredentials()
    }

    private fun fetchAndSetCredentials() {
        viewModelScope.launch {
            dataStoreRepository.readUsernameAndPassword.collect {
                _credentials.value = it
                credentialsLoaded.value = true
                getTodayTimetable()
            }
        }
    }

    private fun getTodayTimetable() {
        todayTimetable.value = RequestState.Loading
        viewModelScope.launch {
            try {
                val timetableData = dataRepository.getTodayTimeTableData(
                    userCredentials = _credentials.value, pullNewData = false
                )
                todayTimetable.value = RequestState.Success(timetableData)
            } catch (e: Exception) {
                todayTimetable.value = RequestState.Error(e)
                exceptionMessage.value = e.message
            }
        }
    }

    fun updateAllData() {
        if (credentialsLoaded.value) {
            viewModelScope.launch {
                try {
                    dataRepository.refreshAllData(_credentials.value)
                } catch (e: Exception) {
                    exceptionMessage.value = e.message
                }
            }
        }
    }

    fun clearException() {
        exceptionMessage.value = null
    }
}
