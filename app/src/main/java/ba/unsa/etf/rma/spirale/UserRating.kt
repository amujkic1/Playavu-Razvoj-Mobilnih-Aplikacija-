package ba.unsa.etf.rma.spirale

data class UserRating(
    override val username: String,
    override val timestamp: Long,
    val rating: Double
): UserImpression()