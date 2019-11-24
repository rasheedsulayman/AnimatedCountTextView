# AnimatedCountTextView

A library that helps you animate change in numeric values in a TextView.

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

That's all for basic usage. Your `countTextView` should animate form your `startWith` value to `endWith` value within the given time `duration`.

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
