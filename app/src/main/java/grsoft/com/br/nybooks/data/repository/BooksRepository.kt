package grsoft.com.br.nybooks.data.repository

import grsoft.com.br.nybooks.data.BooksResult

interface BooksRepository {
    fun getBooks(booksResultCallback: (result: BooksResult) -> Unit)
}