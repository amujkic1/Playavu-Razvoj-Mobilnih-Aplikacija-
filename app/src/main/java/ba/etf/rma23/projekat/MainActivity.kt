package ba.etf.rma23.projekat

import android.content.res.Configuration
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
//import ba.etf.rma23.projekat.data.repositories.GameData
import com.google.android.material.bottomnavigation.BottomNavigationView
import ba.etf.rma23.projekat.R
import ba.etf.rma23.projekat.data.repositories.*

class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController
    companion object {
        lateinit var navControllerDetails: NavController
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home_activity)

        val bottomNav: BottomNavigationView = findViewById(R.id.bottom_nav)

        var navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController
        navController.graph.setStartDestination(R.id.homeItem)

        if(resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE){
            bottomNav.visibility = View.GONE
            onConfigurationChanged(Configuration())
        }

        navController.addOnDestinationChangedListener(object :
            NavController.OnDestinationChangedListener{
            override fun onDestinationChanged(
                controller: NavController,
                destination: NavDestination,
                arguments: Bundle?
            ) {
                if (destination.id == R.id.homeItem) {
                    bottomNav.menu.findItem(R.id.homeItem).setEnabled(false);
                    bottomNav.menu.findItem(R.id.gameDetailsItem).setEnabled(true);
                    if(GameDetailsFragment.lastOpened == ""){
                        bottomNav.menu.findItem(R.id.gameDetailsItem).setEnabled(false);
                    }
                } else if (destination.id == R.id.gameDetailsItem) {
                    bottomNav.menu.findItem(R.id.homeItem).setEnabled(true);
                    bottomNav.menu.findItem(R.id.gameDetailsItem).setEnabled(false);
                }
            }
        }
        )

        bottomNav.setOnItemSelectedListener { menuItem ->
            when(menuItem.itemId){
                R.id.homeItem -> {
                    NavHostFragment.findNavController(navHostFragment).navigate(R.id.homeItem)
                }
                R.id.gameDetailsItem -> {
                    println(GameDetailsFragment.lastOpened)
                    NavHostFragment.findNavController(navHostFragment).navigate(R.id.gameDetailsItem,
                    Bundle().apply { putString("game_title", GameDetailsFragment.lastOpened) })
                }
            }
            true
        }

    }


    override fun onConfigurationChanged(newConfig: Configuration){
        super.onConfigurationChanged(newConfig)
        val navHostFragmentDetails = supportFragmentManager.findFragmentById(R.id.nav_host_fragment_details_land) as NavHostFragment
        navControllerDetails = navHostFragmentDetails.findNavController()
        navControllerDetails.graph.setStartDestination(R.id.gameDetailsItem)
        var gameList = getAllItemsFromRecyclerView()
        if(GameDetailsFragment.lastOpened == "") {
            NavHostFragment.findNavController(navHostFragmentDetails).navigate(R.id.gameDetailsItem,
                Bundle().apply { putString("game_title", gameList.first().name) })
        }else{
            NavHostFragment.findNavController(navHostFragmentDetails).navigate(R.id.gameDetailsItem,
                Bundle().apply { putString("game_title", GameDetailsFragment.lastOpened) })
        }
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