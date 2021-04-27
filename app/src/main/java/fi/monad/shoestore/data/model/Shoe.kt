package fi.monad.shoestore.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlin.random.Random

@Parcelize
data class Shoe(var id: Int?, var name: String, var size: Double, var company: String, var description: String,
                val images: List<String> = mutableListOf()) : Parcelable


fun Shoe.getId(): Int {
    return (1..12).shuffled().first()
}