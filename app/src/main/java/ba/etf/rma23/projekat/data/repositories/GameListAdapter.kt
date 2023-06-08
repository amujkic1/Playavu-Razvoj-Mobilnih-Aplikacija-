package ba.etf.rma23.projekat.data.repositories

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ba.etf.rma23.projekat.R

class GameListAdapter(
    private var gameList: List<Game>,
    private val onItemClicked: (game:Game) -> Unit
    ) :
    RecyclerView.Adapter<GameListAdapter.GameViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GameViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_game, parent, false)
        return GameViewHolder(view)
    }

    override fun getItemCount(): Int = gameList.size

    override fun onBindViewHolder(holder: GameViewHolder, position: Int) {
        holder.titleTextview.text = gameList[position].name
        holder.releaseDateTextview.text = gameList[position].releaseDate
        holder.platformTextview.text = gameList[position].platforms?.get(0)?.name ?: ""
        holder.ratingTextview.text = gameList[position].rating.toString()
        holder.itemView.setOnClickListener{ onItemClicked(gameList[position]) }
    }

    fun updateGames(gameList: List<Game>) {
        this.gameList = gameList
        notifyDataSetChanged()
    }

    fun getItem(i: Int): Game {
        return gameList[i]
    }

    inner class GameViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val titleTextview : TextView = itemView.findViewById(R.id.item_title_textview)
        val releaseDateTextview : TextView = itemView.findViewById(R.id.release_date)
        val platformTextview : TextView = itemView.findViewById(R.id.game_platform_textview)
        val ratingTextview : TextView = itemView.findViewById(R.id.game_rating_textview)

    }
}

