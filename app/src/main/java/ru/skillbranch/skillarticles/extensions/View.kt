package ru.skillbranch.skillarticles.extensions

import android.view.View
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.view.marginBottom
import androidx.core.view.marginLeft
import androidx.core.view.marginRight
import androidx.core.view.marginTop

fun View.setMarginOptionally(left:Int = marginLeft, top : Int = marginTop, right : Int = marginRight, bottom : Int = marginBottom) {
    val layoutParams = this.layoutParams as CoordinatorLayout.LayoutParams
    layoutParams.setMargins(left, top, right, bottom)
    this.layoutParams = layoutParams
}