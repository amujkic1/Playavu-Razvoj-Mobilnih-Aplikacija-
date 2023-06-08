package ba.etf.rma23.projekat.data.repositories

import com.google.gson.annotations.SerializedName

data class Game(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String?,
    var platform: String = "",
    var releaseDate: String? = "",
    @SerializedName("rating")
    val rating: Double? = 0.0,
    var cover_img: String? = "",
    var esrb_rating: String = "",
    var genre: String? = "",
    var developer: String = "",
    var publisher: String= "",
    @SerializedName("summary")
    val description: String? = "",
    val userImpressions: List<UserImpression> = emptyList(),
    @SerializedName("genres")
    val genres: List<Genre>? = emptyList(),
    @SerializedName("platforms")
    val platforms: List<Platform>? = emptyList(),
    @SerializedName("release_dates")
    val releaseDateList: List<ReleaseDate>? = emptyList(),
    @SerializedName("age_ratings")
    val ageRatings: List<ESRBRating>? = emptyList(),
    @SerializedName("cover")
    val covers: Cover? = Cover(""),
    @SerializedName("involved_companies")
    var involvedComp: List<InvolvedCompany>? = emptyList())
{
    fun populate(){
        if(platforms!=null) platform = platforms[0].name!!
        if(releaseDateList!=null) releaseDate = releaseDateList[0].human!!
        if(genres!=null) genre = genres[0].name!!
        if(ageRatings!=null) esrb_rating = ageRatings[0].rating.toString()
        if(covers!=null) cover_img = covers.url

        if (involvedComp != null) {
            val companiesList = involvedComp

            companiesList!!.forEach { involvedCompany ->
                val company = involvedCompany.comp
                if (involvedCompany.pub == true) {
                    if (company != null) {
                        if (publisher.isNullOrEmpty()) {
                            publisher = company.name

                        } else {
                            publisher += "\n${company.name}"
                        }
                    }
                }
                if (involvedCompany.dev == true) {
                    if (company != null) {
                        if (developer.isNullOrEmpty()) {
                            developer = company.name
                        } else {
                            developer += "\n${company.name}"
                        }
                    }
                }
            }
        }
        else {
            developer = ""
            publisher = ""
        }
        //if(involvedComp!=null) publisher = involvedComp!![0].comp.name!!
        /*val developers = mutableListOf<String>()
        val publishers = mutableListOf<String>()
        involvedComp?.forEach { involvedCompany ->
            if (involvedCompany.dev == true) {
                involvedCompany.comp?.name?.let { developers.add(it) }
            }
            if (involvedCompany.pub == true) {
                involvedCompany.comp?.name?.let { publishers.add(it) }
            }
        }
        developer = developers.joinToString(", ")
        publisher = publishers.joinToString(", ")*/
        cover_img = covers?.url
    }

}



