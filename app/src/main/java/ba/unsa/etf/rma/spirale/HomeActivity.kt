package ba.unsa.etf.rma.spirale

import android.widget.Button
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ba.unsa.etf.rma.spirale.GameData.GameData.getAll

class HomeActivity : AppCompatActivity() {

    private lateinit var gamesList: RecyclerView
    private lateinit var gamesListAdapter: GameListAdapter
    private var allGamesList =  getAll()
    private lateinit var searchText: EditText
    private lateinit var homeButton: Button
    private lateinit var detailsButton: Button

    private lateinit var selected: Game

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home_activity)
        gamesList = findViewById(R.id.game_list)
        searchText = findViewById(R.id.search_query_edittext)
        detailsButton = findViewById(R.id.details_button)
        homeButton = findViewById(R.id.home_button)


        gamesList.layoutManager = LinearLayoutManager(
            this,
            LinearLayoutManager.VERTICAL,
            false
        )
        gamesListAdapter = GameListAdapter(arrayListOf()) { game -> showGameDetails(game) }
        gamesList.adapter = gamesListAdapter
        gamesListAdapter.updateGames(allGamesList)

        homeButton.isEnabled = false

        val extras = intent.extras
        if (extras != null) {
            selected = getGameByTitle(extras.getString("game_title", ""))
        }
        else detailsButton.isEnabled = false
        detailsButton.setOnClickListener {
            // Check if a game has been selected
            selected.let { game ->
                // Start the GameDetailsActivity with the selected game
                val intent = Intent(this, GameDetailsActivity::class.java).apply {
                    putExtra("game_title", game.title)
                }
                startActivity(intent)
            }
        }
    }

    private fun showGameDetails(game: Game) {
        detailsButton.isEnabled = true
        selected = game
        val intent = Intent(this, GameDetailsActivity::class.java).apply {
            putExtra("game_title", game.title)
        }
        startActivity(intent)
    }

    private fun getGameByTitle(name: String): Game {
        val games: ArrayList<Game> = arrayListOf()
        games.addAll(getAll())
        val game = games.find { game -> name == game.title }
        return game?:Game("Test","Test","Test",0.0,"Test","Test", "Test", "Test", "Test", "Test", emptyList())
    }

}