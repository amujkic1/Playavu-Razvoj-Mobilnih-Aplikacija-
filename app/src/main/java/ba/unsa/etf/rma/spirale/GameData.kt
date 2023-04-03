package ba.unsa.etf.rma.spirale

class GameData {
    companion object GameData {
        private val games = listOf(
            Game(
                title = "The Legend of Zelda: Breath of the Wild",
                platform = "Nintendo Switch",
                releaseDate = "March 3, 2017",
                rating = 9.5,
                coverImage = "https://www.example.com/zelda-cover.jpg",
                esrbRating = "E10+",
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
            ),
            Game(
                title = "Super Mario Odyssey",
                platform = "Nintendo Switch",
                releaseDate = "October 27, 2017",
                rating = 9.3,
                coverImage = "https://www.example.com/mario-cover.jpg",
                esrbRating = "E10+",
                developer = "Nintendo EPD",
                publisher = "Nintendo",
                genre = "Platformer",
                description = "Super Mario Odyssey is a platformer game where players control Mario as he travels across various worlds to rescue Princess Peach from Bowser.",
                userImpressions = listOf(
                    UserRating(username = "Lisa321", timestamp = 1648736400L, rating = 9.5),
                    UserReview(username = "Mike444", timestamp = 1648851600L, review = "This game is so much fun! The worlds are beautiful and the gameplay is smooth."),
                    UserRating(username = "Jen999", timestamp = 1648966800L, rating = 8.0),
                    UserReview(username = "David567", timestamp = 1649082000L, review = "I really enjoyed playing this game, but some parts were a bit too difficult for me."),
                    UserRating(username = "Karen777", timestamp = 1649197200L, rating = 9.0)
                )
            ),
            Game(
                title = "Red Dead Redemption 2",
                platform = "PlayStation 4",
                releaseDate = "October 26, 2018",
                rating = 9.8,
                coverImage = "https://www.example.com/red-dead-redemption-2-cover.jpg",
                esrbRating = "M",
                developer = "Rockstar Studios",
                publisher = "Rockstar Games",
                genre = "Action-adventure",
                description = "Red Dead Redemption 2 is an action-adventure game set in an open world environment where players take on the role of Arthur Morgan, a member of the Van der Linde gang, as he navigates the dying West while trying to survive against rival gangs and government forces.",
                userImpressions = listOf(
                    UserRating(username = "Ben456", timestamp = 1648736400L, rating = 10.0),
                    UserReview(username = "Julia789", timestamp = 1648851600L, review = "This game is a masterpiece! The story, characters, and world-building are incredible."),
                    UserReview(username = "Jack101", timestamp = 1648966800L, review = "I've been playing this game for hours and I'm still not bored. The attention to detail is mind-blowing."),
                    UserRating(username = "Sam222", timestamp = 1649197200L, rating = 9.5)
                )
            ),

            Game(
                title = "Overwatch",
            platform = "PC",
            releaseDate = "May 24, 2016",
            rating = 9.2,
            coverImage = "https://www.example.com/overwatch-cover.jpg",
            esrbRating = "T",
            developer = "Blizzard Entertainment",
            publisher = "Blizzard Entertainment",
            genre = "First-person shooter",
            description = "Overwatch is a team-based first-person shooter set in a futuristic world where players choose from a roster of heroes, each with their own unique abilities and playstyles, to battle against enemy teams and complete objectives.",
            userImpressions = listOf(
                UserRating(username = "Eva456", timestamp = 1648736400L, rating = 8.5),
                UserReview(username = "Max789", timestamp = 1648966800L, review = "I love the variety of heroes and the team dynamics in this game. It's always a blast to play with friends."),
                UserRating(username = "Gina101", timestamp = 1649197200L, rating = 9.0)
            )
        ),
            Game(
                title = "The Witcher 3: Wild Hunt",
                platform = "PlayStation 4",
                releaseDate = "May 19, 2015",
                rating = 9.8,
                coverImage = "https://www.example.com/witcher-3-cover.jpg",
                esrbRating = "M",
                developer = "CD Projekt Red",
                publisher = "CD Projekt",
                genre = "Action role-playing",
                description = "The Witcher 3: Wild Hunt is an action role-playing game set in a massive open world environment. Players take on the role of Geralt of Rivia, a monster hunter, as he embarks on a journey to find his adopted daughter and unravel a mysterious plot that threatens the Northern Kingdoms.",
                userImpressions = listOf(
                    UserRating(username = "Maggie123", timestamp = 1648736400L, rating = 10.0),
                    UserReview(username = "Luke456", timestamp = 1648851600L, review = "The Witcher 3 is a masterpiece. The story, characters, and world-building are all top-notch."),
                    UserRating(username = "Nick789", timestamp = 1649197200L, rating = 9.5)
                )
            ),


        )

        private val impressions = games.associateBy({ it.title }, { it.userImpressions })

        fun getUserImpressions(): List<UserImpression>{
            return listOf(games[0].userImpressions[0], games[0].userImpressions[1], games[0].userImpressions[2], games[0].userImpressions[3], games[0].userImpressions[4],).sortedByDescending { it.timestamp }
        }

        fun getAll(): List<Game> {
            return games
        }

        fun getDetails(title: String): Game? {
            return games.find { it.title == title }
        }

        fun getImpressions(title: String): List<UserImpression>? {
            return impressions[title]
        }
    }
}