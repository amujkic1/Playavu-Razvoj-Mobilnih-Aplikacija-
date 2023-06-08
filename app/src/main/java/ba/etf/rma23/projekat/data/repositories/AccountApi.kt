package ba.etf.rma23.projekat.data.repositories

import ba.etf.rma23.projekat.BuildConfig
import retrofit2.Response
import retrofit2.http.*

interface AccountApi {

    @GET("/account/{aid}/games")
    suspend fun getSavedGames(@Path("aid") accountId: String): Response<List<GameForSaving>>

    @POST("/account/{aid}/game")
    suspend fun saveGame(@Path("aid") accountId: String,
                         @Body game:WrapperGame)

    @DELETE("/account/{aid}/game/{gid}/")
    suspend fun removeGame(
                            @Path("aid") accountId: String = "ca0ee672-440b-45b2-8a12-75b80f4fbdd3",
                            @Path("gid") gameId: Int): DeleteResponse

}