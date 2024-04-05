package com.example.noteapp.presentation.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

abstract class BaseListAdapter<D: Any, V: ViewBinding>(
    var layoutId: ((position: Int) -> Int)? = null,
    var onBind: ((binding: V, data: D, position: Int) -> Unit)? = null
): RecyclerView.Adapter<BaseListAdapter.BaseViewHolder<D, V>>() {

    private val listData = mutableListOf<D>()

    var onClickItem: ((D, Int) -> Unit)? = null

    private fun inflateBinding(inflater: LayoutInflater, parent: ViewGroup, viewType: Int): V {
        val inflatedView = inflater.inflate(viewType, parent, false)
        @Suppress("UNCHECKED_CAST")
        return inflatedView as V
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<D, V> {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = inflateBinding(layoutInflater, parent, viewType)
        return BaseViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BaseViewHolder<D, V>, position: Int) {
        val item = listData[position]
        holder.onBind(item)
        onBind?.invoke(holder.viewBinding as V, item, position)
        holder.itemView.setOnClickListener {
            onClickItem?.invoke(item, position)
        }
    }

    fun setData(list: List<D>) {
        listData.clear()
        listData.addAll(list)
        notifyDataSetChanged()
    }

    fun remove(position: Int) {
        listData.removeAt(position)
        notifyItemRemoved(position)
    }

    override fun getItemCount(): Int = listData.size

    override fun getItemViewType(position: Int): Int {
        return layoutId?.invoke(position) ?: 1
    }

    open class BaseViewHolder<D, V: ViewBinding>(open val viewBinding: V): RecyclerView.ViewHolder(viewBinding.root) {
        open fun onBind(data: D) {}

    }
}