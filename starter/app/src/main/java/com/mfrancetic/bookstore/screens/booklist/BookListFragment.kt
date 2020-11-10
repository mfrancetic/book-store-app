package com.mfrancetic.bookstore.screens.booklist

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.mfrancetic.bookstore.R
import com.mfrancetic.bookstore.databinding.BookListFragmentBinding
import timber.log.Timber

class BookListFragment : Fragment() {

    private lateinit var viewModel: BookListViewModel
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
        viewModel = ViewModelProvider(this).get(BookListViewModel::class.java)

        binding.addBookButton.setOnClickListener {
            navigateToBookDetailsScreen()
        }
    }

    private fun navigateToBookDetailsScreen() {
        Timber.i("navigating to book details screen")
    }
}