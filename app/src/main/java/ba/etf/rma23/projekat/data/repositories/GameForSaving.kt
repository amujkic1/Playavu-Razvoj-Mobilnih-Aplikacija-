package ba.etf.rma23.projekat.data.repositories

import com.google.gson.annotations.SerializedName

data class GameForSaving(
    @SerializedName("igdb_id")
    val Id: Int,
    @SerializedName("name")
    val name: String?
)
