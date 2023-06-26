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
                            @Path("aid") accountId: String = AccountGamesRepository.accountHash,
                            @Path("gid") gameId: Int): DeleteResponse

    @GET("/game/{gid}/gamereviews")
    suspend fun getReviewsForGame(@Path("gid") gameId: Int): Response<List<GameReview>>

    @POST("/account/{aid}/game/{gid}/gamereview")
    suspend fun sendReviewToWeb(@Path("aid") accountId: String,
                                  @Path("gid") gameId: Int,
                                  @Body gameReview: PostReview)

}