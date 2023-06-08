package ba.etf.rma23.projekat.data.repositories

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ba.etf.rma23.projekat.R
import org.w3c.dom.Text

private const val TYPE_REVIEW: Int = 0
private const val TYPE_RATING: Int = 1

class DetailsListAdapter(
    private var impressionsList: List<UserImpression>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    inner class RatingBarViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val username: TextView = itemView.findViewById(R.id.username_textview)
        val ratingBar: RatingBar = itemView.findViewById(R.id.rating_bar)
    }

    inner class CommentViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val username: TextView = itemView.findViewById(R.id.username_textview)
        val review: TextView = itemView.findViewById(R.id.review_textview)
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

        val userImpression = impressionsList[position]
        if(getItemViewType(position) == TYPE_REVIEW){
            holder as CommentViewHolder
            userImpression as UserReview
            holder.username.text = userImpression.username
            holder.review.text = userImpression.review
        } else{
            holder as RatingBarViewHolder
            userImpression as UserRating
            holder.username.text = userImpression.username
            holder.ratingBar.rating = userImpression.rating.toFloat()
        }
    }
}