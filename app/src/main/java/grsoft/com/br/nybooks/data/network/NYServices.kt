package grsoft.com.br.nybooks.data.network

import grsoft.com.br.nybooks.data.response.BooksBodyResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface NYServices {

    @GET("lists.json")
    fun getBooks(
        @Query("api-key") apiKey: String = "fooGFQb8PWiMCzQeIG9NecLjJK1sHkhJ",
        @Query("list") list: String = "hardcover-fiction"): Call<BooksBodyResponse>
}