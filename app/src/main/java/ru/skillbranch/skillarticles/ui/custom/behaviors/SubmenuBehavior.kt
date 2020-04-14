package ru.skillbranch.skillarticles.ui.custom.behaviors

import android.view.View
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.view.ViewCompat
import ru.skillbranch.skillarticles.ui.custom.ArticleSubmenu

class SubmenuBehavior : CoordinatorLayout.Behavior<ArticleSubmenu>() {

    private var width: Int = 0
    private var height: Int = 0

    override fun onLayoutChild(
        parent: CoordinatorLayout,
        child: ArticleSubmenu,
        layoutDirection: Int
    ): Boolean {
        width = child.measuredWidth
        height = child.measuredHeight
        return super.onLayoutChild(parent, child, layoutDirection)
    }

    override fun onStartNestedScroll(
        coordinatorLayout: CoordinatorLayout,
        child: ArticleSubmenu,
        directTargetChild: View,
        target: View,
        axes: Int,
        type: Int
    ): Boolean = axes == ViewCompat.SCROLL_AXIS_VERTICAL

    override fun onNestedScroll(
        coordinatorLayout: CoordinatorLayout,
        child: ArticleSubmenu,
        target: View,
        dxConsumed: Int,
        dyConsumed: Int,
        dxUnconsumed: Int,
        dyUnconsumed: Int,
        type: Int,
        consumed: IntArray
    ) {
        if (child.isOpen) {
            if (dyConsumed > 0) {
                hideMenu(child)
            } else if (dyConsumed < 0) {
                showMenu(child)
            }
        }
    }

    private fun showMenu(child: ArticleSubmenu) {
        child.visibility = View.VISIBLE
        child.clearAnimation()
        child.animate().translationY(0f).translationX(0f).duration = 300
    }

    private fun hideMenu(child: ArticleSubmenu) {
        child.clearAnimation()
        child.animate().translationY(height.toFloat()).translationX(width.toFloat()).duration = 300
        child.visibility = View.INVISIBLE
    }
}