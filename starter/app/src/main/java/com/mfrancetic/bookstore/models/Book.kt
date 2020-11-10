package com.mfrancetic.bookstore.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Book(var name: String, var author: String, var pageCount: Int, var publisher: String, var genre: String,
                val images: List<String> = mutableListOf()) : Parcelable