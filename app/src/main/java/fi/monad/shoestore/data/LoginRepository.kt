package fi.monad.shoestore.data

import fi.monad.shoestore.data.model.User


class LoginRepository {

    // in-memory cache of the loggedInUser object
    var user: User? = null
        private set

    val isLoggedIn: Boolean
        get() = user != null

    init {
        user = null
    }

    fun logout() {
        user = null
    }

    fun login(email: String, isNew: Boolean) {
        this.user = User(email, isNew)
    }
}