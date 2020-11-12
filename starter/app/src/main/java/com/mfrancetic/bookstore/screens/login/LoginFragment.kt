package com.mfrancetic.bookstore.screens.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.mfrancetic.bookstore.BookViewModel
import com.mfrancetic.bookstore.MainActivity
import com.mfrancetic.bookstore.R
import com.mfrancetic.bookstore.databinding.LoginFragmentBinding
import com.mfrancetic.bookstore.utils.UIUtils

class LoginFragment : Fragment() {

    private lateinit var binding: LoginFragmentBinding
    private lateinit var activity: MainActivity
    private val viewModel: BookViewModel by activityViewModels()

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.login_fragment, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        activity = this.getActivity() as MainActivity

        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        viewModel.eventLogin.observe(viewLifecycleOwner, { eventLogin ->
            if (eventLogin) {
                viewModel.addLoginStateToSharedPreferences(activity, true)
                navigateToWelcomeScreen()
                viewModel.eventLoginComplete()
            }
        })

        viewModel.eventSnackbar.observe(viewLifecycleOwner, { eventSnackbar ->
            if (eventSnackbar) {
                UIUtils.displaySnackbar(requireView(), getString(R.string.all_fields_required))
                viewModel.eventSnackbarComplete()
            }
        })
    }

    private fun navigateToWelcomeScreen() {
        findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToWelcomeFragment())
    }
}