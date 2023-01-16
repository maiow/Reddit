package com.mivanovskaya.humblr.tools

/**попытка разобраться с DelegateAdapter остановилась на "реализовать интерфейс LayoutContainer"*/
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.extensions.LayoutContainer

interface BaseViewHolding<Command, ItemType> {
    val containerView: View

    fun bind(data: ItemType)
}

abstract class BaseViewHolder<Command, ItemType>(
    override val containerView: View
) : RecyclerView.ViewHolder(containerView), BaseViewHolding<Command, ItemType>, LayoutContainer