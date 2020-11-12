package com.mfrancetic.bookstore.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Book(var name: String = "",
                var author: String = "",
                var pageNumber: Int = 0,
                var publisher: String = "",
                var genre: String = "",
                val images: List<String> = mutableListOf()) : Parcelable