package ba.unsa.etf.rma.spirale

import android.annotation.SuppressLint
import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ba.unsa.etf.rma.spirale.GameData.GameData.getAll
import com.google.android.material.bottomnavigation.BottomNavigationItemView
import com.google.android.material.bottomnavigation.BottomNavigationView

class HomeFragment : Fragment() {

    private lateinit var gamesList: RecyclerView
    private lateinit var gamesListAdapter: GameListAdapter
    private var allGamesList =  getAll()
    private lateinit var searchText: EditText
    private lateinit var selected: Game
    private lateinit var lastOpened: Game

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view =  inflater.inflate(R.layout.fragment_home, container, false)
        gamesList = view.findViewById(R.id.game_list)
        gamesList.layoutManager = GridLayoutManager(activity, 1)
        gamesListAdapter = GameListAdapter(getAll()){ game -> showGameDetails(game) }
        gamesList.adapter = gamesListAdapter
        gamesListAdapter.updateGames(allGamesList)

        return view
    }

    private fun showGameDetails(game: Game){
        lastOpened = getGameByTitle(game.title)
        if(resources.configuration.orientation != Configuration.ORIENTATION_LANDSCAPE)
            findNavController().navigate(R.id.gameDetailsItem, Bundle().apply { putString("game_title", game.title) })
        else
            HomeActivity.navControllerDetails.navigate(R.id.gameDetailsItem, Bundle().apply { putString("game_title", game.title) })
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