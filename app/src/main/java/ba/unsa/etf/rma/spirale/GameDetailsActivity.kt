package ba.unsa.etf.rma.spirale

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ba.unsa.etf.rma.spirale.GameData.GameData.getAll
import ba.unsa.etf.rma.spirale.GameData.GameData.getUserImpressions

class GameDetailsActivity : AppCompatActivity() {

    private lateinit var game: Game
    private lateinit var impressionsList: RecyclerView
    private lateinit var impressionsListAdapter: DetailsListAdapter
    private lateinit var coverImage: ImageView
    private lateinit var gameTitle: TextView
    private lateinit var platform: TextView
    private lateinit var releaseDate: TextView
    private lateinit var rating: TextView
    private lateinit var review: TextView
    private lateinit var developer: TextView
    private lateinit var publisher: TextView
    private lateinit var description: TextView
    private lateinit var genre: TextView
    private lateinit var homeButton: Button
    private lateinit var detailsButton: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game_details)

        impressionsList = findViewById(R.id.details_list)
        coverImage = findViewById(R.id.cover_imageview)
        gameTitle = findViewById(R.id.game_title_textview)
        platform = findViewById(R.id.platform_textview)
        releaseDate = findViewById(R.id.release_date)
        rating = findViewById(R.id.esrb_rating_textview)
        developer = findViewById(R.id.developer_textview)
        publisher = findViewById(R.id.publisher_textview)
        description = findViewById(R.id.description_textview)
        genre = findViewById(R.id.genre_textview)
        homeButton = findViewById(R.id.home_button)
        detailsButton = findViewById(R.id.details_button)

        val extras = intent.extras
        if (extras != null) {
            game = getGameByTitle(extras.getString("game_title",""))
            populateDetails()
        } else {
            finish()
        }
        //private
        var userImpressionsList = getUserImpressions(game.title)

        //inicijalizacija adaptera
        impressionsListAdapter = DetailsListAdapter(game!!.userImpressions)
        impressionsList.adapter = impressionsListAdapter
        impressionsListAdapter.updateImpression(userImpressionsList)

        impressionsList.layoutManager = LinearLayoutManager(
            this,
            LinearLayoutManager.VERTICAL,
            false
        )

        detailsButton.isEnabled = false

        homeButton.setOnClickListener {
            val intent = Intent(this, HomeActivity::class.java).apply {
                putExtra("game_title", game.title)
            }
            startActivity(intent)
        }
    }

    private fun populateDetails(){
        gameTitle.text = game.title
        platform.text = game.platform
        releaseDate.text = game.releaseDate
        rating.text = game.esrbRating
        developer.text = game.developer
        publisher.text = game.publisher
        genre.text = game.genre
        description.text = game.description
        var context: Context = coverImage.context
        var Id: Int = context.resources.getIdentifier(game!!.coverImage, "drawable", context.packageName)
        if(Id==0) Id =context.resources.getIdentifier("cover_image", "drawable", context.packageName)
        coverImage.setImageResource(Id)
    }

    private fun getGameByTitle(name: String): Game {
        val games: ArrayList<Game> = arrayListOf()
        games.addAll(getAll())
        val game = games.find { game -> name == game.title }
        return game?:Game("Test","Test","Test",0.0,"Test","Test", "Test", "Test", "Test", "Test", emptyList())
    }

}