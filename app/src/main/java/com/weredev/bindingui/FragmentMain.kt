package com.weredev.bindingui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.weredev.binding_ui.viewBinding
import com.weredev.bindingui.databinding.FragmentMainBinding
/**
 * This example demonstrates the use of BindingUI to acquire view binding within a Fragment.
 * For proper functioning of the library, it is mandatory to pass the layout's resId that you intend to use.
 */
class FragmentMain: Fragment(R.layout.fragment_main) {

    private val binding by viewBinding(FragmentMainBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.txtExample.setText(R.string.example)
    }
}