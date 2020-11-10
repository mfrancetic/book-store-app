package com.mfrancetic.bookstore

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mfrancetic.bookstore.models.Book

class BookViewModel : ViewModel() {

    private val _books: MutableLiveData<MutableList<Book>> = MutableLiveData()
    private val books: LiveData<MutableList<Book>>
        get() = _books

    init {
        _books.value = ArrayList()
    }

    fun saveBook(book: Book?) {
        if (book != null) {
            _books.value?.add(book)
        }
    }
}