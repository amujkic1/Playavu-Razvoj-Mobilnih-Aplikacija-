package ba.unsa.etf.rma.spirale

class GameData {
    companion object GameData {
        private val games = listOf(
            Game(
                title = "The Legend of Zelda: Breath of the Wild",
                platform = "Nintendo Switch",
                releaseDate = "March 3, 2017",
                rating = 9.5,
                coverImage = "zelda",
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
                coverImage = "supermario",
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
                coverImage = "redemption",
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
            coverImage = "overwatch",
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
                coverImage = "witcher",
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

            Game(
                title = "Assassin's Creed Valhalla",
                platform = "PlayStation 5",
                releaseDate = "November 10, 2020",
                rating = 8.5,
                coverImage = "assassins_creed",
                esrbRating = "M",
                developer = "Ubisoft Montreal",
                publisher = "Ubisoft",
                genre = "Action-adventure",
                description = "Assassin's Creed Valhalla is an action-adventure game set in a historical open-world environment where players control Eivor, a Viking raider, as they lead their clan from Norway to England to settle in a new land and fight against rival factions.",
                userImpressions = listOf(
                    UserRating(username = "Emma123", timestamp = 1648736400L, rating = 8.0),
                    UserReview(username = "Leo456", timestamp = 1648851600L, review = "The graphics and attention to detail are amazing. The story is intriguing and the combat is fun."),
                    UserRating(username = "Nora789", timestamp = 1648966800L, rating = 9.0),
                    UserReview(username = "Oscar101", timestamp = 1649082000L, review = "I love the Viking setting and the exploration aspect of the game. The side quests are also really engaging."),
                    UserRating(username = "Paul222", timestamp = 1649197200L, rating = 8.5)
                )
            ),
            Game(
                title = "Animal Crossing: New Horizons",
                platform = "Nintendo Switch",
                releaseDate = "March 20, 2020",
                rating = 9.0,
                coverImage = "animal_crossing",
                esrbRating = "E",
                developer = "Nintendo EPD",
                publisher = "Nintendo",
                genre = "Life simulation",
                description = "Animal Crossing: New Horizons is a life simulation game where players take on the role of a human character living on a deserted island inhabited by anthropomorphic animals, and engage in various activities such as fishing, bug catching, and crafting.",
                userImpressions = listOf(
                    UserRating(username = "Rachel321", timestamp = 1648736400L, rating = 9.5),
                    UserReview(username = "Steve444", timestamp = 1648851600L, review = "This game is a relaxing and calming experience. The customization options are endless and the daily events keep the game fresh."),
                    UserRating(username = "Tina999", timestamp = 1648966800L, rating = 8.0),
                    UserReview(username = "Vincent567", timestamp = 1649082000L, review = "The game is cute and charming, but it can get repetitive after a while."),
                    UserRating(username = "Wendy777", timestamp = 1649197200L, rating = 9.0)
                )
            ),
            Game(
                title = "Horizon Zero Dawn",
                platform = "PlayStation 4",
                releaseDate = "February 28, 2017",
                rating = 9.0,
                coverImage = "horizon_zero_dawn",
                esrbRating = "T",
                developer = "Guerrilla Games",
                publisher = "Sony Interactive Entertainment",
                genre = "Action-RPG",
                description = "Horizon Zero Dawn is an action-RPG set in a post-apocalyptic world where humans live alongside robotic creatures. Players take on the role of Aloy, a skilled hunter, as she explores the world, uncovers its secrets and battles the robotic creatures that threaten it.",
                userImpressions = listOf(
                    UserRating(username = "Emily123", timestamp = 1648736400L, rating = 9.5),
                    UserReview(username = "Jake456", timestamp = 1648851600L, review = "The world-building and character development in this game are excellent. The story kept me hooked until the very end."),
                    UserRating(username = "Alex789", timestamp = 1648966800L, rating = 8.0),
                    UserReview(username = "Olivia101", timestamp = 1649082000L, review = "The combat is satisfying, and I loved exploring the world, but some of the side quests felt a bit repetitive."),
                    UserRating(username = "Daniel222", timestamp = 1649197200L, rating = 9.0)
                )
            ),
            Game(
                title = "Super Smash Bros. Ultimate",
                platform = "Nintendo Switch",
                releaseDate = "December 7, 2018",
                rating = 9.4,
                coverImage = "super_smash_bros",
                esrbRating = "E10+",
                developer = "Bandai Namco Studios, Sora Ltd.",
                publisher = "Nintendo",
                genre = "Fighting",
                description = "Super Smash Bros. Ultimate is a fighting game where players choose from a roster of iconic video game characters and battle it out on various stages. The game features a variety of modes, including a single-player adventure mode, and can be played with up to 8 players locally or online.",
                userImpressions = listOf(
                    UserRating(username = "GamerGuru", timestamp = 1648736400L, rating = 10.0),
                    UserReview(username = "SuperSmashFan", timestamp = 1648851600L, review = "This game is the ultimate Super Smash Bros. experience. The amount of content is insane and the gameplay is top-notch."),
                    UserRating(username = "Joey23", timestamp = 1648966800L, rating = 9.0),
                    UserReview(username = "MeggieSmith", timestamp = 1649082000L, review = "I love playing this game with friends. It's always a blast and there's so many characters to choose from."),
                    UserRating(username = "BobTheBuilder", timestamp = 1649197200L, rating = 8.5)
                )
            ),
            Game(
                title = "Stardew Valley",
                platform = "Multi-platform",
                releaseDate = "February 26, 2016",
                rating = 9.5,
                coverImage = "stardew_valley",
                esrbRating = "E",
                developer = "ConcernedApe",
                publisher = "ConcernedApe",
                genre = "Farming simulation, role-playing",
                description = "Stardew Valley is a farming simulation and role-playing game where players inherit a small farm and work to build it up into a thriving homestead. The game also features social and exploration elements, as players can interact with other characters in the game and explore the surrounding countryside.",
                userImpressions = listOf(
                    UserRating(username = "FarmLife", timestamp = 1648736400L, rating = 9.0),
                    UserReview(username = "GardeningGuru", timestamp = 1648851600L, review = "Stardew Valley is the perfect game for anyone who loves farming and gardening. The attention to detail is impressive and the game is incredibly addictive."),
                    UserRating(username = "HarvestMoonFan", timestamp = 1648966800L, rating = 10.0),
                    UserReview(username = "CitySlicker", timestamp = 1649082000L, review = "I usually don't like farming games, but Stardew Valley has won me over. The characters are charming and the gameplay is relaxing and rewarding."),
                    UserRating(username = "RuralRocker", timestamp = 1649197200L, rating = 9.5)
                )
            )
        )


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
            return games.find { it.title == title }
        }

    }

}