package grsoft.com.br.nybooks.data.response


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class BooksBodyResponse(
    @Json(name = "copyright")
    val copyright: String,
    @Json(name = "last_modified")
    val lastModified: String,
    @Json(name = "num_results")
    val numResults: Int,
    @Json(name = "results")
    val results: List<Result>,
    @Json(name = "status")
    val status: String
)