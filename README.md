# Binding-UI

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
Implementation("TODO link maven")
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
