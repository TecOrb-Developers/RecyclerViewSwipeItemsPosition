package com.e.swaplistproject.callback

import androidx.annotation.Nullable
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.e.swaplistproject.adapter.AdapterSwappingList


class RecycleViewRowMoveCallback(var touchHelperContact: RecyclerViewRowTouchHelperContract) :
    ItemTouchHelper.Callback() {

    var myViewHolder: AdapterSwappingList.ViewHolder? = null

    override fun getMovementFlags(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder
    ): Int {
        val dragFlag = ItemTouchHelper.UP or ItemTouchHelper.DOWN
        return makeMovementFlags(dragFlag, 0)
    }

    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean {
        this.touchHelperContact.onRowMoved(viewHolder.adapterPosition, target.adapterPosition)
        return false
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        TODO("Not yet implemented")
    }

    override fun clearView(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder) {
        super.clearView(recyclerView, viewHolder)
        if (viewHolder is AdapterSwappingList.ViewHolder) {
            myViewHolder = viewHolder
            touchHelperContact.onRowClear(myViewHolder!!)
        }
    }

    override fun onSelectedChanged(
        @Nullable viewHolder: RecyclerView.ViewHolder?,
        actionState: Int
    ) {
        if (actionState != ItemTouchHelper.ACTION_STATE_IDLE) {
            if (viewHolder is AdapterSwappingList.ViewHolder) {
                myViewHolder = viewHolder
                touchHelperContact.onRowSelected(myViewHolder!!)
            }
        }
        super.onSelectedChanged(viewHolder, actionState)
    }

    override fun isLongPressDragEnabled(): Boolean {
        return true
    }

    override fun isItemViewSwipeEnabled(): Boolean {
        return false
    }

    interface RecyclerViewRowTouchHelperContract {

        fun onRowMoved(from: Int, to: Int)

        fun onRowSelected(myViewHolder: AdapterSwappingList.ViewHolder)

        fun onRowClear(myViewHolder: AdapterSwappingList.ViewHolder)
    }
}