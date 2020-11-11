package com.mfrancetic.bookstore.screens.booklist

import android.os.Bundle
import android.view.ContextThemeWrapper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.mfrancetic.bookstore.BookViewModel
import com.mfrancetic.bookstore.R
import com.mfrancetic.bookstore.databinding.BookListFragmentBinding
import com.mfrancetic.bookstore.models.Book

class BookListFragment : Fragment() {

    private val viewModel: BookViewModel by activityViewModels()
    private lateinit var binding: BookListFragmentBinding

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.book_list_fragment, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel.books.observe(viewLifecycleOwner, Observer { books ->
            updateBookView(books)
        })

        binding.addBookButton.setOnClickListener {
            navigateToBookDetailsScreen()
        }
    }

    private fun updateBookView(books: List<Book>) {
        binding.bookListLinearLayout.removeAllViews()
        books.forEach { book ->
            addBookToLinearLayout(book)
        }
    }

    private fun addBookToLinearLayout(book: Book) {
        val bookViewTitle = TextView(ContextThemeWrapper(context, R.style.BookTitleStyle), null, 0)
        bookViewTitle.text = viewModel.getBookViewTitle(book)

        val bookViewSubtitle = TextView(ContextThemeWrapper(context, R.style.BookSubtitleStyle), null, 0)
        bookViewSubtitle.text = viewModel.getBookViewSubtitle(book)

        binding.bookListLinearLayout.addView(bookViewTitle)
        binding.bookListLinearLayout.addView(bookViewSubtitle)
    }

    private fun navigateToBookDetailsScreen() {
        findNavController().navigate(BookListFragmentDirections.actionBookListFragmentToBookDetailFragment())
    }
}