package ba.etf.rma23.projekat.data.repositories

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

object AccountGamesRepository {

    var accountHash: String = "7636a32b-1170-4dad-b5ab-195a1c77f15c"
    var userAge: Int = 17

    suspend fun getSavedGames():List<Game>{
        return withContext(Dispatchers.IO) {
            var response = AccountApiConfig.retrofit.getSavedGames(accountHash)
            var responseBody = response.body()

            var gameList: ArrayList<Game> = ArrayList()

            if (responseBody != null) {
                for(game in responseBody){
                    var tempLista: List<Game> = ArrayList()
                    tempLista = GamesRepository.getGamesByName(game.name)
                    for (tempGame in tempLista){
                        if (tempGame.id == game.Id){
                            gameList.add(tempGame)
                        }
                    }
                }
            }

            for(game in gameList){
                game.populate()
            }

            return@withContext gameList ?: emptyList()
        }
    }

    suspend fun saveGame(game: Game): Game{
        var gameConvertedToApi: Game = Game(1, "name","", "", 0.1, "", "", "", "", "", "",
            emptyList(), emptyList(), emptyList(), emptyList(), emptyList())
        var tempLista: List<Game> = ArrayList()
        tempLista = GamesRepository.getGamesByName(game.name)
        for (tempGame in tempLista){
            if (tempGame.id == game.id){
                gameConvertedToApi = tempGame
            }
        }
        val gameToSave: GameForSaving = GameForSaving(game.id, game.name)
        val gameWrapper: WrapperGame = WrapperGame(gameToSave)
        return withContext(Dispatchers.IO){
            AccountApiConfig.retrofit.saveGame(accountHash, gameWrapper)
            return@withContext gameConvertedToApi
        }
    }

    suspend fun removeGame(id: Int): Boolean{
        return withContext(Dispatchers.IO){
            AccountApiConfig.retrofit.removeGame(accountHash, id)
            AccountApiConfig.retrofit.removeGame(accountHash, id).equals(DeleteResponse("Games deleted"))
        }
    }


    suspend fun removeNonSafe(): Boolean {

        var response = AccountApiConfig.retrofit.getSavedGames(accountHash)
        var responseBody = response.body()
        var gameList: ArrayList<Game> = ArrayList()

        if (responseBody != null) {
            for (game in responseBody) {
                var tempLista: List<Game> = ArrayList()
                tempLista = GamesRepository.getGamesByName(game.name)
                for (tempGame in tempLista) {
                    if (tempGame.id == game.Id) {
                        gameList.add(tempGame)
                    }
                }
            }
        }
        for (game in gameList) {
            game.populate()
            var ageRating = checkRating(game.esrb_rating.toInt())
            if(ageRating > userAge){
                removeGame(game.id)
            }
        }
        return true
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

    suspend fun getGamesContainingString(query: String): List<Game> {
        var response = AccountApiConfig.retrofit.getSavedGames(accountHash)
        var responseBody = response.body()
        var gamesToReturn: ArrayList<Game> = ArrayList()

        if (responseBody != null) {
            for (game in responseBody) {
                if (game.name?.contains(query) == true) {
                    var tempLista: List<Game> = ArrayList()
                    tempLista = GamesRepository.getGamesByName(game.name)
                    for (tempGame in tempLista) {
                        if (tempGame.id == game.Id) {
                            gamesToReturn.add(tempGame)
                        }
                    }
                }
            }
        }
        return gamesToReturn
    }


    fun setHash(acHash: String): Boolean{
        this.accountHash = acHash
        return true
    }

    fun getHash(): String{
        return accountHash
    }

    fun setAge(age: Int): Boolean{
        if(age<3 || age>100) return false
        userAge=age
        return true
    }

}
