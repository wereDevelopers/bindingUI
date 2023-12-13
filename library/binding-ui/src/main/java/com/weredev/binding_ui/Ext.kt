package com.weredev.binding_ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding

/**
 * This inline function simplifies the process of creating ViewBinding within an Android Activity.
 * Using the higher-order function `bindingInflater`, it extracts and inflates the layout associated with the specified ViewBinding.
 *
 * @param <T> Type parameter extending the ViewBinding class.
 * @param bindingInflater Lambda function taking a LayoutInflater object and returning an object of type T (extension of ViewBinding).
 * @return Lazy<T> Returns a Lazyobject containing the ViewBinding.
 */
inline fun <T : ViewBinding> AppCompatActivity.viewBinding(
    crossinline bindingInflater: (LayoutInflater) -> T) =
    lazy(LazyThreadSafetyMode.NONE) {
        bindingInflater.invoke(layoutInflater)
    }

/**
 * Inflate a layout resource into this ViewGroup.
 *
 * @param layoutRes The resource ID of the layout to be inflated.
 * @return Returns the inflated View.
 */
fun ViewGroup.inflate(layoutRes: Int): View {
    return LayoutInflater.from(context).inflate(layoutRes, this, false)
}
