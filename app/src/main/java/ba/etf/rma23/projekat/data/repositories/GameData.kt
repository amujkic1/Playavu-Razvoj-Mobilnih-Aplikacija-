package ba.etf.rma23.projekat.data.repositories

class GameData {
    companion object GameData {
        private val games = listOf(
            Game(
                id = 1,
                name = "The Legend of Zelda: Breath of the Wild",
                platform = "Nintendo Switch",
                releaseDate = "March 3, 2017",
                rating = 9.5,
                cover_img = "https://images.igdb.com/igdb/image/upload/t_thumb/xgk5cjwzccp0g20ssd0k.jpg",
                esrb_rating = "E10+",
                developer = "Nintendo EPD",
                publisher = "Nintendo",
                genre = "Action-adventure",
                description = "The Legend of Zelda: Breath of the Wild is an action-adventure game set in an open world environment where players are tasked with exploring the kingdom of Hyrule while battling enemies and solving puzzles.",
                userImpressions = listOf(
                    UserRating(username = "John123", timestamp = 1648736400L, rating = 9.0),
                    UserRating(username = "Sara456", timestamp = 1648851600L, rating = 10.0),
                    UserReview(username = "Tom789", timestamp = 1648966800L, review = "This game is amazing! The open world is huge and there's so much to explore."),
                    UserReview(username = "Amy101", timestamp = 1649082000L, review = "I love this game so much! The puzzles are challenging but fun."),
                    UserRating(username = "Chris222", timestamp = 1649197200L, rating = 8.5)
                )
        ))


        fun getUserImpressions(title: String): List<UserImpression>{
            val game_instance : Game?
            game_instance = getDetails(title)
            val returnList = mutableListOf<UserImpression>()

            if (game_instance != null) {
                for(impression in game_instance.userImpressions){
                    returnList.add(impression)
                }
            }

            return returnList.sortedByDescending { it.timestamp }
        }

        fun getAll(): List<Game> {
            return games
        }

        fun getDetails(title: String): Game? {
            return games.find { it.name == title }
        }

    }

}