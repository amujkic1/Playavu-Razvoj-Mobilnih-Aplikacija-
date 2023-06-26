package ba.etf.rma23.projekat.data.repositories

import androidx.room.ColumnInfo
import com.google.gson.annotations.SerializedName

data class PostReview(
    @ColumnInfo(name = "review") @SerializedName("review") var review: String,
    @ColumnInfo(name = "rating") @SerializedName("rating") var rating: Int?
)
