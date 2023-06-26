package ba.etf.rma23.projekat.data.repositories

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface GameDAO {
    @Query("SELECT * FROM gamereview")
    suspend fun getAll(): List<GameReview>?
    @Query("SELECT * FROM gamereview where online=false")
    suspend fun getOffline(): List<GameReview>?
    @Query("UPDATE gamereview SET online = true WHERE online=false")
    suspend fun updateStatusToOnline()
    @Insert
    suspend fun insertAll(vararg games: GameReview)
    @Query("DELETE FROM gamereview")
    suspend fun deleteAll()
}