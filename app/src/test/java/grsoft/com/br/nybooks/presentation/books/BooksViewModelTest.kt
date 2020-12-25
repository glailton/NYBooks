package grsoft.com.br.nybooks.presentation.books

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.nhaarman.mockitokotlin2.verify
import grsoft.com.br.nybooks.R
import grsoft.com.br.nybooks.data.BooksResult
import grsoft.com.br.nybooks.data.model.Book
import grsoft.com.br.nybooks.data.repository.BooksRepository
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class BooksViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Mock
    private lateinit var booksLiveDataObserver: Observer<List<Book>>

    @Mock
    private lateinit var viewFlipperDataObserver: Observer<Pair<Int, Int?>>

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
    }

    private lateinit var viewModel: BooksViewModel

    @Test
    fun `when view model getBooks gets success then sets booksLiveData`() {
        val books = listOf<Book>(
            Book("Title 1", "Author 1", "Description 1")
        )
        val resultSuccess = MockRepository(BooksResult.Success(books))
        viewModel = BooksViewModel(resultSuccess)
        viewModel.booksLiveData.observeForever(booksLiveDataObserver)
        viewModel.viewFlipperData.observeForever(viewFlipperDataObserver)

        viewModel.getBooks()

        verify(booksLiveDataObserver).onChanged(books)
        verify(viewFlipperDataObserver).onChanged(Pair(1, null))
    }

    @Test
    fun `when view model getBooks gets api error 401 then set viewFlipperData`() {
        val resultApiError401 = MockRepository(BooksResult.ApiError(401))
        viewModel = BooksViewModel(resultApiError401)
        viewModel.viewFlipperData.observeForever(viewFlipperDataObserver)

        viewModel.getBooks()

        verify(viewFlipperDataObserver).onChanged(Pair(2, R.string.books_error_401 ))
    }

    @Test
    fun `when view model getBooks gets api error generic then set viewFlipperData`() {
        val resultApiError401 = MockRepository(BooksResult.ApiError(404))
        viewModel = BooksViewModel(resultApiError401)
        viewModel.viewFlipperData.observeForever(viewFlipperDataObserver)

        viewModel.getBooks()

        verify(viewFlipperDataObserver).onChanged(Pair(2, R.string.books_error_400_generic ))
    }

    @Test
    fun `when view model getBooks gets server error then set viewFlipperData`() {
        val books = listOf<Book>(
            Book("Title 1", "Author 1", "Description 1")
        )
        val resultServer = MockRepository(BooksResult.ServerError)
        viewModel = BooksViewModel(resultServer)
        viewModel.viewFlipperData.observeForever(viewFlipperDataObserver)

        viewModel.getBooks()

        verify(viewFlipperDataObserver).onChanged(Pair(2, R.string.books_error_500_generic ))
    }
}

class MockRepository(private val result: BooksResult) : BooksRepository {
    override fun getBooks(booksResultCallback: (result: BooksResult) -> Unit) {
        booksResultCallback(result)
    }
}