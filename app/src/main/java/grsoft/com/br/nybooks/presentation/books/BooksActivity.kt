package grsoft.com.br.nybooks.presentation.books

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import grsoft.com.br.nybooks.R
import grsoft.com.br.nybooks.data.model.Book
import kotlinx.android.synthetic.main.activity_books.*

class BooksActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_books)

        setSupportActionBar(toolbar)

        with(recyclerBooks) {
            layoutManager = LinearLayoutManager(this@BooksActivity, RecyclerView.VERTICAL, false)
            setHasFixedSize(true)
            adapter = BooksAdapter(listOf(
                Book("o livro", "Glailton"),
                Book("o livro", "Glailton"),
                Book("o livro", "Glailton"),
                Book("o livro", "Glailton"),
                Book("o livro", "Glailton"),
                Book("o livro", "Glailton"),
                Book("o livro", "Glailton"),
                Book("o livro", "Glailton"),
                Book("o livro", "Glailton"),
                Book("o livro", "Glailton"),
                Book("o livro", "Glailton"),
                Book("o livro", "Glailton"),
                Book("o livro", "Glailton"),
                Book("o livro", "Glailton"),
                Book("o livro", "Glailton"),
                Book("o livro", "Glailton"),
                Book("o livro", "Glailton"),
                Book("o livro 2", "Jose")))
        }
    }
}