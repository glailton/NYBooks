package grsoft.com.br.nybooks.presentation.books

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import grsoft.com.br.nybooks.data.model.Book
import grsoft.com.br.nybooks.data.network.ApiService
import grsoft.com.br.nybooks.data.response.BooksBodyResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BooksViewModel : ViewModel() {

    val booksLiveData: MutableLiveData<List<Book>> = MutableLiveData()

    fun getBooks() {
        ApiService.service.getBooks().enqueue(object : Callback<BooksBodyResponse> {
            override fun onResponse(
                call: Call<BooksBodyResponse>,
                response: Response<BooksBodyResponse>
            ) {
                if (response.isSuccessful) {
                   val books: MutableList<Book> = mutableListOf()

                   response.body()?.let { booksBodyResponse ->
                        booksBodyResponse.results.forEach { book ->
                            books.add(Book(book.bookDetails[0].title, book.bookDetails[0].author))
                        }

                   }

                    booksLiveData.value = books
                }
            }

            override fun onFailure(call: Call<BooksBodyResponse>, t: Throwable) {

            }

        })
    }
}