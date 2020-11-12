package com.mfrancetic.bookstore.screens.instructions

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
import com.mfrancetic.bookstore.databinding.InstructionsFragmentBinding

class InstructionsFragment : Fragment() {

    private lateinit var binding: InstructionsFragmentBinding
    private val viewModel: BookViewModel by activityViewModels()

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
                layoutInflater,
                R.layout.instructions_fragment,
                container,
                false
        )
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        viewModel.eventDisplayBookList.observe(viewLifecycleOwner, { eventDisplayBookList ->
            if (eventDisplayBookList) {
                navigateToBookListFragment()
                viewModel.eventDisplayBookListComplete()
            }
        })
    }

    private fun navigateToBookListFragment() {
        findNavController().navigate(InstructionsFragmentDirections.actionInstructionsToBookListFragment())
    }
}