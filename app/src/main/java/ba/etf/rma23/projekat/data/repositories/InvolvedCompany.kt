package ba.etf.rma23.projekat.data.repositories

import com.google.gson.annotations.SerializedName

data class InvolvedCompany(
    @SerializedName("company")
    val comp: Company,
    @SerializedName("developer")
    val dev: Boolean,
    @SerializedName("publisher")
    val pub: Boolean
)
