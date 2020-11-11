package com.mfrancetic.bookstore.screens.bookdetail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.mfrancetic.bookstore.BookViewModel
import com.mfrancetic.bookstore.R
import com.mfrancetic.bookstore.databinding.BookDetailFragmentBinding
import com.mfrancetic.bookstore.models.Book

class BookDetailFragment : Fragment() {

    private val viewModel: BookViewModel by activityViewModels()
    private lateinit var binding: BookDetailFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.book_detail_fragment, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        binding.viewModel = viewModel
        binding.book = Book()

        setupOnClickListeners()
    }

    private fun setupOnClickListeners() {
        binding.cancelBookEditButton.setOnClickListener {
            navigateToBookListFragment()
        }

        binding.saveBookEditButton.setOnClickListener {
            viewModel.saveBook(binding.book)
            navigateToBookListFragment()
        }
    }

    private fun navigateToBookListFragment() {
        findNavController().navigate(BookDetailFragmentDirections.actionBookDetailFragmentToBookListFragment())
    }
}