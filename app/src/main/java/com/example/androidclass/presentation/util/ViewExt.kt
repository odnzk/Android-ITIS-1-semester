package com.example.androidclass.presentation.util

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.example.androidclass.R
import com.google.android.material.snackbar.Snackbar

fun View.showSnackbar(@StringRes stringResId: Int) {
    Snackbar.make(
        this, this.context.getString(stringResId), Snackbar.LENGTH_SHORT
    ).show()
}

fun Fragment.findTopNavController(): NavController {
    return requireActivity().findNavController(R.id.main_fragment_container)
}

fun View.hideKeyboard() {
    val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
    imm?.hideSoftInputFromWindow(windowToken, 0)

}
