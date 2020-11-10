package com.mfrancetic.bookstore.screens.instructions

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.mfrancetic.bookstore.R
import com.mfrancetic.bookstore.databinding.InstructionsFragmentBinding
import timber.log.Timber

class InstructionsFragment : Fragment() {

    private lateinit var viewModel: InstructionsViewModel
    private lateinit var binding: InstructionsFragmentBinding

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
        viewModel = ViewModelProvider(this).get(InstructionsViewModel::class.java)

        binding.displayBookListButton.setOnClickListener {
            navigateToBookListFragment()
        }
    }

    private fun navigateToBookListFragment() {
        findNavController().navigate(InstructionsFragmentDirections.actionInstructionsToBookListFragment())
    }
}