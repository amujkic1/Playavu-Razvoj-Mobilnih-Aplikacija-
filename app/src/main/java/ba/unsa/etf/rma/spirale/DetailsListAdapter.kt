package ba.unsa.etf.rma.spirale

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import org.w3c.dom.Text

private const val TYPE_REVIEW: Int = 0
private const val TYPE_RATING: Int = 1

class DetailsListAdapter(
    private var impressionsList: List<UserImpression>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    class RatingBarViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        fun bind(impression: UserImpression){
            val ratingBar : RatingBar = itemView.findViewById(R.id.rating_bar)
            ratingBar.rating = 2.5F
        }
    }

    class CommentViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        fun bind(impression: UserImpression){
            val comment: TextView = itemView.findViewById(R.id.review_textview)
        }
    }

    fun updateImpression(impressionsList: List<UserImpression>) {
        this.impressionsList = impressionsList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {


        if(viewType == TYPE_REVIEW){
            val view = LayoutInflater.from(parent.context).inflate(R.layout.item_comment, parent, false)
            return CommentViewHolder(view)
        } else{
            val view = LayoutInflater.from(parent.context).inflate(R.layout.item_rating, parent, false)
            return RatingBarViewHolder(view)
        }
    }

    override fun getItemCount(): Int {
        return impressionsList.size
    }

    override fun getItemViewType(position: Int): Int {

        return if(impressionsList[position] is UserRating){
                TYPE_RATING
        } else{
            TYPE_REVIEW
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        if(getItemViewType(position) == TYPE_REVIEW){
            (holder as CommentViewHolder).bind(impressionsList[position])
        } else{
            (holder as RatingBarViewHolder).bind(impressionsList[position])
        }
    }
}