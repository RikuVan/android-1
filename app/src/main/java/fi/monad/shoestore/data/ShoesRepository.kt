package fi.monad.shoestore.data

import fi.monad.shoestore.data.model.Shoe

class ShoesRepository {

    private val shoes = mutableListOf<Shoe>()

    init {
        repeat(14) {
            shoes += Shoe(
                id = it + 1,
                company = "Monad shoes",
                name = "Men's ballroom",
                size = it + 1.0,
                description = "Top quality ballroom dancing shoe made from the best leather for dancer's who want comfort, stability, and style.",
                images = listOf("shoes")
            )
        }
    }

    fun getShoes(): List<Shoe> {
        return shoes.toList()
    }
}