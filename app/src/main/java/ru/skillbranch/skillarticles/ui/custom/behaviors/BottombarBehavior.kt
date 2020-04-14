package ru.skillbranch.skillarticles.ui.custom.behaviors

import android.view.View
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.view.ViewCompat
import ru.skillbranch.skillarticles.ui.custom.Bottombar

class BottombarBehavior : CoordinatorLayout.Behavior<Bottombar>() {

    private var height: Int = 0

    override fun onLayoutChild(
        parent: CoordinatorLayout,
        child: Bottombar,
        layoutDirection: Int
    ): Boolean {
        height = child.height
        return super.onLayoutChild(parent, child, layoutDirection)
    }

    override fun onStartNestedScroll(
        coordinatorLayout: CoordinatorLayout,
        child: Bottombar,
        directTargetChild: View,
        target: View,
        axes: Int,
        type: Int
    ): Boolean {
        return axes == ViewCompat.SCROLL_AXIS_VERTICAL
    }

    override fun onNestedScroll(
        coordinatorLayout: CoordinatorLayout,
        child: Bottombar,
        target: View,
        dxConsumed: Int,
        dyConsumed: Int,
        dxUnconsumed: Int,
        dyUnconsumed: Int,
        type: Int,
        consumed: IntArray
    ) {
        if (dyConsumed > 0) {
            slideUp(child)
        } else if (dyConsumed < 0) {
            slideDown(child)
        }
    }

    private fun slideDown(child: Bottombar) {
        child.clearAnimation()
        child.animate().translationY(0f).duration = 200
    }

    private fun slideUp(child: Bottombar) {
        child.clearAnimation()
        child.animate().translationY(height.toFloat()).duration = 200
    }
}