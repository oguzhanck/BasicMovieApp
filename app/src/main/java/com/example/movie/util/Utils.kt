package com.example.movie.util

import android.content.Context
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.movie.R


fun Context.showToast(message: String, duration: Int = Toast.LENGTH_LONG) {
    Toast.makeText(this, message, duration).show()
}

fun Fragment.showToast(message: String, duration: Int = Toast.LENGTH_LONG) {
    requireContext().showToast(message, duration)
}

fun Fragment.showErrorToast(message: String = requireContext().getString(R.string.error_message), duration: Int = Toast.LENGTH_LONG) {
    requireContext().showToast(message, duration)
}
