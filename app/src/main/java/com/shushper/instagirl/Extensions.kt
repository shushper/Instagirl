package com.shushper.instagirl

import android.app.Activity
import android.support.annotation.IdRes
import android.support.v7.widget.RecyclerView
import android.view.View

fun <T : View> RecyclerView.ViewHolder.bindView(@IdRes res: Int): Lazy<T> {
    @Suppress("UNCHECKED_CAST")
    return lazy { itemView.findViewById<T>(res) }
}

fun <T : View> Activity.bind(@IdRes res: Int): Lazy<T> {
    @Suppress("UNCHECKED_CAST")
    return lazy { findViewById<T>(res) }
}