package ru.otus.coroutineshomework.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.otus.coroutineshomework.ui.login.data.Credentials

class LoginViewModel : ViewModel() {

    private val _state = MutableLiveData<LoginViewState>(LoginViewState.Login())
    val state: LiveData<LoginViewState> = _state

    /**
     * Login to the network
     * @param name user name
     * @param password user password
     */
    fun login(name: String, password: String) {
        _state.value = LoginViewState.LoggingIn
        viewModelScope.launch(Dispatchers.IO) {
            runCatching {
                LoginApi().login(
                    Credentials(
                        name,
                        password
                    )
                )
            }.onFailure {
                launch(Dispatchers.Main) {
                    _state.value = LoginViewState.Login(error = it as Exception)
                }
            }.onSuccess {
                launch(Dispatchers.Main) { _state.value = LoginViewState.Content(it) }
            }
        }
    }

    /**
     * Logout from the network
     */
    fun logout() {
        _state.value = LoginViewState.LoggingOut
        viewModelScope.launch(Dispatchers.IO) {
            LoginApi().logout()
        }
        _state.value = LoginViewState.Login()
    }
}
