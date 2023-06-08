package ba.etf.rma23.projekat.data.repositories

import com.google.gson.annotations.SerializedName

data class Platform(
    @SerializedName("name")
    val name: String?
)
