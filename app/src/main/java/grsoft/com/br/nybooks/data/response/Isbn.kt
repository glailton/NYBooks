package grsoft.com.br.nybooks.data.response


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Isbn(
    @Json(name = "isbn10")
    val isbn10: String,
    @Json(name = "isbn13")
    val isbn13: String
)