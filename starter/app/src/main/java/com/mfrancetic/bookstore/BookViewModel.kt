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

    private val _eventDisplayInstructions: MutableLiveData<Boolean> = MutableLiveData()
    val eventDisplayInstructions: LiveData<Boolean>
        get() = _eventDisplayInstructions

    private val _eventDisplayBookList: MutableLiveData<Boolean> = MutableLiveData()
    val eventDisplayBookList: LiveData<Boolean>
        get() = _eventDisplayBookList

    private val _eventAddBook: MutableLiveData<Boolean> = MutableLiveData()
    val eventAddBook: LiveData<Boolean>
        get() = _eventAddBook

    private val _eventCancel: MutableLiveData<Boolean> = MutableLiveData()
    val eventCancel: LiveData<Boolean>
        get() = _eventCancel

    private val _eventSaveBook: MutableLiveData<Boolean> = MutableLiveData()
    val eventSaveBook: LiveData<Boolean>
        get() = _eventSaveBook

    private val _eventSnackbar: MutableLiveData<Boolean> = MutableLiveData()
    val eventSnackbar: LiveData<Boolean>
        get() = _eventSnackbar

    private val _eventLogin: MutableLiveData<Boolean> = MutableLiveData()
    val eventLogin: LiveData<Boolean>
        get() = _eventLogin

    private val _email: MutableLiveData<String> = MutableLiveData()
    val email: MutableLiveData<String>
        get() = _email

    private val _password: MutableLiveData<String> = MutableLiveData()
    val password: MutableLiveData<String>
        get() = _password

    init {
        _books.value = ArrayList()
        _email.value = ""
        _password.value = ""
    }

    fun getLoginStateFromSharedPreferences(activity: MainActivity) {
        val isUserLoggedIn = SharedPreferencesHelper.getLoginStatusFromSharedPreferences(activity)
        _isUserLoggedIn.value = isUserLoggedIn
    }

    fun addLoginStateToSharedPreferences(activity: MainActivity) {
        SharedPreferencesHelper.addLoginStatusToSharedPreferences(activity, true)
    }

    fun getBookViewTitle(book: Book): String {
        return "${book.author}: ${book.name}"
    }

    fun getBookViewSubtitle(book: Book): String {
        return "${book.publisher} | ${book.pageNumber} p | ${book.genre}"
    }

    fun onLogin() {
        if (ValidationUtils.isLoginRegisterFormValid(_email.value.toString(), _password.value.toString())) {
            _eventLogin.value = true
        } else {
            _eventSnackbar.value = true
        }
    }

    fun onAddBook(){
        _eventAddBook.value = true
    }

    fun onCancel() {
        _eventCancel.value = true
    }

    fun onDisplayInstructions() {
        _eventDisplayInstructions.value = true
    }

    fun onDisplayBookList() {
        _eventDisplayBookList.value = true
    }

    fun onSaveBook(book: Book?) {
        if (book != null && ValidationUtils.isBookDetailsFormValid(book)) {
            _books.value?.add(book)
            _eventSaveBook.value = true
        } else {
            _eventSnackbar.value = true
        }
    }

    fun eventSaveBookComplete() {
        _eventSaveBook.value = false
    }

    fun eventCancelComplete() {
        _eventCancel.value = false
    }

    fun eventSnackbarComplete() {
        _eventSnackbar.value = false
    }

    fun eventLoginComplete() {
        _eventLogin.value = false
    }

    fun eventDisplayInstructionsComplete() {
        _eventDisplayInstructions.value = false
    }

    fun eventDisplayBookListComplete() {
        _eventDisplayBookList.value = false
    }

    fun eventAddBookComplete(){
        _eventAddBook.value = false
    }
}