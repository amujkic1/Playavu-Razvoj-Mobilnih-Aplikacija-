package ba.etf.rma23.projekat.data.repositories

import ba.etf.rma23.projekat.data.repositories.AccountGamesRepository.userAge
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

object GamesRepository {

    var gamesToSort: ArrayList<Game> = ArrayList()

    suspend fun getGamesByName(name: String?):List<Game>{
        return withContext(Dispatchers.IO) {
            var response = IGDBApiConfig.retrofit.getGamesByName(name)
            val responseBody = response.body()
            if (responseBody != null) {
                for(game in responseBody){
                    game.populate()
                    gamesToSort.add(game)
                }
            }
            return@withContext responseBody!!
        }
    }

    suspend fun sortGames(): List<Game> {
        val savedGames = AccountGamesRepository.getSavedGames()
        val currentlyDisplayedGames: List<Game> = gamesToSort
        if (currentlyDisplayedGames.isEmpty())
            return sortAZ(savedGames)

        val favoritesDisplayed: ArrayList<Game> = ArrayList()
        val nonFavoritesDisplayed: ArrayList<Game> = ArrayList(currentlyDisplayedGames)

        for (game in currentlyDisplayedGames) {
            for (savedGame in savedGames) {
                if (game.id == savedGame.id) {
                    favoritesDisplayed.add(game)
                }
            }
        }

        val gamesToRemove: ArrayList<Game> = ArrayList()
        for (game in currentlyDisplayedGames) {
            if (favoritesDisplayed.contains(game)) {
                gamesToRemove.add(game)
            }
        }

        nonFavoritesDisplayed.removeAll(gamesToRemove)

        return sortAZ(favoritesDisplayed) + sortAZ(nonFavoritesDisplayed)
    }


    fun sortAZ(list: List<Game>): List<Game>{
        return list.sortedBy { it.name }
    }

    suspend fun getGamesSafe(name: String?): List<Game> {
        val safeGames: ArrayList<Game> = getGamesByName(name) as ArrayList<Game>
        val iterator = safeGames.iterator()

        if(userAge==0){
            return emptyList()
        }

        while (iterator.hasNext()) {
            val game = iterator.next()

            val ratingAge: Int = if (game.esrb_rating == null) {
                -1
            } else {
                val rating = game.esrb_rating.toInt()
                checkRating(rating)
            }

            if (ratingAge > AccountGamesRepository.userAge) {
                iterator.remove()
            }
        }

        return safeGames
    }

    private fun checkRating(rating: Int): Int{
        if(rating==1 || rating==6) return 3
        if(rating==7) return 6
        if (rating==2) return 7
        if(rating==8) return 10
        if(rating==3) return 12
        if(rating==9) return 13
        if (rating==4) return 16
        if(rating==10) return 17
        if (rating==5 || rating==11) return 18
        else return -1
    }

}

