package com.mfrancetic.bookstore.screens.login

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.mfrancetic.bookstore.MainActivity
import com.mfrancetic.bookstore.R
import com.mfrancetic.bookstore.databinding.LoginFragmentBinding
import com.mfrancetic.bookstore.utils.SharedPreferencesHelper

class LoginFragment : Fragment() {

    private lateinit var viewModel: LoginViewModel
    private lateinit var binding: LoginFragmentBinding;
    private lateinit var activity: MainActivity

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
        viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)

        binding.loginButton.setOnClickListener {
            loginUser()
        }
        binding.registerButton.setOnClickListener {
            loginUser()
        }
    }

    private fun loginUser() {
        SharedPreferencesHelper.addLoginStatusToSharedPreferences(this.activity, true)
        navigateToWelcomeScreen()
    }

    private fun navigateToWelcomeScreen() {
        findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToWelcomeFragment())
    }
}