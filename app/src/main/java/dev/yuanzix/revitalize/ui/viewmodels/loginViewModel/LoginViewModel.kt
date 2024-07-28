package dev.yuanzix.revitalize.ui.viewmodels.loginViewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.yuanzix.revitalize.data.api.API
import dev.yuanzix.revitalize.data.models.CredentialStatus
import dev.yuanzix.revitalize.data.repository.DataStoreRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val api: API,
    private val dataStoreRepository: DataStoreRepository
) : ViewModel() {
    var username by mutableStateOf("")
    var password by mutableStateOf("")
    var loginState by mutableStateOf(LoginStates.Login)

    private fun validateInputs(): Boolean {
        return if (username.isBlank() || password.isBlank()) {
            loginState = LoginStates.BlankFields
            false
        } else {
            true
        }
    }

    fun handleState() {
        loginState = when (loginState) {
            LoginStates.CredentialsIncorrect -> LoginStates.Login
            LoginStates.SomethingWentWrong -> LoginStates.Login
            LoginStates.BlankFields -> LoginStates.Login
            else -> return
        }
    }

    fun handleLogin() {
        if (!validateInputs()) return

        loginState = LoginStates.Loading
        viewModelScope.launch(Dispatchers.IO) {
            val validity = api.verifyCredentials(username, password)
            when (validity) {
                CredentialStatus.Correct -> {
                    dataStoreRepository.persistUsernameAndPassword(username, password)
                    loginState = LoginStates.Success
                }
                CredentialStatus.Incorrect -> loginState = LoginStates.CredentialsIncorrect
                CredentialStatus.SomethingWentWrong -> loginState = LoginStates.SomethingWentWrong
            }
        }
    }
}
