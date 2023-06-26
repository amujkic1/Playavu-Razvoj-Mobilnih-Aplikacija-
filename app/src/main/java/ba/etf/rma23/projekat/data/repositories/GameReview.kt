package ba.etf.rma23.projekat.data.repositories

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class GameReview(
    @PrimaryKey @SerializedName("id") var id: Int,
    @ColumnInfo(name = "review") @SerializedName("review") var review: String,
    @ColumnInfo(name = "rating") @SerializedName("rating") var rating: Int,
    @ColumnInfo(name = "igdbID") var igdbID: Int,
    @ColumnInfo(name = "online") var online: Boolean?,
    @ColumnInfo(name = "timestamp") var timestamp: String?,
    @ColumnInfo(name = "student") var student: String
    )
