package com.mfrancetic.bookstore

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mfrancetic.bookstore.models.Book

class MainViewModel : ViewModel() {

    private val _books: MutableLiveData<List<Book>> = MutableLiveData()
    private val books: LiveData<List<Book>>
        get() = _books
}