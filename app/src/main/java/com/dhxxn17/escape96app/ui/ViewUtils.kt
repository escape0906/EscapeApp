package com.dhxxn17.escape96app.ui

import android.view.View

fun Boolean.toVisibility(): Int {
    return if (this) View.VISIBLE else View.GONE
}