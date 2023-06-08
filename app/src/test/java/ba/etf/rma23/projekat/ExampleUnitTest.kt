package ba.etf.rma23.projekat

import ba.etf.rma23.projekat.data.repositories.GamesRepository
import kotlinx.coroutines.runBlocking
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun test1() = runBlocking {
        var games = GamesRepository.getGamesByName("Ztar Attack 2: A Blast to the Past")
        assertEquals("Sep 24, 2018", games[0].releaseDate)
    }



}