package grsoft.com.br.nybooks.data.response


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Review(
    @Json(name = "article_chapter_link")
    val articleChapterLink: String,
    @Json(name = "book_review_link")
    val bookReviewLink: String,
    @Json(name = "first_chapter_link")
    val firstChapterLink: String,
    @Json(name = "sunday_review_link")
    val sundayReviewLink: String
)