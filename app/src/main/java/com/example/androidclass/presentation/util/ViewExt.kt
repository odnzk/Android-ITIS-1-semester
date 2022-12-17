package com.example.androidclass.presentation.util

import android.view.View
import androidx.annotation.StringRes
import com.google.android.material.snackbar.Snackbar

fun View.showSnackbar(@StringRes stringResId: Int) {
    Snackbar.make(
        this, this.context.getString(stringResId), Snackbar.LENGTH_SHORT
    ).show()
}
