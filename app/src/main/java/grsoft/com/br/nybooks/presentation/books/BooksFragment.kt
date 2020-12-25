package grsoft.com.br.nybooks.presentation.books

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import grsoft.com.br.nybooks.databinding.FragmentBooksBinding

class BooksFragment : Fragment() {
    private var _binding: FragmentBooksBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentBooksBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val booksViewModel: BooksViewModel = ViewModelProviders.of(this).get(BooksViewModel::class.java)

        booksViewModel.booksLiveData.observe(viewLifecycleOwner, Observer {
            it?.let { books ->
                with(binding.recyclerBooks) {
                    layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
                    setHasFixedSize(true)
                    adapter = BooksAdapter(books) { book ->
                        val directions = BooksFragmentDirections
                            .actionBooksFragmentToBookDetailsFragment(book.title, book.description)
                        findNavController().navigate(directions)
                    }
                }
            }
        })

        booksViewModel.viewFlipperData.observe(viewLifecycleOwner, Observer {
            it?.let { viewFlipper ->
                binding.viewFlipperBooks.displayedChild = viewFlipper.first
                viewFlipper.second?.let { errorMessageHasId ->
                    binding.textViewError.text = getString(errorMessageHasId)

                }
            }
        })

        booksViewModel.getBooks()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}