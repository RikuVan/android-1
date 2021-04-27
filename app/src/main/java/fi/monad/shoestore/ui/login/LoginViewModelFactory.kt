package fi.monad.shoestore.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import fi.monad.shoestore.data.LoginRepository


// Required given LoginViewModel has a non-empty constructor
class LoginViewModelFactory : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
            return LoginViewModel(
                    loginRepository = LoginRepository()
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}