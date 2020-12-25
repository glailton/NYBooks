package grsoft.com.br.nybooks.data.response


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import grsoft.com.br.nybooks.data.model.Book

@JsonClass(generateAdapter = true)
data class BookDetail(
    @Json(name = "age_group")
    val ageGroup: String,
    @Json(name = "author")
    val author: String,
    @Json(name = "contributor")
    val contributor: String,
    @Json(name = "contributor_note")
    val contributorNote: String,
    @Json(name = "description")
    val description: String,
    @Json(name = "price")
    val price: Int,
    @Json(name = "primary_isbn10")
    val primaryIsbn10: String,
    @Json(name = "primary_isbn13")
    val primaryIsbn13: String,
    @Json(name = "publisher")
    val publisher: String,
    @Json(name = "title")
    val title: String
) {
    fun getBookModel() = Book(title = this.title,
        author = this.author,
        description = this.description)
}