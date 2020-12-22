package grsoft.com.br.nybooks.data.response


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Result(
    @Json(name = "amazon_product_url")
    val amazonProductUrl: String,
    @Json(name = "asterisk")
    val asterisk: Int,
    @Json(name = "bestsellers_date")
    val bestsellersDate: String,
    @Json(name = "book_details")
    val bookDetails: List<BookDetail>,
    @Json(name = "dagger")
    val dagger: Int,
    @Json(name = "display_name")
    val displayName: String,
    @Json(name = "isbns")
    val isbns: List<Isbn>,
    @Json(name = "list_name")
    val listName: String,
    @Json(name = "published_date")
    val publishedDate: String,
    @Json(name = "rank")
    val rank: Int,
    @Json(name = "rank_last_week")
    val rankLastWeek: Int,
    @Json(name = "reviews")
    val reviews: List<Review>,
    @Json(name = "weeks_on_list")
    val weeksOnList: Int
)