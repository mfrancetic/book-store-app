package com.mfrancetic.bookstore

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mfrancetic.bookstore.models.Book
import com.mfrancetic.bookstore.utils.SharedPreferencesHelper
import com.mfrancetic.bookstore.utils.ValidationUtils

class BookViewModel : ViewModel() {

    private val _books: MutableLiveData<MutableList<Book>> = MutableLiveData()
    val books: LiveData<MutableList<Book>>
        get() = _books

    private val _isUserLoggedIn: MutableLiveData<Boolean> = MutableLiveData()
    val isUserLoggedIn: LiveData<Boolean>
        get() = _isUserLoggedIn

    init {
        _books.value = ArrayList()
    }

    fun getLoginStateFromSharedPreferences(activity: MainActivity) {
        val isUserLoggedIn = SharedPreferencesHelper.getLoginStatusFromSharedPreferences(activity)
        _isUserLoggedIn.value = isUserLoggedIn
    }

    fun saveBook(book: Book?): Boolean {
        if (book != null && ValidationUtils.isBookDetailsFormValid(book)) {
            _books.value?.add(book)
            return true
        }
        return false
    }

    fun getBookViewTitle(book: Book): String {
        return "${book.author}: ${book.name}"
    }

    fun getBookViewSubtitle(book: Book): String {
        return "${book.publisher} | ${book.pageNumber} p | ${book.genre}"
    }
}