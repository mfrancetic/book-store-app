package com.mfrancetic.bookstore.utils

import com.mfrancetic.bookstore.models.Book

class ValidationUtils {

    companion object {

        fun isBookDetailsFormValid(book: Book): Boolean {
            return book.name.isNotBlank() && book.author.isNotBlank() && book.genre.isNotBlank()
                    && book.publisher.isNotBlank() && book.pageNumber != 0
        }

        fun isLoginRegisterFormValid(email: String, password: String): Boolean {
            return email.isNotBlank() && password.isNotBlank()
        }
    }
}