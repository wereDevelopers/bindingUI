package com.weredev.binding_ui

import android.content.Intent
import android.content.res.ColorStateList
import android.os.Build
import android.os.Bundle
import android.os.SystemClock
import android.text.Html
import android.text.Spanned
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.ColorInt
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
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

/**
 * Sets the tappable status of a view by enabling or disabling it, changing its background color,
 * and setting or removing the click listener based on the active state.
 *
 * @param isActive A boolean flag indicating whether the view should be active (enabled and tappable).
 * @param onClickListener The click listener to be set if the view is active. If the view is inactive, the listener is removed.
 * @param activeColor The color resource to be applied as the background tint when the view is active.
 * @param disableColor The color resource to be applied as the background tint when the view is inactive.
 */
fun View.setTappableStatus(isActive: Boolean, onClickListener: View.OnClickListener, @ColorInt activeColor: Int, @ColorInt disableColor: Int) {
    isEnabled = isActive
    if (isActive) {
        backgroundTintList = ColorStateList.valueOf(
            ContextCompat.getColor(
                context,
                activeColor
            )
        )
        setOnClickListener(onClickListener)
    } else {
        backgroundTintList = ColorStateList.valueOf(
            ContextCompat.getColor(
                context,
                disableColor
            )
        )
        setOnClickListener(null)
    }
}

/**
 * Checks if the view is clickable by ensuring that clicks are not registered
 * within a specified number of milliseconds of each other to prevent accidental double clicks.
 *
 * @param minMillisecondStop The minimum time interval (in milliseconds) required between two consecutive clicks.
 *                           Defaults to 500 milliseconds.
 * @return `true` if the view is clickable (i.e., the last click was more than `minMillisecondStop` milliseconds ago),
 *         `false` otherwise.
 */
private var mLastClickTime: Long = 0
fun View.isViewClickable(minMillisecondStop: Long = 500): Boolean {
    if (SystemClock.elapsedRealtime() - mLastClickTime < minMillisecondStop){
        return false
    }
    mLastClickTime = SystemClock.elapsedRealtime()
    return true
}