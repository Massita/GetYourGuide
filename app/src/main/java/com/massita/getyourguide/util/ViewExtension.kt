package com.massita.getyourguide.util

import android.view.View
import android.widget.TextView

fun TextView.setTextOrGone(text: String?) {
    if(text.isNullOrEmpty()) {
        this.visibility = View.GONE
    } else {
        this.visibility = View.VISIBLE
        this.text = text
    }
}