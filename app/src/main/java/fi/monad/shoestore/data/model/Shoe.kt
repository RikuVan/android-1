package fi.monad.shoestore.data.model

import android.graphics.drawable.Drawable
import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlin.random.Random

@Parcelize
data class Shoe(var id: Int?, var name: String, var size: Double, var company: String, var description: String,
                var images: List<Int> = mutableListOf()) : Parcelable


fun Shoe.getId(): Int {
    return (1..12).shuffled().first()
}