# AnimatedCountTextView

A library that helps you animate change in numeric values in a `TextView`.

[![Build Status](https://travis-ci.com/r4sh33d/AnimatedCountTextView.svg?token=8TPyvGS2YqpBT3ypdxNc&branch=master)](https://travis-ci.com/r4sh33d/AnimatedCountTextView)

## Gradle Dependency

Add the dependency to your app's `build.gradle`:

```groovy
implementation 'com.r4sh33d:AnimatedCountTextView:0.0.1'
```

## Usage

Add the `AnimatedCountTextView` to your layout. 

```xml
<com.r4sh33d.animatedcounttextview.AnimatedCountTextView
    app:startWith="0"
    app:endWith="100"
    app:duration="4000"
    app:suffix="%"
    app:numberType="integer"
    android:id="@+id/textView"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:text="0"
    android:textSize="30sp" />
```

Then call the `start()` method to start the count: 

```kotlin
countTextView.start()
```

You can optionally listen for the count end event by setting a `CountEndListener`

```kotlin
countTextView.countEndListener(object : CountEndListener {
      override fun onCountFinish() {
           // Do something 
      }
   })
```

If required, you can also stop the count at any time by calling the `stop()` method:
```kotlin
countTextView.stop()
```

That's all for basic usage. Your `AnimatedCountTextView` should animate form your `startWith` value to `endWith` value within the given time `duration`.

## Customisation
`AnimatedCountTextView` attempts to use some default values to simplify the usage. The behaviour can be further 
customized by setting the following attributes via `xml` or `code`. 

### Start and End values
You can use the xml attributes `startWith` and the `endWith` values to specify the value to animate from, and value to animate to, respectively.  
 
```xml
<com.r4sh33d.animatedcounttextview.AnimatedCountTextView
    ...
    app:startWith="0"
    app:endWith="100"/>
```
or programmatically:
```kotlin
countTextView.startWith(0)
countTextView.endWith(100)
```

### Duration
You can specify the duration(in milliseconds) for the count-up or count-down animation using the `duration` attribute in xml.   
 
```xml
<com.r4sh33d.animatedcounttextview.AnimatedCountTextView
    ...
    app:duration="4000"/>
```
or programmatically:
```kotlin
countTextView.duration(4000)
```

### Number Type
You can use `NumberType` to specify the type of number you want to animate. You can either specify `NumberType.Integer()` or `NumberType.Decimal()`. You can also apply custom formatting to display the animated values. Custom formats can be specified by passing a `DecimalFormat` to `NumberType.Integer()` or `NumberType.Decimal()` constructor. The default `NumberType` is `Integer`.
 
```xml
<com.r4sh33d.animatedcounttextview.AnimatedCountTextView
    ...
    app:numberType="integer"/>
```
Programmatically:
```kotlin
countTextView.numberType(NumberType.Decimal(twoDecimalPlacesFormat))
//or 
countTextView.numberType(NumberType.Integer())
```
### Prefix and Suffix
You can specify `prefix` and/or `suffix` to the animated values. This is useful if you want to specify a currency symbol as a `prefix` or the percentage sign as the `suffix`.
 
```xml
<com.r4sh33d.animatedcounttextview.AnimatedCountTextView
    ...
    app:suffix="%"
    app:prefix="$"/>
```
or programmatically:
```kotlin
 countTextView.prefix("$")
// or 
 countTextView.suffix("%")
```

### Interpolator
You can specify the [Interpolator](https://developer.android.com/reference/android/view/animation/Interpolator) to use when animating the values. This can only be done programmatically:
```kotlin
countTextView.interpolator(AccelerateDecelerateInterpolator())
```

##  License

    Copyright (c) 2019 Rasheed Sulayman.

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
