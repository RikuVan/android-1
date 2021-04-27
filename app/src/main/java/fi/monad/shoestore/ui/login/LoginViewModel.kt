package fi.monad.shoestore.ui.login

import android.text.Editable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import fi.monad.shoestore.data.LoginRepository
import kotlin.math.E

const val EMAIL_ERROR = "Invaid email"
const val PASSWORD_ERROR = "Password too short"

class LoginViewModel(private val loginRepository: LoginRepository) : ViewModel() {

    private val _email = MutableLiveData("")
    val email: LiveData<String> = _email

    private val _password = MutableLiveData("")
    val password: LiveData<String> = _password

    private val _error = MutableLiveData("")
    val error: LiveData<String> = _error

    fun setEmail(email: Editable) {
        _email.value = email.toString()
        validate()
    }

    fun setPassword(password: Editable) {
        _password.value = password.toString()
        validate()
    }

    fun handleLogin(isNew: Boolean) {
        if (!isEmailValid(_email.value)) {
            _error.value = "Invaid email"
        } else if (!isPasswordValid(_password.value)) {
            _error.value = "Password too short"
        } else {
            loginRepository.login(email.value as String, isNew)
        }
    }

    private fun validate() {
        if (!isEmailValid(_email.value)) _error.value = EMAIL_ERROR
        else if (!isPasswordValid(_password.value)) _error.value = PASSWORD_ERROR
        else _error.value = ""
    }

    private fun isPasswordValid(password: String?): Boolean {
        return password?.let {
            it.length >= 5
        } ?: false
    }

    private fun isEmailValid(email: String?): Boolean {
        return !email.isNullOrBlank() && email.contains('@')
    }

}