package com.e.swaplistproject.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.e.swaplistproject.R
import com.e.swaplistproject.callback.RecycleViewRowMoveCallback
import com.e.swaplistproject.model.ModelSwap
import java.util.*


class AdapterSwappingList(var context: Context, var swapList: List<ModelSwap>) :
    RecyclerView.Adapter<AdapterSwappingList.ViewHolder>(),
    RecycleViewRowMoveCallback.RecyclerViewRowTouchHelperContract {


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val v: View = LayoutInflater.from(parent.context).inflate(R.layout.list_swap, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.icon.setImageResource(swapList[holder.adapterPosition].image)
    }

    override fun getItemCount(): Int {
        return swapList.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var icon: ImageView
        var cardView: CardView

        init {
            icon = itemView.findViewById(R.id.icon_swap) as ImageView
            cardView = itemView.findViewById(R.id.cardview) as CardView
        }
    }


    override fun onRowMoved(from: Int, to: Int) {
        if (from < to) {
            for (i in from until to) {
                Collections.swap(swapList, i, i + 1)
            }
        } else {
            for (i in from downTo to + 1) {
                Collections.swap(swapList, i, i - 1)
            }
        }
        notifyItemMoved(from, to)
    }

    override fun onRowSelected(myViewHolder: ViewHolder) {
    // Toast.makeText(context, "", Toast.LENGTH_SHORT).show()

    }

    override fun onRowClear(myViewHolder: ViewHolder) {
       // Toast.makeText(context, myViewHolder.layoutPosition, Toast.LENGTH_SHORT).show()

    }


}