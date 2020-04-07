package ru.skillbranch.skillarticles.extensions

import android.util.TypedValue
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment

fun AppCompatActivity.initializeToolbar(
    toolbar: Toolbar,
    title: String? = null,
    hasHomeButton: Boolean = true
) {
    setSupportActionBar(toolbar)

    supportActionBar?.apply {
        setDisplayHomeAsUpEnabled(hasHomeButton)
        setHomeButtonEnabled(hasHomeButton)
        setTitle(title)
    }
}

fun Fragment.getToolbarHeight(): Int {
    val typedValue = TypedValue()
    var actionBarSize = 0
    if (requireActivity().theme.resolveAttribute(android.R.attr.actionBarSize, typedValue, true)) {
        actionBarSize = TypedValue.complexToDimensionPixelSize(
            typedValue.data,
            requireActivity().resources.displayMetrics
        )
    }
    return actionBarSize
}