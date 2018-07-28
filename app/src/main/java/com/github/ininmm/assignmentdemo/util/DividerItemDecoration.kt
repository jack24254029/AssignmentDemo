package com.github.ininmm.assignmentdemo.util

import android.graphics.Rect
import android.support.v7.widget.RecyclerView
import android.view.View

/**
 * Created by Michael Lien
 * on 2018/7/28.
 */
class DividerItemDecoration(private val margin: Int) : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        outRect.bottom = margin
        outRect.left = margin
        outRect.right = margin
        if (parent.getChildAdapterPosition(view) == 0) {
            outRect.top = margin
        }
    }
}