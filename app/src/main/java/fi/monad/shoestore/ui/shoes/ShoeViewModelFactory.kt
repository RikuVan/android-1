package fi.monad.shoestore.ui.shoes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import fi.monad.shoestore.data.ShoesRepository


// Required given LoginViewModel has a non-empty constructor
class ShoeListViewModelFactory : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ShoeListViewModel::class.java)) {
            return ShoeListViewModel(
                shoesRepository = ShoesRepository()
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}