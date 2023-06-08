package ba.etf.rma23.projekat.data.repositories

import com.google.gson.annotations.SerializedName
import java.net.URL

data class Cover(
    @SerializedName("url")
    val url: String?
)
