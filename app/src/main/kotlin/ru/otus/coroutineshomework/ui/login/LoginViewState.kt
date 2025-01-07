package ru.otus.coroutineshomework.ui.login

import ru.otus.coroutineshomework.ui.login.data.User

sealed class LoginViewState {
    data class Login(val error: Exception? = null) : LoginViewState()
    data object LoggingIn : LoginViewState()
    data class Content(val user: User) : LoginViewState()
    data object LoggingOut : LoginViewState()
}