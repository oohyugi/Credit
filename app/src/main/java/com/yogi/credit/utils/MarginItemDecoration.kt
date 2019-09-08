package com.yogi.credit.utils

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

/**
 * Created by oohyugi on 2019-09-07.
 * github: https://github.com/oohyugi
 */
class MarginItemDecoration(
    private val space: Int,
    private val type: Int,
    private val spanCount: Int = 0
) :
    RecyclerView.ItemDecoration() {

    companion object {
        const val TYPE_GRID_LAYOUT = 1
        const val TYPE_LINEARLAYOUT_VERTICAL = 2
    }

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {

        with(outRect) {
            if (type == TYPE_GRID_LAYOUT) {
                val position = parent.getChildAdapterPosition(view)
                val column = position % spanCount
                outRect.left = column * space / spanCount
                outRect.right = space - (column + 1) * space / spanCount
                if (position >= spanCount) {
                    outRect.top = space
                }
            }
            if (type == TYPE_LINEARLAYOUT_VERTICAL) {
                val position = parent.getChildAdapterPosition(view)
                if (position != 0) {
                    top = space
                }
            }
        }
    }
}
