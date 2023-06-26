package ba.etf.rma23.projekat.data.repositories

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ba.etf.rma23.projekat.R
import com.squareup.picasso.Picasso
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.net.URL

class GameDetailsFragment : Fragment() {

    companion object {
        var lastOpened: String? = ""
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
    private lateinit var favoriteButton: Button
    private lateinit var deleteButton: Button
    private var allGamesList : List<Game> = getAllItemsFromRecyclerView()

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
        favoriteButton = view.findViewById(R.id.favorite_button)
        deleteButton = view.findViewById(R.id.delete_button)

        val bundle = arguments
        //game = bundle?.getString("game_title") as Game
        game = getGameByTitle(bundle?.getString("game_title")) as Game
        lastOpened = game.name

        favoriteButton.setOnClickListener{
            saveGame(game)
        }

        deleteButton.setOnClickListener{
            removeGame(game.id)
        }

        var gameReview: GameReview = GameReview(2, "mediocre", 3, 1025, false, "", "")
        var context = requireContext()

        //sendReview(gameReview, context)

        getUserImpressions()

        impressionsList.layoutManager = LinearLayoutManager(
            activity,
            LinearLayoutManager.VERTICAL,
            false
        )
        populateDetails()

        return view

    }

    fun populateUserImpressions(reviews : List<GameReview>): List<UserImpression>{
        var impression: UserImpression
        var userImpressions: ArrayList<UserImpression> = ArrayList()
        for(gameReview in reviews){
            if(gameReview.rating == null){
                impression = UserReview("username", 1, gameReview.review)
                userImpressions.add(impression)
            }
            if(gameReview.review == null){
                impression = UserRating("username", 1, gameReview.rating?.toDouble() ?: 0.0)
                userImpressions.add(impression)
            }
            if(gameReview.review != null && gameReview.rating != null){
                impression = UserRating("username", 1, gameReview.rating?.toDouble() ?: 0.0)
                userImpressions.add(impression)
                impression = UserReview("username", 1, gameReview.review)
                userImpressions.add(impression)
            }
        }
        return userImpressions
    }

    fun sendReview(gameReview: GameReview, context: Context){
        val scope = CoroutineScope(Job() + Dispatchers.Main)
        scope.launch {
            val result = GameReviewsRepository.sendReview(context, gameReview)
            when(result){
                is Boolean -> onSuccess3(result)
                else -> onError3()
            }
        }
    }

    private fun getUserImpressions() {
        println("ooooooooooooooooooooooooooooooooooooooooooooooooooooooo")
        val scope = CoroutineScope(Job() + Dispatchers.Main)
        scope.launch {
            val result = GameReviewsRepository.getReviewsForGame(game.id)
            when(result){
                is List<GameReview> -> onSuccess2(result)
                else -> onError1()
            }
        }
    }

    fun onSuccess2(gameReviews: List<GameReview>){
        impressionsListAdapter = DetailsListAdapter(populateUserImpressions(gameReviews))
        impressionsList.adapter = impressionsListAdapter
        impressionsListAdapter.updateImpression(populateUserImpressions(gameReviews))
        println(populateUserImpressions(gameReviews).size)
        println("broj reviewsa jeeeeeeeeeeeeeeeeeeeeeeeeee " + gameReviews.size)
        if(gameReviews.size > 0)
            println("review je " + gameReviews[0].review + "rating jr " + gameReviews[0].rating)
        else
            println("nema reviewaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa")
        println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa")

    }

    private fun getSavedGames(): List<Game>{
        val scope = CoroutineScope(Job() + Dispatchers.Main)
        scope.launch {
            val result = AccountGamesRepository.getSavedGames()
            when(result){
                is List<Game> -> onSuccess1(result)
                else -> onError1()
            }
        }
        return emptyList()
    }

    fun onSuccess3(value: Boolean): Boolean{
        return true
    }

    fun onError3(): Boolean{
        return false
    }

    fun onSuccess1(games: List<Game>): List<Game>{
        return games
    }

    fun onError1(): List<Game>{
        return  emptyList()
    }


    private fun saveGame(game: Game){
        val scope = CoroutineScope(Job() + Dispatchers.Main)
        scope.launch {
            try{
                AccountGamesRepository.saveGame(game)
            }catch (e: Exception){
                onError()
            }
        }
    }

    private fun removeGame(id: Int){
        val scope = CoroutineScope(Job() + Dispatchers.Main)
        scope.launch {
            try{
                AccountGamesRepository.removeGame(id)
            }catch (e: Exception){
                onError()
            }
        }
    }

    fun onSuccess(game: Game){
        val toast = Toast.makeText(context, "Game saved", Toast.LENGTH_SHORT)
        toast.show()
    }

    fun onError(){
        val toast = Toast.makeText(context, "Search error", Toast.LENGTH_SHORT)
        toast.show()
    }


    private fun populateDetails() {

        gameTitle.text = game.name ?: ""
        if(game.platforms?.size ?: 0 != 0)
            platform.text = game.platforms?.get(0)?.name ?: ""
        else platform.text = " "
        releaseDate.text = game.releaseDate?.toString() ?: ""
        if(game.ageRatings?.size ?: 0 != 0)
            rating.text = game.ageRatings?.get(0)?.toString() ?: "0"
        else rating.text = " "
        genre.text = game.genres?.getOrNull(1)?.name ?: ""
        //description.text = game.description ?: ""
        description.text = "no description"
        publisher.text = game.publisher
        println(game.publisher)
        println(game.developer)
        developer.text = game.developer

        val imageUrl = "https:" + game.cover_img

        var context: Context = coverImage.context
        Picasso.with(context).load(imageUrl).into(coverImage)
        println(imageUrl)
    }

    private fun getGameByTitle(name: String?): Game {
        val game = allGamesList.find { it.name == name }
        var tmpCover: Cover? = Cover("")
        return game?:Game(1, "name","", "", 0.1, "", "", "", "", "", "",
            emptyList(), emptyList(), emptyList(), emptyList(), emptyList())
    }

    private fun getAllItemsFromRecyclerView(): List<Game> {
        val allItems = mutableListOf<Game>()
        val adapter = HomeFragment.gamesList.adapter
        if (adapter is GameListAdapter) {
            val itemCount = adapter.itemCount
            for (i in 0 until itemCount) {
                val game = adapter.getItem(i)
                allItems.add(game)
            }
        }
        return allItems
    }

}
