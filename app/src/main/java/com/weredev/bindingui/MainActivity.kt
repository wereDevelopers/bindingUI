package com.weredev.bindingui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.weredev.binding_ui.viewBinding
import com.weredev.bindingui.databinding.ActivityMainBinding

/**
 * This example demonstrates the use of BindingUI to acquire view binding within an activity.
 */
class MainActivity : AppCompatActivity() {

    private val binding by viewBinding(ActivityMainBinding::inflate)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.btnFakeFloating.setOnClickListener {
            println("clicked")
        }
    }
}