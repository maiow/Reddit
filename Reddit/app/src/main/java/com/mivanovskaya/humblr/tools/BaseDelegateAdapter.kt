package com.mivanovskaya.humblr.tools

/**попытка разобраться с DelegateAdapter с помощью первой библиотеки попроще*/

//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import androidx.recyclerview.widget.RecyclerView
//
//abstract class BaseDelegateAdapter<VH : BaseViewHolder<*, *>?, T : Any> :
//    IDelegateAdapter<VH, T> {
//    protected abstract fun onBindViewHolder(
//        view: View, item: T, viewHolder: VH
//    )
//
//    protected abstract val layoutId: Int
//
//    protected abstract fun createViewHolder(parent: View?): VH
//    fun onRecycled(holder: VH) {}
//    fun onCreateViewHolder(
//        parent: ViewGroup, viewType: Int
//    ): RecyclerView.ViewHolder {
//        val inflatedView = LayoutInflater
//            .from(parent.context)
//            .inflate(layoutId, parent, false)
//        val holder = createViewHolder(inflatedView)
//        holder.setListener(object : ItemInflateListener() {
//            fun inflated(viewType: Any, view: View) {
//                onBindViewHolder(view, viewType as T, holder)
//            }
//        })
//        return holder
//    }
//
//    fun onBindViewHolder(
//        holder: VH,
//        items: List<T>,
//        position: Int
//    ) {
//        (holder as BaseViewHolder<*, *>).bind(items[position])
//    }
//}