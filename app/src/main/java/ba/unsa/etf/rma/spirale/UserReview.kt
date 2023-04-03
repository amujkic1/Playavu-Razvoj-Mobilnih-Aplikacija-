package ba.unsa.etf.rma.spirale

data class UserReview (
    override val username: String,
    override val timestamp: Long,
    val review: String
    ): UserImpression()