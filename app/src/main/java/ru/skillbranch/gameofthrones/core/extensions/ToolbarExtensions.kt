package ru.skillbranch.gameofthrones.core.extensions

import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment

fun Fragment.initializeToolbar(
    toolbar: Toolbar,
    title: String? = null,
    hasHomeButton: Boolean = true
) {
    if (activity is AppCompatActivity) {
        val appCompatActivity = activity as AppCompatActivity
        appCompatActivity.setSupportActionBar(toolbar)

        appCompatActivity.supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(hasHomeButton)
            setHomeButtonEnabled(hasHomeButton)
            setTitle(title)
        }
    }
}