package grsoft.com.br.nybooks.data

import grsoft.com.br.nybooks.data.model.Book

sealed class BooksResult {
    class Success(val books: List<Book>): BooksResult()
    class ApiError(val code: Int) : BooksResult()
    object ServerError : BooksResult()
}