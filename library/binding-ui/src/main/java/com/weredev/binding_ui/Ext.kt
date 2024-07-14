package com.weredev.binding_ui

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.text.Html
import android.text.Spanned
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import java.io.Serializable

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

/**
 * Retrieves a serializable object of type T from the extras of an Intent using the provided key.
 *
 * @param key The key used to retrieve the serializable object from the Intent extras.
 * @return The deserialized object of type T, or null if the key is not found or the deserialization fails.
 * @param T The expected type of the deserialized object.
 */
public inline fun <reified T> Intent?.getSerializable(key: String): T? {
    return this?.extras?.getSerializableFromBundle(key)
}

/**
 * Retrieves a serializable object of type T from a Bundle using the provided key.
 *
 * @param key The key used to retrieve the serializable object from the Bundle.
 * @return The deserialized object of type T, or null if the key is not found or the deserialization fails.
 * @param T The expected type of the deserialized object.
 */
public inline fun <reified T> Bundle?.getSerializableFromBundle(key: String): T? {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        this?.getSerializable(key, Serializable::class.java) as T?
    } else {
        this?.getSerializable(key) as T?
    }
}

/**
 * Extension function to convert a HTML-formatted string into a Spanned object.
 * Uses different methods based on the Android version.
 *
 * @receiver String The HTML-formatted string to be converted.
 * @return Spanned The converted Spanned object.
 */
fun String.convertFromHtml(): Spanned {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        Html.fromHtml(this, Html.FROM_HTML_MODE_LEGACY)
    } else {
        Html.fromHtml(this)
    }
}