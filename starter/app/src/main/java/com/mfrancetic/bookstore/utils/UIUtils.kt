package com.mfrancetic.bookstore.utils

import android.view.View
import com.google.android.material.snackbar.Snackbar

class UIUtils {
    companion object {

        fun displaySnackbar(view: View, text: String) {
            Snackbar.make(view, text, Snackbar.LENGTH_SHORT).show()
        }
    }
}