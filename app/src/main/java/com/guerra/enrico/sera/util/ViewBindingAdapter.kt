package com.guerra.enrico.sera.util

import android.view.View
import androidx.databinding.BindingAdapter

/**
 * Created by enrico
 * on 05/01/2020.
 */
@BindingAdapter("goneUnless")
fun goneUnless(view: View, visible: Boolean) {
  view.visibility = if (visible) View.VISIBLE else View.GONE
}

@BindingAdapter("goneIf")
fun goneIf(view: View, gone: Boolean) {
  view.visibility = if (gone) View.GONE else View.VISIBLE
}