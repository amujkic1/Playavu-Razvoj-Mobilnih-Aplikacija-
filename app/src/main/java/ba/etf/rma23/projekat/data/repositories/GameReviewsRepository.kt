package ba.etf.rma23.projekat.data.repositories

import android.annotation.SuppressLint
import android.content.Context
import android.net.ConnectivityManager
import androidx.test.core.app.ApplicationProvider
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.net.UnknownHostException

object GameReviewsRepository {

    suspend fun getOfflineReviews(context: Context): List<GameReview> {
        return withContext(Dispatchers.IO) {
            var db = AppDatabase.getInstance(context)
            var gameReviews = db!!.gameDao().getOffline()
            return@withContext gameReviews ?: emptyList()
        }
    }

    suspend fun sendOfflineReviews(context: Context): Int{
        val offline = getOfflineReviews(context)
        var count = 0
        for(review in offline){
            if(review.rating in 0..5 && (review.online == false || review.online == null)) {
                //zamijeni hash
                AccountApiConfig.retrofit.sendReviewToWeb(AccountGamesRepository.accountHash,
                                                    review.igdbID, PostReview(review.review, review.rating))
                count++
                //gameDAO.updateStatusToOnline()
                var db = AppDatabase.getInstance(context)
                db.gameDao().updateStatusToOnline()
                review.online = true
            }
        }
        //database.close()
        return count
    }

    suspend fun getReviewsForGame(igdb_id:Int):List<GameReview> {
        return withContext(Dispatchers.IO){
            var response = AccountApiConfig.retrofit.getReviewsForGame(igdb_id)
            return@withContext response.body() ?: emptyList()
        }
    }

    suspend fun sendReview(context: Context, gameReview: GameReview):Boolean {
        /*if(isConnectedToNetwork(context)!=true){
            gameReview.online = false
            writeIfNotSentToWeb(context, gameReview)
            return false
        }*/
        try{
            val savedGames = AccountGamesRepository.getSavedGames()
            val gameIdExists = savedGames.any { it.id == gameReview.igdbID }
            if (!gameIdExists) {
                val game = getById(gameReview.igdbID)[0]
                if (game != null) AccountGamesRepository.saveGame(game)
            }

            val reviewRequest = PostReview(gameReview.review, gameReview.rating)
            AccountApiConfig.retrofit.sendReviewToWeb(AccountGamesRepository.accountHash,
                gameReview.igdbID, reviewRequest)
        }catch(ue: UnknownHostException){
            gameReview.online = false
            writeIfNotSentToWeb(context, gameReview)
            return false
        }
        catch (e: Exception){
            gameReview.online = false
            writeIfNotSentToWeb(context, gameReview)
            return false
        }
        return true
    }


    private fun isConnectedToNetwork(context: Context): Boolean {
        val connectivityManager = context?.getSystemService(Context.CONNECTIVITY_SERVICE) as? ConnectivityManager
        val networkInfo = connectivityManager?.activeNetworkInfo
        return networkInfo?.isConnected == false
    }

    suspend fun writeIfNotSentToWeb(context: Context, gameReview: GameReview) : String?{
        return withContext(Dispatchers.IO) {
            try{
                var db = AppDatabase.getInstance(context)
                db!!.gameDao().insertAll(gameReview)
                return@withContext "success"
            }
            catch(error:Exception){
                return@withContext null
            }
        }
    }

    suspend fun getById(id: Int): List<Game> {
        return withContext(Dispatchers.IO){
            var response = IGDBApiConfig.retrofit.getById(id)
            val responseBody = response.body()
            if (responseBody != null) {
                for(game in responseBody){
                    game.populate()
                }
            }
            return@withContext responseBody!!
        }
    }

}