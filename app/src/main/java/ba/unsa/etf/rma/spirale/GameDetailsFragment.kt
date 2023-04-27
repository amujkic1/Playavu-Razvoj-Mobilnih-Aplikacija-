package ba.unsa.etf.rma.spirale

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ba.unsa.etf.rma.spirale.GameData.GameData.getAll

class GameDetailsFragment : Fragment() {

    companion object {
        var lastOpened: String = ""
    }

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


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        var view = inflater.inflate(R.layout.fragment_game_details, container, false)
        impressionsList = view.findViewById(R.id.details_list)
        coverImage = view.findViewById(R.id.cover_imageview)
        gameTitle = view.findViewById(R.id.item_title_textview)
        platform = view.findViewById(R.id.platform_textview)
        releaseDate = view.findViewById(R.id.release_date)
        rating = view.findViewById(R.id.esrb_rating_textview)
        developer = view.findViewById(R.id.developer_textview)
        publisher = view.findViewById(R.id.publisher_textview)
        description = view.findViewById(R.id.description_textview)
        genre = view.findViewById(R.id.genre_textview)

        val bundle = arguments
        //game = bundle?.getString("game_title") as Game
        game = getGameByTitle(bundle?.getString("game_title")) as Game
        lastOpened = game.title
        //println(lastOpened)


        impressionsListAdapter = DetailsListAdapter(game!!.userImpressions)
        impressionsList.adapter = impressionsListAdapter
        impressionsListAdapter.updateImpression(game.userImpressions)

        impressionsList.layoutManager = LinearLayoutManager(
            activity,
            LinearLayoutManager.VERTICAL,
            false
        )
        populateDetails()
        return view

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

    private fun getGameByTitle(name: String?): Game {
        /*if(name==null){
            //lastOpened=""
            return Game("Test","Test","Test",0.0,"Test","Test", "Test", "Test", "Test", "Test", emptyList())
        }*/
        val games: ArrayList<Game> = arrayListOf()
        games.addAll(getAll())
        val game = games.find { game -> name == game.title }
        return game?:Game("Test","Test","Test",0.0,"Test","Test", "Test", "Test", "Test", "Test", emptyList())
    }
}
