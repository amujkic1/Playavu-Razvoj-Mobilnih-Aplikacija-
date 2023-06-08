package ba.etf.rma23.projekat.data.repositories

import ba.etf.rma23.projekat.BuildConfig
import retrofit2.Response
import retrofit2.http.*

interface IGDBApi {

    @Headers(
        value = ["Client-ID: 44z88tqoevkhyqgh8rmwdffciz8nn3",
            "Authorization: Bearer " + BuildConfig.CLIENT_TOKEN]
    )
    @GET("games/")
    suspend fun getGamesByName(@Query("search") name: String?,
                               @Query("fields") fields: String = "id, name, platforms.name, release_dates.human, " +
                                        "rating, cover.url, age_ratings.rating, genres.name, involved_companies.publisher, involved_companies.developer, involved_companies.company.name, summary"): Response<List<Game>>

    @GET("games/")
    suspend fun sortGames(@Query("search") name: String,
                               @Query("fields") fields: String = "id, name, platforms.name, release_dates.human, " +
                                       "rating, cover.url, genres.name, involved_companies.publisher, involved_companies.developer, involved_companies.company.name, summary, age_ratings"): Response<List<Game>>


}