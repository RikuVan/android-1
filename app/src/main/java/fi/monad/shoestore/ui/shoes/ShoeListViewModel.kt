package fi.monad.shoestore.ui.shoes

import android.text.Editable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import fi.monad.shoestore.data.ShoesRepository
import fi.monad.shoestore.data.model.Shoe
import fi.monad.shoestore.data.model.getId

typealias ShoeList = List<Shoe>

val shoeTemplate = Shoe(
    id = null,
    name = "",
    size = 0.0,
    company = "",
    description = "",
    images = emptyList()
)

class ShoeListViewModel(private val shoesRepository: ShoesRepository) : ViewModel() {
    private val _shoes = MutableLiveData<ShoeList>(emptyList())
    val shoes: LiveData<ShoeList> = _shoes
    // for details view
    private val _current = MutableLiveData<Shoe>()
    val current: LiveData<Shoe> = _current
    // for add shoes view
    private val _newShoes = MutableLiveData<Shoe>(shoeTemplate)
    val newShoes: LiveData<Shoe> = _newShoes

    init {
        _shoes.value = shoesRepository.getShoes()
    }

    fun setCurrent(shoe: Shoe) {
        _current.value = shoe
    }

    fun addShoes() {
        val copy = _shoes.value!!.toMutableList()
        _newShoes.value?.apply {
            id = this.getId()
        }
        copy.add(_newShoes.value as Shoe)
        _shoes.value = copy.toList()
        // reset form
        _newShoes.value = shoeTemplate
    }

    fun clearShoes() {
        _newShoes.value = shoeTemplate
    }

    fun setName(n: Editable) {
        _newShoes.value?.name = n.toString()
    }

    fun setDescription(n: Editable) {
        _newShoes.value?.description = n.toString()
    }

    fun setSize(s: Editable) {
        _newShoes.value?.size = s.toString().let {
            if(it.isNullOrEmpty()) 0.0 else it.toDouble()
        }
    }

    fun setCompany(c: Editable) {
        _newShoes.value?.company = c.toString()
    }
}