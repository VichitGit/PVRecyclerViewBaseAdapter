package com.example.pvrecyclerview_adapter_helper

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

abstract class BaseAdapter<T : BaseModel>(
    recyclerView: RecyclerView
) : RecyclerView.Adapter<BaseViewHolder<T>>() {

    private var items = mutableListOf<T>()
    private var onHandlePagination: OnHandlePagination? = null
    private var totalPage: Int = 1
    private var currentPage: Int = 1
    private var isLoading: Boolean = false

    init {
        if (recyclerView.layoutManager is LinearLayoutManager) {
            val linearLayoutManager = recyclerView.layoutManager as LinearLayoutManager?
            recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    //Get position items in LayoutManager
                    val lastVisibleItem =
                        linearLayoutManager?.findLastCompletelyVisibleItemPosition()
                    totalPage = onHandlePagination?.getTotalPage()!!

                    if (!isLoading && currentPage < totalPage && lastVisibleItem == items.size - 3) {
                        currentPage += 1
                        onHandlePagination?.onLoanMore(totalPage, currentPage)
                        if (currentPage > 1)
                            addProgressBar()
                    }
                }
            })
        }
    }

    fun setItems(items: Collection<T>?) {
        if (this.items === items) return
        with(this.items) {
            //clear()
            addAll(items ?: emptyList())
        }
        notifyDataSetChanged()
    }

    fun setLoading(loading: Boolean) {
        isLoading = loading
    }

    fun onResultCallback(status: Result) {
        if (status == Result.SUCCESS) {
            setLoading(false)
            if (items.size > 0)
                removeProgressbar()
        }
    }

    fun setRefresh() {
        totalPage = 1
        currentPage = 1
        isLoading = false

        items.clear()
        notifyDataSetChanged()
    }

    private fun addProgressBar() {
        val i = getProgressbar()
        items.addAll(i)
        notifyDataSetChanged()
    }

    private fun removeProgressbar() {
        items.removeAt(items.size - 1)
        notifyItemRemoved(items.size - 1)
    }

    fun setOnHandlePagination(onHandlePagination: OnHandlePagination) {
        this.onHandlePagination = onHandlePagination
    }

    abstract fun getViewHolder(viewType: Int, itemView: View): BaseViewHolder<T>
    abstract fun setItemViewType(position: Int, items: MutableList<T>): Int
    abstract fun getProgressbar(): MutableList<T>

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): BaseViewHolder<T> {
        val view = LayoutInflater.from(viewGroup.context).inflate(viewType, viewGroup, false)
        return getViewHolder(viewType, view)
    }

    override fun onBindViewHolder(viewHolder: BaseViewHolder<T>, position: Int) {
        return viewHolder.bindItem(items[position])
    }

    override fun getItemViewType(position: Int): Int {
        //return items[position].getLayoutType()
        return setItemViewType(position, items)
    }

    override fun getItemCount(): Int {
        return items.size
    }
}

abstract class BaseViewHolder<T>(itemView: View) : RecyclerView.ViewHolder(itemView) {
    abstract fun bindItem(item: T)
}



