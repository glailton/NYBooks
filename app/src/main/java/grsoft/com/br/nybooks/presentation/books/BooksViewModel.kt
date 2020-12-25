package grsoft.com.br.nybooks.presentation.books

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import grsoft.com.br.nybooks.R
import grsoft.com.br.nybooks.data.model.Book
import grsoft.com.br.nybooks.data.network.ApiService
import grsoft.com.br.nybooks.data.response.BooksBodyResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BooksViewModel : ViewModel() {

    val booksLiveData: MutableLiveData<List<Book>> = MutableLiveData()
    val viewFlipperData: MutableLiveData<Pair<Int, Int?>> = MutableLiveData()

    fun getBooks() {
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

                        booksLiveData.value = books
                        viewFlipperData.value = Pair(VIEW_FLIPPER_BOOKS, null)
                    }
                    response.code() == 401 -> {
                        viewFlipperData.value = Pair(VIEW_FLIPPER_ERROR, R.string.books_error_401)
                    }
                    else -> {
                        viewFlipperData.value = Pair(VIEW_FLIPPER_ERROR, R.string.books_error_400_generic)
                    }
                }
            }

            override fun onFailure(call: Call<BooksBodyResponse>, t: Throwable) {
                viewFlipperData.value = Pair(VIEW_FLIPPER_ERROR, R.string.books_error_500_generic)
            }

        })
    }

    companion object {
        private const val VIEW_FLIPPER_BOOKS = 1
        private const val VIEW_FLIPPER_ERROR = 2
    }
}