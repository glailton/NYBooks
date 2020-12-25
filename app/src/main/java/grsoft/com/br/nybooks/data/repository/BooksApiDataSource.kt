package grsoft.com.br.nybooks.data.repository

import grsoft.com.br.nybooks.data.BooksResult
import grsoft.com.br.nybooks.data.model.Book
import grsoft.com.br.nybooks.data.network.ApiService
import grsoft.com.br.nybooks.data.response.BooksBodyResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BooksApiDataSource: BooksRepository {
    override fun getBooks(booksResultCallback: (result: BooksResult) -> Unit) {
        ApiService.service.getBooks().enqueue(object : Callback<BooksBodyResponse> {
            override fun onResponse(
                call: Call<BooksBodyResponse>,
                response: Response<BooksBodyResponse>
            ) {
                when {
                    response.isSuccessful -> {
                        val books: MutableList<Book> = mutableListOf()

                        response.body()?.let { booksBodyResponse ->
                            booksBodyResponse.results.forEach { book ->
                                val b = book.bookDetails[0].getBookModel()
                                books.add(b)
                            }
                        }
                        booksResultCallback(BooksResult.Success(books))
                    }
                    else -> {
                        booksResultCallback(BooksResult.ApiError(response.code()))
                    }
                }
            }

            override fun onFailure(call: Call<BooksBodyResponse>, t: Throwable) {
                booksResultCallback(BooksResult.ServerError)
            }

        })
    }
}