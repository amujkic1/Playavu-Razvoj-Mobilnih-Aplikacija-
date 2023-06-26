package ba.etf.rma23

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import ba.etf.rma23.projekat.data.repositories.AppDatabase
import ba.etf.rma23.projekat.data.repositories.GameDAO
import ba.etf.rma23.projekat.data.repositories.GameReview
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MyDBTest {

    private lateinit var gameDAO: GameDAO
    private lateinit var database: AppDatabase

    @Before
    fun setup(){
        val context: Context = ApplicationProvider.getApplicationContext()
        database = AppDatabase.getInstance(context)
        gameDAO = database.gameDao()
    }

    @After
    fun teardown(){
        database.close()
    }

    @Test
    fun insertGameReview() = runBlocking{
        val gameReview1 = GameReview(
            id = 1111,
            review = "excellent",
            rating = 5,
            igdbID = 1025,
            online = false,
            timestamp = "1687597233123",
            student = "amujkic1@etf.unsa.ba"
        )
        val gameReview2 = GameReview(
            id = 2222,
            review = "very good",
            rating = 4,
            igdbID = 1025,
            online = false,
            timestamp = "1687597233456",
            student = "amujkic1@etf.unsa.ba"
        )
        val gameReview3 = GameReview(
            id = 3333,
            review = "ok",
            rating = 3,
            igdbID = 1025,
            online = false,
            timestamp = "1687597233789",
            student = "amujkic1@etf.unsa.ba"
        )

        gameDAO.deleteAll()
        gameDAO.insertAll(gameReview1)
        gameDAO.insertAll(gameReview2)
        gameDAO.insertAll(gameReview3)
        //gameDAO.deleteAll()
    }

}
