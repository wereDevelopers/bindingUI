# Binding-UI

[![Jitpack](https://jitpack.io/v/wereDevelopers/bindingUI.svg)](https://jitpack.io/#wereDevelopers/bindingUI)
[![API](https://img.shields.io/badge/API-23%2B-brightgreen.svg?style=flat)](https://android-arsenal.com/api?level=23)
[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://github.com/wereDevelopers/bindingUI/blob/main/LICENSE)

Binding-UI is a set of functions aimed at simplifying the creation and management of the view-binding lifecycle in activities and fragments

## How to implement:

add in the Gradle
```
android {
    buildFeatures {
        viewBinding = true
    }
}
```
```groovy
dependencies {
    implementation('com.github.wereDevelopers:bindingUI:{LastTag}')
}
```


## How to use
After creating your own XML layout


### Activity:
```
import com.weredev.binding_ui.viewBinding
import com.weredev.bindingui.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding by viewBinding(ActivityMainBinding::inflate)
	...
```


### Fragment:
```
import com.weredev.binding_ui.viewBinding
import com.weredev.bindingui.databinding.FragmentMainBinding

class FragmentMain: Fragment(R.layout.fragment_main) {

    private val binding by viewBinding(FragmentMainBinding::bind)
    ....
```
