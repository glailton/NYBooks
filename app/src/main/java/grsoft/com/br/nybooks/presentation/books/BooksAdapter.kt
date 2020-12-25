package grsoft.com.br.nybooks.presentation.books

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import grsoft.com.br.nybooks.data.model.Book
import grsoft.com.br.nybooks.databinding.BookItemBinding

class BooksAdapter(
    private val books: List<Book>,
    val onItemClickListener: ((book: Book) -> Unit)): RecyclerView.Adapter<BooksAdapter.BooksViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BooksViewHolder {
        val binding = BookItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BooksViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BooksViewHolder, position: Int) {
        val book = books[position]

        holder.bindView(book, onItemClickListener)
    }

    override fun getItemCount() = books.count()

    class BooksViewHolder(private val binding: BookItemBinding) : RecyclerView.ViewHolder(binding.root) {

        private val title = binding.textViewTitle
        private val author = binding.textViewAuthor
        private val description = binding.textViewAuthor

        fun bindView(book: Book, listener: (book: Book) -> Unit) {
            title.text = book.title
            author.text = book.author

            binding.root.setOnClickListener {
               listener(book)
            }
        }
    }
}


